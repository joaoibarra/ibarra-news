package com.ibarra.news.article

import android.os.Build
import androidx.test.espresso.intent.Intents
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ibarra.news.ui.article.ArticleActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest

@LargeTest
@RunWith(JUnit4::class)
class ArticleActivityTest: KoinTest {
    @get:Rule
    var activityRule = ActivityTestRule(ArticleActivity::class.java, true, false)

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8000)
        getKoin().setProperty("SOURCE", "bbc-news")
        Intents.init()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        Intents.release()
    }

    @Test
    fun whenOpenScreenShouldDisplayArticleList() {
        arrange(activityRule) {
            mockWebServer(mockWebServer)
            launchActivity()
        }

        assert {
            isArticleListVisible()
        }
    }

    @Test
    fun whenOpenScreenShouldDisplaySpecificArticleItemList() {
        arrange(activityRule) {
            mockWebServer(mockWebServer)
            launchActivity()
        }

        assert {
            verifyItemByPosition(0)
        }
    }
}