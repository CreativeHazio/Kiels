package com.timeless.kiels.di

import com.timeless.kiels.data.api.ArticlesAPI
import com.timeless.kiels.data.local.ArticleDAO
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
        articlesDAO: ArticleDAO
    ) : ArticleRepository {
        return ArticleRepositoryImpl(articlesAPI,  articlesDAO)
    }
}