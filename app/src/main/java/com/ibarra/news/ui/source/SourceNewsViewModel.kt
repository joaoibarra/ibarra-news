package com.ibarra.news.ui.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ibarra.news.data.db.dao.SourceDao
import com.ibarra.news.data.db.entity.Source
import com.ibarra.news.data.remote.IbarraNewsAPi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SourceNewsViewModel (private val api: IbarraNewsAPi, private val dao: SourceDao) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    val items: LiveData<PagedList<Source>> = LivePagedListBuilder(dao.findAll(),  20).build()

    init {
        loadSources()
    }

    private fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    private fun loadSources() {
        addToDisposable(api.getSources()
            .subscribeOn(Schedulers.io())
            .subscribe({
                //dao.insertAll(Source.toList(it.sources))
            }, {

            })

        )
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}