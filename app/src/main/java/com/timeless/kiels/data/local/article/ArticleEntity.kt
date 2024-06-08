package com.timeless.kiels.data.local.article

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles_table")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = false)
    val title : String,
    val author : String,
    val content : String,
    val description : String,
    val publishedAt : String,
    val source : SourceEntity,
    val url : String,
    val urlToImage : String
)

@Entity
data class SourceEntity(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val name : String
)
