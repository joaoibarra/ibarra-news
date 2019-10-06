package com.ibarra.news.ui.article.paging

import androidx.paging.PagedList
import com.ibarra.news.data.db.entity.Article
import com.ibarra.news.ui.article.ArticleViewModel

class ArticleBoundaryCallback (
    private val viewModel: ArticleViewModel
) : PagedList.BoundaryCallback<Article>() {
    override fun onZeroItemsLoaded() {
        viewModel.endList()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        viewModel.loadArticles()
    }
}