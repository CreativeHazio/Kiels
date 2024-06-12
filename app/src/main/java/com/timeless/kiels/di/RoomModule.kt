package com.timeless.kiels.di

import android.content.Context
import androidx.room.Room
import com.timeless.kiels.data.local.article.ArticleDAO
import com.timeless.kiels.data.local.article.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesArticleDatabase(@ApplicationContext context: Context) : ArticleDatabase {
        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "Article_Database"
        ).addMigrations(ArticleDatabase.MIGRATION_1_2, ArticleDatabase.MIGRATION_3_4).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesArticleDAO(articleDatabase: ArticleDatabase) : ArticleDAO {
        return articleDatabase.articleDAO
    }
}