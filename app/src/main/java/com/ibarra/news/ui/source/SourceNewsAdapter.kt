package com.ibarra.news.ui.source

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ibarra.news.R
import com.ibarra.news.data.db.entity.Source
import com.ibarra.news.databinding.ItemSourceBinding
import com.ibarra.news.ui.BindingViewHolder

class SourceNewsAdapter (private val viewModel: SourceNewsViewModel) :
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
        getItem(position)?.run {
            holder.binding.item = this
            holder.binding.viewModel = viewModel
        }
    }

    class SourceViewHolder(view: View) : BindingViewHolder<ItemSourceBinding>(view)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Source>() {
            override fun areItemsTheSame(oldItem: Source, newItem: Source) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Source, newItem: Source) = oldItem == newItem
        }
    }
}