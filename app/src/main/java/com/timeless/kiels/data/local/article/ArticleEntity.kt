package com.timeless.kiels.data.local.article

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles_table")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int?,
    @ColumnInfo("title")
    val title : String,
    @ColumnInfo("author")
    val author : String,
    @ColumnInfo("content")
    val content : String,
    @ColumnInfo("description")
    val description : String,
    @ColumnInfo("publishedAt")
    val publishedAt : String,
    @ColumnInfo("source")
    val source : SourceEntity,
    @ColumnInfo("url")
    val url : String,
    @ColumnInfo("urlToImage")
    val urlToImage : String,
    @ColumnInfo(name = "isStarred", defaultValue = "false")
    val isStarred : Boolean = false
)

@Entity
data class SourceEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id : String,
    @ColumnInfo("name")
    val name : String
)
