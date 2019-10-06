package com.ibarra.news.ui.source.paging

import androidx.paging.PagedList
import com.ibarra.news.data.db.entity.Source
import com.ibarra.news.ui.source.SourceNewsViewModel

class SourceBoundaryCallback (
    private val viewModel: SourceNewsViewModel
) : PagedList.BoundaryCallback<Source>() {
    override fun onZeroItemsLoaded() {
    }

    override fun onItemAtEndLoaded(itemAtEnd: Source) {
        viewModel.endList()
    }
}