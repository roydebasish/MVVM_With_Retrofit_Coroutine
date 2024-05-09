package com.mofficial.mvvm_with_retrofit_coroutine.models

data class QuoteResponse(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
)