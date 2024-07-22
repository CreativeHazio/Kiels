package com.timeless.kiels.data.local.typeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.timeless.kiels.data.local.article.SourceEntity
import com.timeless.kiels.data.local.article.StarredSourceEntity

class ArticleTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromSourceEntity(sourceEntity: SourceEntity) : String {
        return gson.toJson(sourceEntity)
    }

    @TypeConverter
    fun toSourceEntity(sourceJson : String) : SourceEntity {
        return gson.fromJson(sourceJson, SourceEntity::class.java)
    }

    @TypeConverter
    fun fromStarredSourceEntity(starredSourceEntity: StarredSourceEntity) : String {
        return gson.toJson(starredSourceEntity)
    }

    @TypeConverter
    fun toStarredSourceEntity(starredSourceJson : String) : StarredSourceEntity {
        return gson.fromJson(starredSourceJson, StarredSourceEntity::class.java)
    }
}