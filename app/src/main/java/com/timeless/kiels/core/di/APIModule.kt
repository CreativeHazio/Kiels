package com.timeless.kiels.core.di

import com.timeless.kiels.data.local.article.ArticleDatabase
import com.timeless.kiels.data.remote.ArticlesAPI
import com.timeless.kiels.data.repository.ArticleRepositoryImpl
import com.timeless.kiels.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {

    @Provides
    @Singleton
    fun providesArticlesAPI() : ArticlesAPI {
        return ArticlesAPI.create()
    }

    @Provides
    @Singleton
    fun providesArticleRepository(
        articlesAPI: ArticlesAPI,
        articlesDatabase: ArticleDatabase
    ) : ArticleRepository {
        return ArticleRepositoryImpl(articlesAPI,  articlesDatabase)
    }
}