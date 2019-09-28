package com.ibarra.news.ui.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ibarra.news.data.db.dao.SourceDao
import com.ibarra.news.data.db.entity.Source
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class SourceNewsViewModel (private val dao: SourceDao) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    val items: LiveData<PagedList<Source>> = LivePagedListBuilder(dao.findAll(),  20).build()

    fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}