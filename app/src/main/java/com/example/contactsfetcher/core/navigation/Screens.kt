package com.example.contactsfetcher.core.navigation

import com.example.contactsfetcher.data.model.ContactItem
import com.example.contactsfetcher.presentation.contactdetail.ContactDetailFragment
import com.example.contactsfetcher.presentation.contacts.ContactsFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    object ContactsScreen : FragmentScreen(
        screenKey = "screen_key:contacts",
        createFragment = { ContactsFragment.newInstance() }
    )

    class ContactDetailScreen(contactItem: ContactItem) : FragmentScreen(
        screenKey = "screen_key:contact_detail",
        createFragment = { ContactDetailFragment.newInstance(contactItem) }
    )
}