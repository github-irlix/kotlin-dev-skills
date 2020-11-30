package com.example.contactsfetcher.presentation.contactdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactsfetcher.data.model.ContactItem

class ContactDetailViewModel(
    private val contactItem: ContactItem
) : ViewModel() {

    val contactPhones by lazy { MutableLiveData<ContactItem>() }

    fun onViewCreated() {
        contactPhones.value = contactItem
    }
}