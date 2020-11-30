package com.example.contactsfetcher.di.modules

import com.example.contactsfetcher.presentation.contacts.ContactsFragment
import com.example.contactsfetcher.presentation.contacts.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactsModule = module {
    scope<ContactsFragment> {
        viewModel {
            ContactsViewModel(router = get(), getContactsInteractor = get())
        }
    }
}