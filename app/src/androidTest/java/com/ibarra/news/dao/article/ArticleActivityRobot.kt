package com.ibarra.news.dao.article

import android.content.Intent
import androidx.test.rule.ActivityTestRule
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions
import br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewInteractions
import com.ibarra.news.R
import com.ibarra.news.extensions.loadResponse
import com.ibarra.news.ui.article.ArticleActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

fun arrange(rule: ActivityTestRule<ArticleActivity>, func: ArticleActivityArrangeRobot.() -> Unit) = ArticleActivityArrangeRobot(
    rule
).apply { func() }
fun act(func: ArticleActivityActRobot.() -> Unit) = ArticleActivityActRobot().apply { func() }
fun assert(func: ArticleActivityAssertRobot.() -> Unit) = ArticleActivityAssertRobot().apply { func() }

class ArticleActivityArrangeRobot(private val rule: ActivityTestRule<ArticleActivity>) {

    private val articleResponse by lazy { "article.json".loadResponse() }

    fun launchActivity() {
        rule.launchActivity(Intent())
    }

    fun mockWebServer(mockWebServer: MockWebServer) {
        val dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when {
                    request.path.orEmpty().contains("everything") -> {
                        MockResponse().setResponseCode(200).setBody(articleResponse)
                    }
                    else -> MockResponse().setResponseCode(404)
                }
            }
        }

        mockWebServer.dispatcher = dispatcher
    }
}

class ArticleActivityActRobot {
    fun clickArticleItemByPosition(position: Int) {
        RecyclerViewInteractions.recyclerView(R.id.rv_articles) {
            atPosition(position) {
                click()
            }
        }
    }
}

class ArticleActivityAssertRobot {
    fun isArticleListVisible() {
        VisibilityAssertions.displayed {
            id(R.id.rv_articles)
        }
    }

    fun verifyItemByPosition(position: Int = 0) {
        RecyclerViewInteractions.recyclerView(R.id.rv_articles) {
            atPosition(position) {
                displayed {
                    id(R.id.article_name)
                    id(R.id.article_author)
                    id(R.id.article_description)
                }
            }
        }
    }
}