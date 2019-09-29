package com.ibarra.news.ui.article

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ibarra.news.BuildConfig
import com.ibarra.news.R
import com.ibarra.news.data.db.entity.Article
import com.ibarra.news.databinding.ItemArticleBinding
import com.ibarra.news.ui.BindingViewHolder

class ArticleAdapter (private val vm: ArticleViewModel) :
    PagedListAdapter<Article, ArticleAdapter.ArticleViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { article ->
            holder.binding?.apply{
                item = article
                viewModel = vm
                loadImage(ivNewsImage, article.urlToImage)
            }
        }
    }

    private fun loadImage(view: ImageView, url: String?){
        Glide.with(view.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }

    class ArticleViewHolder(view: View) : BindingViewHolder<ItemArticleBinding>(view)


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.url == newItem.url
            override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
        }
    }
}