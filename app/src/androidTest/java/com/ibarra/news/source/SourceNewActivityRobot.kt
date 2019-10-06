package com.ibarra.news.source

import android.content.Intent
import androidx.test.rule.ActivityTestRule
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions
import br.com.concretesolutions.kappuccino.custom.intent.IntentMatcherInteractions
import br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewInteractions
import com.ibarra.news.R
import com.ibarra.news.extensions.loadResponse
import com.ibarra.news.ui.article.ArticleActivity
import com.ibarra.news.ui.source.SourceNewsActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

fun arrange(rule: ActivityTestRule<SourceNewsActivity>, func: SourceNewsActivityArrangeRobot.() -> Unit) = SourceNewsActivityArrangeRobot(
    rule
).apply { func() }
fun act(func: SourceNewsActivityActRobot.() -> Unit) = SourceNewsActivityActRobot().apply { func() }
fun assert(func: SourceNewsActivityAssertRobot.() -> Unit) = SourceNewsActivityAssertRobot().apply { func() }

class SourceNewsActivityArrangeRobot(private val rule: ActivityTestRule<SourceNewsActivity>) {

    private val sourceResponse by lazy { "source.json".loadResponse() }

    fun launchActivity() {
        rule.launchActivity(Intent())
    }

    fun mockWebServer(mockWebServer: MockWebServer) {
        val dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when {
                    request.path.orEmpty().contains("sources") -> {
                        MockResponse().setResponseCode(200).setBody(sourceResponse)
                    }
                    else -> MockResponse().setResponseCode(404)
                }
            }
        }

        mockWebServer.dispatcher = dispatcher
    }
}

class SourceNewsActivityActRobot {
    fun clickSourceItemByPosition(position: Int) {
        RecyclerViewInteractions.recyclerView(R.id.rv_sources) {
            atPosition(position) {
                click()
            }
        }
    }
}

class SourceNewsActivityAssertRobot {
    fun isSourceListVisible() {
        VisibilityAssertions.displayed {
            id(R.id.rv_sources)
        }
    }

    fun verifyItemByPosition(position: Int = 0) {
        RecyclerViewInteractions.recyclerView(R.id.rv_sources) {
            atPosition(position) {
                displayed {
                    id(R.id.source_name)
                    text("ABC News (AU)")
                    id(R.id.source_url)
                    text("http://www.abc.net.au/news")
                    id(R.id.source_description)
                }
            }
        }
    }

    fun redirectToArticles() {
        IntentMatcherInteractions.sentIntent {
            className(ArticleActivity::class.java.name)
        }
    }
}