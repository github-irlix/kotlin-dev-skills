package com.example.contactsfetcher.di.modules

import com.example.contactsfetcher.data.model.ContactItem
import com.example.contactsfetcher.presentation.contactdetail.ContactDetailFragment
import com.example.contactsfetcher.presentation.contactdetail.ContactDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactDetailModule = module {
    scope<ContactDetailFragment> {
        viewModel { (contactItem: ContactItem) ->
            ContactDetailViewModel(contactItem)
        }
    }
}