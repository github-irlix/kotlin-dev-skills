package com.example.contactsfetcher.presentation.contacts

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.example.contactsfetcher.R
import com.example.contactsfetcher.core.BaseViewModel
import com.example.contactsfetcher.core.extensions.async
import com.example.contactsfetcher.core.navigation.MainRouter
import com.example.contactsfetcher.data.model.ContactItem
import com.example.contactsfetcher.domain.interactor.GetContactsInteractor
import io.reactivex.rxjava3.functions.Consumer

class ContactsViewModel(
    private val router: MainRouter,
    private val getContactsInteractor: GetContactsInteractor
) : BaseViewModel() {

    val checkPermissionsEvents by lazy { MutableLiveData<Unit>() }
    val contactItemsEvents by lazy { MutableLiveData<List<ContactItem>>() }

    val loaderVisibilityEvents by lazy { MutableLiveData<Boolean>() }
    val showErrorEvents by lazy { MutableLiveData<@StringRes Int>() }

    fun onViewCreated() {
        checkPermissionsEvents.value = Unit
    }

    fun onContactItemClicked(contactItem: ContactItem) {
        router.openContactDetailScreen(contactItem)
    }

    fun onPermissionGranted() {
        fetchContacts()
    }

    fun onPermissionDenied() {
        showErrorEvents.value = R.string.permission_denied
    }

    private fun fetchContacts() {
        loaderVisibilityEvents.value = true
        getContactsInteractor().async()
            .doFinally { loaderVisibilityEvents.value = false }
            .subscribe(Consumer {
                contactItemsEvents.value = it
            })
            .unsubscribeOnDestroy()
    }
}