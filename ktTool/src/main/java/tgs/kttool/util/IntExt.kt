package tgs.kttool.util

import tgs.kttool.ToolApplication.toolAppContext


fun Int.dp2px() = (toolAppContext.getResources().getDisplayMetrics().density * this + 0.5).toInt()

fun Int.px2dp() = (this / (toolAppContext.getResources().getDisplayMetrics().density) + 0.5).toInt()
