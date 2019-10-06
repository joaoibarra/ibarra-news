package com.ibarra.news.source

import androidx.test.espresso.intent.Intents
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ibarra.news.ui.source.SourceNewsActivity
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
class SourceNewsActivityTest: KoinTest {
    @get:Rule
    var activityRule = ActivityTestRule(SourceNewsActivity::class.java, true, false)

    private lateinit var mockWebServer: MockWebServer

    init {
//        loadKoinModules(listOf(NetworkModule, RoomModule, ViewModule))
    }

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8000)
//        val app: Context = InstrumentationRegistry.getInstrumentation().context

//        try{
//            startKoin {
//                androidContext(app)
//                modules(listOf(NetworkModule, RoomModule, ViewModule))
//            }
//
//        }catch (e: Exception) {
//
//        }
//        TestApp.startKoinInstance
//        startKoin {
//            androidContext(app)
//        }
        Intents.init()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        Intents.release()
//        stopKoin()
    }

    @Test
    fun whenOpenScreenShouldDisplaySourceList() {
        arrange(activityRule) {
            mockWebServer(mockWebServer)
            launchActivity()
        }

        assert {
            isSourceListVisible()
        }
    }

    @Test
    fun whenOpenScreenShouldDisplaySpecificSourceItemList() {
        arrange(activityRule) {
            mockWebServer(mockWebServer)
            launchActivity()
        }

        assert {
            verifyItemByPosition(1)
        }
    }

    @Test
    fun whenOpenScreenAndUserClickInItemShouldRedirectToArticles() {
        arrange(activityRule) {
            mockWebServer(mockWebServer)
            launchActivity()
        }

        act {
            clickSourceItemByPosition(2)
        }

        assert {
            redirectToArticles()
        }
    }
}