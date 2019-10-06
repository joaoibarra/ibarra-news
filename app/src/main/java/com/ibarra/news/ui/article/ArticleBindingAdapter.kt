package com.ibarra.news.ui.article

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.ibarra.news.data.db.entity.Article
import com.ibarra.news.data.db.entity.Source

@BindingAdapter(value = ["articles", "viewModel"])
fun setArticles(view: RecyclerView, items: PagedList<Article>?, vm: ArticleViewModel) {
    view.adapter?.run {
        if (this is ArticleAdapter) this.submitList(items)
    } ?: run {
        ArticleAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}