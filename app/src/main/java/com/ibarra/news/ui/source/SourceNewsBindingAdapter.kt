package com.ibarra.news.ui.source

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.ibarra.news.data.db.entity.Source
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ibarra.news.R


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

@BindingAdapter("sourceImage")
fun loadImage(view: ImageView, imageUrl: Int) {
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions().circleCrop())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}