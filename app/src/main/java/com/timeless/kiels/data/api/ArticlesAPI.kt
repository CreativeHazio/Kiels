package com.timeless.kiels.data.api

import com.timeless.kiels.BuildConfig
import com.timeless.kiels.util.Constants.ARTICLES_API_BASE_URL
import com.timeless.kiels.util.Constants.ARTICLES_DOMAINS
import com.timeless.kiels.util.Constants.ARTICLES_PAGE_SIZE
import com.timeless.kiels.util.Constants.ARTICLES_SOURCES
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ArticlesAPI {

    @GET("top-headlines")
    suspend fun getArticleHeadlinesFromNewsApi(
        @Query("q")
        keyword: String = "technology",
        @Query("page")
        pageNumber : Int,
        @Query("sources")
        sources : String = ARTICLES_SOURCES,
        @Query("apiKey")
        apiKey : String = BuildConfig.NEWS_API_KEY
    ) : Response<ArticlesResponse>

    @GET("everything")
    suspend fun getLatestArticlesFromNewsApi(
        @Query("q")
        keyword: String,
        @Query("domains")
        domains : String = ARTICLES_DOMAINS,
        @Query("pageSize")
        pageSize : Int = ARTICLES_PAGE_SIZE,
        @Query("page")
        pageNumber : Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.NEWS_API_KEY
    ) : Response<ArticlesResponse>

    companion object {

        fun create(): ArticlesAPI {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(ARTICLES_API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticlesAPI::class.java)
        }
    }

}