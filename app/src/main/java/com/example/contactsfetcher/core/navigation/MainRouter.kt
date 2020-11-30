package com.example.contactsfetcher.core.navigation

import com.example.contactsfetcher.data.model.ContactItem

interface MainRouter {
    fun openContactsScreen()
    fun openContactDetailScreen(contactItem: ContactItem)
    fun goBack()
}