package com.example.contactsfetcher.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactItem(
    val avatarUrl: String,
    val name: String,
    val phoneNumbers: List<String>
) : Parcelable