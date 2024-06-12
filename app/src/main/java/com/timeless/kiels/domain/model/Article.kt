package com.timeless.kiels.domain.model

data class Article(
    val id : Int?,
    val title : String?,
    val author : String?,
    val content : String?,
    val description : String?,
    val publishedAt : String?,
    val source : Source?,
    val url : String?,
    val urlToImage : String?,
    val isStarred : Boolean = false
)
