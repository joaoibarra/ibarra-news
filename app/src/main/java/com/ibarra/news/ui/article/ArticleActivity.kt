package com.ibarra.news.ui.article

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ibarra.news.R
import com.ibarra.news.databinding.ActivityArticleBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source_news)

        val source : String? = intent.getStringExtra("SOURCE")
        val articleViewModel : ArticleViewModel = getViewModel()


        val binding: ActivityArticleBinding = DataBindingUtil.setContentView(this@ArticleActivity, R.layout.activity_article)
        binding.viewModel = articleViewModel
        binding.lifecycleOwner = this
        articleViewModel.sourceId.observe(this, Observer { value ->
            articleViewModel.loadArticles()
        })
        articleViewModel.sourceId.postValue(source)

    }
}