package com.ibarra.news.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

class ArticleViewModel (private val api: IbarraNewsAPi, private val dao: ArticleDao, private val source: String) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    var items: LiveData<PagedList<Article>>
    val pageToStart = MutableLiveData<String>()
    val endOfList = MutableLiveData<Boolean>()

    private var page = 1

    init {
        val factory: DataSource.Factory<Int, Article> = dao.findBySourceId(source)

        val pagedListBuilder: LivePagedListBuilder<Int, Article>  = LivePagedListBuilder<Int, Article>(factory,
            20)
        items = pagedListBuilder
            .setBoundaryCallback(ArticleBoundaryCallback(this))
            .build()
        loadArticles()
    }

    private fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun loadArticles() {
        addToDisposable(
            api.getEverything(source, page)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if(it.idName==null) {
                        it.idName = source
                    }
                    dao.insertAll(Article.toList(it.articles))
                    page++
                }, {

                })
        )
    }

    fun endList() {
        endOfList.postValue(true)
    }

    fun onClick(url: String) {
        pageToStart.postValue(url)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}