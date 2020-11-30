package com.example.contactsfetcher.di.modules

import com.example.contactsfetcher.data.repository.api.ContactsRepository
import com.example.contactsfetcher.data.repository.impl.ContactsRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {
    factory<ContactsRepository> { ContactsRepositoryImpl(contentResolver = androidApplication().contentResolver) }
}