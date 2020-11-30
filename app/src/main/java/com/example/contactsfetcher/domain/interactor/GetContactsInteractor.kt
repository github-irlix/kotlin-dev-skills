package com.example.contactsfetcher.domain.interactor

import com.example.contactsfetcher.data.repository.api.ContactsRepository

class GetContactsInteractor(private val repo: ContactsRepository) {
    operator fun invoke() = repo.getContacts()
}