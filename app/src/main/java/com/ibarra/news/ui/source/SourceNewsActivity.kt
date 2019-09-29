package com.ibarra.news.ui.source

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ibarra.news.R
import com.ibarra.news.databinding.ActivitySourceNewsBinding
import com.ibarra.news.ui.article.ArticleActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SourceNewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source_news)

        val binding: ActivitySourceNewsBinding = DataBindingUtil.setContentView(this@SourceNewsActivity, R.layout.activity_source_news)
        val sourceViewModel : SourceNewsViewModel = getViewModel()
        binding.viewModel = sourceViewModel
        binding.lifecycleOwner = this

        sourceViewModel.activityToStart.observe(this, Observer { value ->
            val intent = Intent(this, ArticleActivity::class.java)
            val bundle = Bundle()
            bundle.putString("SOURCE", value)
            intent.putExtras(bundle)
            startActivity(intent)
        })
    }
}
