package com.ibarra.news.ui.source

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.ibarra.news.R
import com.ibarra.news.databinding.ActivitySourceNewsBinding
import com.ibarra.news.ui.article.ArticleActivity
import kotlinx.android.synthetic.main.activity_source_news.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.KoinComponent

class SourceNewsActivity : AppCompatActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source_news)

        val binding: ActivitySourceNewsBinding = DataBindingUtil.setContentView(this@SourceNewsActivity, R.layout.activity_source_news)
        val sourceViewModel : SourceNewsViewModel = getViewModel()
        binding.viewModel = sourceViewModel
        binding.lifecycleOwner = this

        sourceViewModel.activityToStart.observe(this, Observer { value ->
            val intent = Intent(this, ArticleActivity::class.java)
            getKoin().setProperty(SourceNewsViewModel.SOURCE, value)
            startActivity(intent)
        })

        sourceViewModel.endOfList.observe(this, Observer { value ->
            if(value) {
                Snackbar.make(container, R.string.no_more_data, Snackbar.LENGTH_LONG).show()
            }
        })
    }
}
