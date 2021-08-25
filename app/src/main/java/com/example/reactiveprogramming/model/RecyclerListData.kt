package com.example.reactiveprogramming.model

data class RecyclerListData(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
)