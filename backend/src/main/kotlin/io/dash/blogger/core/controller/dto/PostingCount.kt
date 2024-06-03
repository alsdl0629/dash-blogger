package io.dash.blogger.core.controller.dto

data class PostingCount(
    val postingCount: Int
) {
    companion object {
        const val TEMPORARY_VALUE: Int = 0
    }
}
