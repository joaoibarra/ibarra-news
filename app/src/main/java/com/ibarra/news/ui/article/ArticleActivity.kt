package com.ibarra.news.ui.article

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.ibarra.news.R
import com.ibarra.news.databinding.ActivityArticleBinding
import kotlinx.android.synthetic.main.activity_article.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source_news)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val articleViewModel : ArticleViewModel = getViewModel()

        val binding: ActivityArticleBinding = DataBindingUtil.setContentView(this@ArticleActivity, R.layout.activity_article)
        binding.viewModel = articleViewModel
        binding.lifecycleOwner = this

        articleViewModel.pageToStart.observe(this, Observer { value ->
            openNewsAtBrowser(value)
        })
        articleViewModel.endOfList.observe(this, Observer { value ->
            if(value) {
                Snackbar.make(container, R.string.no_more_data, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    private fun openNewsAtBrowser(url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}