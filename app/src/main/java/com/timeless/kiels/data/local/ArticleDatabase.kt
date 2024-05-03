package com.timeless.kiels.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.timeless.kiels.data.local.typeConverter.ArticleTypeConverter

@Database(
    entities = [ArticleEntity::class, SourceEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(ArticleTypeConverter::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract val articleDAO : ArticleDAO

}