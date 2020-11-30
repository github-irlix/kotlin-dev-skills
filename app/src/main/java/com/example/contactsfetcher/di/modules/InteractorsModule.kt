package com.example.contactsfetcher.di.modules

import com.example.contactsfetcher.domain.interactor.GetContactsInteractor
import org.koin.dsl.module

val interactorsModule = module {
    factory { GetContactsInteractor(get()) }
}