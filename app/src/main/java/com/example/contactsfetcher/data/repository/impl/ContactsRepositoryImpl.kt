package com.example.contactsfetcher.data.repository.impl

import android.content.ContentResolver
import android.provider.ContactsContract
import com.example.contactsfetcher.data.model.ContactItem
import com.example.contactsfetcher.data.repository.api.ContactsRepository
import io.reactivex.rxjava3.core.Single

private const val EMPTINESS = ""

class ContactsRepositoryImpl(
    private val contentResolver: ContentResolver
) : ContactsRepository {

    override fun getContacts(): Single<List<ContactItem>> {
        return Single.fromCallable { fetchContacts() }
    }

    private fun fetchContacts(): MutableList<ContactItem> {
        val contacts = mutableListOf<ContactItem>()
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        )
        if (cursor?.moveToFirst() == true) {
            do {
                val contactId =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val phonesCursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null,
                    null
                )

                val phoneNumbers = mutableListOf<String>()

                if (phonesCursor?.moveToFirst() == true) {
                    do {
                        val number: String =
                            phonesCursor.getString(phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        phoneNumbers.add(number)
                    } while (phonesCursor.moveToNext())
                }

                val contact = ContactItem(
                    avatarUrl = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                        ?: EMPTINESS,
                    name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    phoneNumbers = phoneNumbers
                )
                contacts.add(contact)
                phonesCursor?.close()
            } while (cursor.moveToNext())
        }

        cursor?.close()
        return contacts
    }
}