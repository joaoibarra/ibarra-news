package com.ibarra.news.ui.article

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ibarra.news.data.db.dao.ArticleDao
import com.ibarra.news.data.db.entity.Article
import com.ibarra.news.data.remote.IbarraNewsAPi
import com.ibarra.news.ui.article.paging.ArticleBoundaryCallback
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ArticleViewModel (private val api: IbarraNewsAPi, private val dao: ArticleDao) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()

//    val items: LiveData<PagedList<Article>> = LivePagedListBuilder(dao.findAll(),  20).build()
    var items: LiveData<PagedList<Article>>
    val pageToStart = MutableLiveData<String>()
    val sourceId = MutableLiveData<String>()
    private var page = 1

    init {
        val factory: DataSource.Factory<Int, Article> = dao.findAll()

        val pagedListBuilder: LivePagedListBuilder<Int, Article>  = LivePagedListBuilder<Int, Article>(factory,
            20)
        items = pagedListBuilder
            .setBoundaryCallback(ArticleBoundaryCallback(this))
            .build()

    }

    private fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun loadArticles() {
        val lol = sourceId.getValue().toString()
        if(lol != null) {
            addToDisposable(
                api.getEverything(lol, page)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        dao.insertAll(Article.toList(it.articles))
                        page++
                    }, {

                    })
            )
        }
    }

    fun endList() {

    }

    fun onClick(url: String) {
        pageToStart.postValue(url)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}