package com.timeless.kiels.core.di

import com.timeless.kiels.domain.repository.ArticleRepository
import com.timeless.kiels.domain.usecases.DeleteStarredArticle
import com.timeless.kiels.domain.usecases.ExploreArticles
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
            getLatestArticles = GetLatestArticles(articleRepository),
            exploreArticles = ExploreArticles(articleRepository)
        )
    }

    @Provides
    @Singleton
    fun providesStarredArticlesUseCase(articleRepository: ArticleRepository) : StarredArticlesUseCase {
        return StarredArticlesUseCase(
            saveStarredArticle = SaveStarredArticle(articleRepository),
            deleteStarredArticle = DeleteStarredArticle(articleRepository),
            getStarredArticles = GetStarredArticles(articleRepository)
        )
    }
}