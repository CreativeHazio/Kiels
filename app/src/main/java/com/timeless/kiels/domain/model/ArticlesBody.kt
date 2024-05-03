package com.timeless.kiels.domain.model

data class ArticlesBody(
    val articles : MutableList<Article>,
    val status : String,
    val totalResults : Int
)
