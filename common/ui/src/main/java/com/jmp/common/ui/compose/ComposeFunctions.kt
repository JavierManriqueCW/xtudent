package com.jmp.common.ui.compose

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.jmp.common.ui.compose.components.state.ScrollState
import com.jmp.common.ui.compose.components.state.ToolbarState
import kotlinx.coroutines.flow.Flow

// region Extensions
fun Modifier.fadingEdge(
    topEndFadedPoint: Float = 0f,
    topStartFadingPoint: Float,
    bottomStartFadingPoint: Float,
    bottomEndFadedPoint: Float = 1f,
    linearEasing: Boolean = true
) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(
            brush =
            if (linearEasing) {
                Brush.verticalGradient(
                    topEndFadedPoint to Color.Transparent,
                    topStartFadingPoint to Color.Black,
                    bottomStartFadingPoint to Color.Black,
                    bottomEndFadedPoint to Color.Transparent
                )
            } else {
                val averageTopFadingPoint = (topEndFadedPoint + topStartFadingPoint) / 2
                val averageBottomFadingPoint = (bottomStartFadingPoint + bottomEndFadedPoint) / 2
                Brush.verticalGradient(
                    topEndFadedPoint to Color.Transparent,
                    averageTopFadingPoint to Color.Black.copy(alpha = 0.3f),
                    topStartFadingPoint to Color.Black,
                    bottomStartFadingPoint to Color.Black,
                    averageBottomFadingPoint to Color.Black.copy(alpha = 0.3f),
                    bottomEndFadedPoint to Color.Transparent
                )},
            blendMode = BlendMode.DstIn
        )
    }

fun Modifier.clearFocusOnTap(): Modifier = composed {
    val focusManager = LocalFocusManager.current
    Modifier.pointerInput(Unit) {
        awaitEachGesture {
            awaitFirstDown(pass = PointerEventPass.Initial)
            val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
            if (upEvent != null) {
                focusManager.clearFocus()
            }
        }
    }
}

inline fun Modifier.clickable(
    rippleEnabled: Boolean = false,
    crossinline onClick: () -> Unit
): Modifier = composed {
    if (rippleEnabled) {
        clickable { onClick() }
    } else {
        clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        }
    }
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideEnterTransitionHorizontally(): EnterTransition =
    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left).plus(fadeIn())

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideExitTransitionHorizontally(): ExitTransition =
    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left).plus(fadeOut())

fun AnimatedContentTransitionScope<NavBackStackEntry>.slidePopExitTransitionHorizontally(): ExitTransition =
    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right).plus(fadeOut())
// endregion

// region Remember functions
/**
 * Remembers the result of [flowWithLifecycle]. Updates the value if the [flow]
 * or [lifecycleOwner] changes. Cancels collection in onStop() and start it in onStart()
 *
 * @param flow The [Flow] that is going to be collected.
 * @param lifecycleOwner The [LifecycleOwner] to validate the [Lifecycle.State] from
 *
 * @return [Flow] with the remembered value of type [T]
 */
@Composable
fun <T> rememberFlowWithLifecycle(
    flow: Flow<T>,
    lifecycleOwner: LifecycleOwner
): Flow<T> {
    return remember(flow, lifecycleOwner) {
        flow.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
}

@Composable
fun rememberToolbarState(toolbarHeightRange: IntRange): ToolbarState {
    return rememberSaveable(saver = ScrollState.Saver) {
        ScrollState(toolbarHeightRange)
    }
}
// endregion
