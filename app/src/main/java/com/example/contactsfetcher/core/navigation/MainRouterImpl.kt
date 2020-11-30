package com.example.contactsfetcher.core.navigation

import com.example.contactsfetcher.data.model.ContactItem
import com.github.terrakok.cicerone.Router

class MainRouterImpl : Router(), MainRouter {

    override fun openContactsScreen() {
        navigateTo(Screens.ContactsScreen)
    }

    override fun openContactDetailScreen(contactItem: ContactItem) {
        navigateTo(Screens.ContactDetailScreen(contactItem))
    }

    override fun goBack() = exit()
}