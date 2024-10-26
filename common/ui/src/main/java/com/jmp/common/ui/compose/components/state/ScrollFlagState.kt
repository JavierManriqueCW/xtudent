package com.jmp.common.ui.compose.components.state

abstract class ScrollFlagState(heightRange: IntRange) : ToolbarState {

    init {
        require(heightRange.first >= 0 && heightRange.last >= heightRange.first) {
            "The lowest height value must be >= 0 and the highest height value must be >= the lowest value."
        }
    }

    protected val minHeight = heightRange.first
    protected val maxHeight = heightRange.last
    protected val rangeDifference = maxHeight - minHeight
    protected var consumedSpace: Float = 0f

    protected abstract var scrolledSpaceOffset: Float

    final override val height: Float
        get() = (maxHeight - scrolledSpaceOffset).coerceIn(minHeight.toFloat(), maxHeight.toFloat())

    final override val progress: Float
        get() = 1 - (maxHeight - height) / rangeDifference

    final override val consumed: Float
        get() = consumedSpace

    final override var scrollTopLimitReached: Boolean = true
}
