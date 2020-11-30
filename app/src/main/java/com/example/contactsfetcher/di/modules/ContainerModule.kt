package com.example.contactsfetcher.di.modules

import com.example.contactsfetcher.presentation.container.ContainerActivity
import com.example.contactsfetcher.presentation.container.ContainerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val containerModule = module {
    scope<ContainerActivity> {
        viewModel {
            ContainerViewModel(
                navigatorHolder = get(),
                router = get()
            )
        }
    }
}