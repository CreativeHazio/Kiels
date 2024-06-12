package com.timeless.kiels.data.local.article

data class ArticleBodyEntity(
    val articles : MutableList<ArticleEntity>,
    val status : String,
    val totalResults : Int
)
