package com.ibarra.news.ui.source

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.ibarra.news.data.db.entity.Source

@BindingAdapter(value = ["sources", "viewModel"])
fun setSources(view: RecyclerView, items: PagedList<Source>?, vm: SourceNewsViewModel) {
    view.adapter?.run {
        if (this is SourceNewsAdapter) this.submitList(items)
    } ?: run {
        SourceNewsAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}