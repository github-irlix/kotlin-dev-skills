package com.example.contactsfetcher.data.repository.api

import com.example.contactsfetcher.data.model.ContactItem
import io.reactivex.rxjava3.core.Single

interface ContactsRepository {
    fun getContacts(): Single<List<ContactItem>>
}