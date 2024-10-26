package com.jmp.common.ui.compose.components

import android.animation.ValueAnimator
import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.animation.core.animateDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jmp.common.ui.compose.components.state.ToolbarState
import com.jmp.common.ui.compose.rememberToolbarState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

private const val COLLAPSE_DURATION_TIME = 600L

@Composable
fun CollapsingToolbar(
    modifier: Modifier = Modifier,
    minCollapsingBarHeight: Dp,
    maxCollapsingBarHeight: Dp,
    listState: LazyListState = rememberLazyListState(),
    collapseModeLockedState: MutableState<Boolean>? = null,
    onCollapseModeChange: ((Boolean) -> Unit)? = null,
    collapsingToolbar: @Composable (ToolbarState, Boolean, (Boolean) -> Unit) -> Unit,
    content: @Composable (ToolbarState, LazyListState, Dp) -> Unit
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val minCollapsingBarHeightPx = with(density) { minCollapsingBarHeight.roundToPx() }
    val maxCollapsingBarHeightPx = with(density) { maxCollapsingBarHeight.roundToPx() }
    val toolbarState = rememberToolbarState(minCollapsingBarHeightPx..maxCollapsingBarHeightPx)
    val currentToolbarHeight = with(density) { toolbarState.height.toDp() }
    var maxHeight by remember { mutableStateOf(0.dp) }
    val listDynamicHeight = maxHeight - currentToolbarHeight
    val nestedScrollConnection = remember {
        getNestedScrollConnection(
            toolbarState = toolbarState,
            lazyListState = listState,
            isCollapseModeLocked = { collapseModeLockedState?.value == true },
            offsetTopLimit = maxCollapsingBarHeightPx.toFloat(),
            scope = scope
        )
    }

    ConstraintLayout(modifier) {
        Box(
            Modifier
                .background(Color.Transparent)
                .fillMaxHeight()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .onGloballyPositioned { coordinates ->
                        maxHeight = with(density) { coordinates.size.height.toDp() }
                    },
                content = {}
            )
        }

        Box(modifier = Modifier.nestedScroll(nestedScrollConnection)) {
            collapsingToolbar(toolbarState, listState.isScrollInProgress) { collapse ->
                // Collapse toolbar
                onCollapseModeChange?.invoke(collapse)
                scope.launch {
                    if (collapse) {
                        val collapseStartValue = toolbarState.scrollOffset
                        val collapseEndValue = maxCollapsingBarHeightPx.toFloat()
                        val durationMillis = COLLAPSE_DURATION_TIME
                        val collapseValueAnimator =
                            ValueAnimator.ofFloat(collapseStartValue, collapseEndValue).apply {
                                duration = durationMillis
                                addUpdateListener { animation ->
                                    val animatedValue = animation.animatedValue as Float
                                    toolbarState.scrollOffset = animatedValue
                                }
                            }

                        collapseValueAnimator.start()
                    }
                }
            }
            content(toolbarState, listState, listDynamicHeight)
        }
    }
}

private fun getNestedScrollConnection(
    toolbarState: ToolbarState,
    lazyListState: LazyListState,
    isCollapseModeLocked: () -> Boolean,
    offsetTopLimit: Float = 0f,
    scope: CoroutineScope
): NestedScrollConnection =
    object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            if (isPreScrollLocked()) {
                toolbarState.scrollTopLimitReached = true
                toolbarState.scrollOffset = offsetTopLimit
            } else {
                toolbarState.scrollTopLimitReached =
                    lazyListState.firstVisibleItemIndex == 0
                            && lazyListState.firstVisibleItemScrollOffset == 0
                toolbarState.scrollOffset -= available.y
            }
            return Offset(0f, toolbarState.consumed)
        }

        override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
            if (shouldScrollOnPostFling(available.y)) {
                scope.launch {
                    animateDecay(
                        initialValue = toolbarState.height + toolbarState.offset,
                        initialVelocity = available.y,
                        animationSpec = FloatExponentialDecaySpec()
                    ) { value, _ ->
                        toolbarState.scrollTopLimitReached =
                            lazyListState.firstVisibleItemIndex == 0 && lazyListState.firstVisibleItemScrollOffset == 0
                        toolbarState.scrollOffset -= (value - (toolbarState.height + toolbarState.offset))
                        if (toolbarState.scrollOffset == 0f) scope.coroutineContext.cancelChildren()
                    }
                }
            }

            return super.onPostFling(consumed, available)
        }

        private fun isPreScrollLocked() = isCollapseModeLocked() && toolbarState.scrollOffset <= offsetTopLimit

        private fun shouldScrollOnPostFling(spaceAvailable: Float) =
            (spaceAvailable > 0 && !isCollapseModeLocked())
                    || (spaceAvailable > 0 && isCollapseModeLocked() && toolbarState.scrollOffset > offsetTopLimit)
    }
