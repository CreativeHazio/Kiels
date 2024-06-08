package com.timeless.kiels.data.local.typeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.timeless.kiels.data.local.article.SourceEntity

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
}