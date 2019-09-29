package com.ibarra.news.ui.source

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ibarra.news.R
import com.ibarra.news.databinding.ActivitySourceNewsBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SourceNewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source_news)

        val binding: ActivitySourceNewsBinding = DataBindingUtil.setContentView(this@SourceNewsActivity, R.layout.activity_source_news)
        binding.viewModel = getViewModel()
        binding.lifecycleOwner = this
    }
}
