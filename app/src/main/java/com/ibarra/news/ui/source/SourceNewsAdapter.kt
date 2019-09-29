package com.ibarra.news.ui.source

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ibarra.news.BuildConfig
import com.ibarra.news.R
import com.ibarra.news.data.db.entity.Source
import com.ibarra.news.databinding.ItemSourceBinding
import com.ibarra.news.ui.BindingViewHolder

class SourceNewsAdapter (private val vm: SourceNewsViewModel) :
    PagedListAdapter<Source, SourceNewsAdapter.SourceViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        return SourceViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_source,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        getItem(position)?.let { source ->
            holder.binding?.apply{
                item = source
                viewModel = vm
                ivNewsImage.setImageResource(getRandomPlaceholder())
            }
        }
    }

    private fun getRandomPlaceholder(): Int{
        val list = listOf(R.drawable.ic_004_newspaper, R.drawable.ic_005_laptop, R.drawable.ic_012_tablet, R.drawable.ic_024_worldwide_1)
        return list.random()
    }

    class SourceViewHolder(view: View) : BindingViewHolder<ItemSourceBinding>(view)


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Source>() {
            override fun areItemsTheSame(oldItem: Source, newItem: Source) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Source, newItem: Source) = oldItem == newItem
        }
    }
}