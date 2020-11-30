package com.example.contactsfetcher.presentation.container

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsfetcher.R
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.androidx.scope.lifecycleScope

class ContainerActivity : AppCompatActivity(R.layout.activity_container) {

    private val viewModel by lifecycleScope.inject<ContainerViewModel>()

    private val navigator by lazy { AppNavigator(this, R.id.screenContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume(navigator)
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }
}