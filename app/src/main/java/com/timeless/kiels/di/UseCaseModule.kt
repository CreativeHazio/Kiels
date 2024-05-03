package com.timeless.kiels.di

import com.timeless.kiels.data.local.ArticleDAO
import com.timeless.kiels.domain.repository.ArticleRepository
import com.timeless.kiels.domain.usecases.DeleteStarredArticle
import com.timeless.kiels.domain.usecases.GetArticlesHeadline
import com.timeless.kiels.domain.usecases.GetArticlesUseCase
import com.timeless.kiels.domain.usecases.GetLatestArticles
import com.timeless.kiels.domain.usecases.GetStarredArticles
import com.timeless.kiels.domain.usecases.SaveStarredArticle
import com.timeless.kiels.domain.usecases.StarredArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesGetArticlesUseCase(articleRepository : ArticleRepository) : GetArticlesUseCase {
        return GetArticlesUseCase(
            getArticlesHeadline = GetArticlesHeadline(articleRepository),
            getLatestArticles = GetLatestArticles(articleRepository)
        )
    }

    @Provides
    @Singleton
    fun providesStarredArticlesUseCase(articleDAO: ArticleDAO) : StarredArticlesUseCase {
        return StarredArticlesUseCase(
            saveStarredArticle = SaveStarredArticle(articleDAO),
            deleteStarredArticle = DeleteStarredArticle(articleDAO),
            getStarredArticles = GetStarredArticles(articleDAO)
        )
    }
}