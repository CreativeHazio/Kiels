package com.timeless.kiels.data.local.article

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ArticleDaoTest {

    private lateinit var articleDatabase: ArticleDatabase
    private lateinit var articleDAO: ArticleDAO

    @Before
    fun setup() {
        articleDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ArticleDatabase::class.java
        ).allowMainThreadQueries().build()
        articleDAO = articleDatabase.articleDAO
    }

    @After
    fun teardown() {
        articleDatabase.close()
    }

    @Test
    fun starArticle() = runTest {
        val starredSourceEntity = StarredSourceEntity("233FJJTHFK","Kiels")
        val starredArticleEntity = StarredArticleEntity(1, "Hazio's payday","Hazio",
            "Can't wait to be paid","Hazio's payday","20 Aug 2003",
            starredSourceEntity, "RemoteWork.com","Hazio's happy face",true)
        articleDAO.starArticle(starredArticleEntity)

        articleDAO.getArticles().collectLatest { list ->
            assertThat(list).contains(starredArticleEntity)
        }
    }

}