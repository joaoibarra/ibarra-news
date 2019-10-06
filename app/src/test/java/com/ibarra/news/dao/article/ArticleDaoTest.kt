package com.ibarra.news.dao.article

import android.content.Context
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import com.ibarra.news.data.db.AppDatabase
import com.ibarra.news.data.db.dao.ArticleDao
import com.ibarra.news.data.db.dao.SourceDao
import com.ibarra.news.data.db.entity.Article
import com.ibarra.news.data.db.entity.Source
import com.ibarra.news.di.RoomTestModule
import com.ibarra.news.dao.source.sourceRepository
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1], manifest=Config.NONE)
class ArticleDaoTest: KoinTest {
    private val appDatabase: AppDatabase by inject()
    private val articleDao: ArticleDao by inject()
    private val sourceDao: SourceDao by inject()

    @Before
    fun before() {
        val app: Context = InstrumentationRegistry.getInstrumentation().context
        startKoin {
            androidContext(app)
            modules(listOf(RoomTestModule))
        }
    }

    @After
    fun after() {
        appDatabase.close()
        stopKoin()
    }

    @Test
    fun testInsertArticle() {
        val source = Source to sourceRepository
        val article = Article to articleRepository

        sourceDao.insert(source)
        articleDao.insert(article)

        val requestSource = sourceDao.findById(sourceRepository.idName)
        val requestArticle = articleDao.findByUrl(articleRepository.url)

        Assert.assertEquals(source, requestSource)
        Assert.assertEquals(article, requestArticle)
    }
}