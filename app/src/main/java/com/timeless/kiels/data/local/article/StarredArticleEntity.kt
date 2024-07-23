package com.timeless.kiels.data.local.article

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "starred_articles_table")
data class StarredArticleEntity(
    @ColumnInfo("id")
    val id : Int,
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
    val source : StarredSourceEntity,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("url")
    val url : String,
    @ColumnInfo("urlToImage")
    val urlToImage : String,
    @ColumnInfo(name = "isStarred")
    val isStarred : Boolean,
    @ColumnInfo(name = "dateAdded", defaultValue = "23 Jul 2024, 21:21:57")
    val dateAdded : String = ""
)

@Entity(tableName = "starred_source_table")
data class StarredSourceEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id : String,
    @ColumnInfo("name")
    val name : String
)
