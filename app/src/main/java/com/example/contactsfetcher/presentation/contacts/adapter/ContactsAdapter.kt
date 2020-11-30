package com.example.contactsfetcher.presentation.contacts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactsfetcher.R
import com.example.contactsfetcher.core.BaseAdapter
import com.example.contactsfetcher.data.model.ContactItem
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactsAdapter(
    items: MutableList<ContactItem> = mutableListOf(),
    private val onContactItemClicked: (item: ContactItem) -> Unit
) : BaseAdapter<ContactsAdapter.ContactsViewHolder, ContactItem>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactsViewHolder(view, onContactItemClicked)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ContactsViewHolder(
        itemView: View,
        private val onContactItemClicked: (item: ContactItem) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ContactItem) {
            with(itemView) {
                Glide.with(context)
                    .load(item.avatarUrl)
                    .placeholder(R.drawable.ic_contact_placeholder)
                    .into(imgContactAvatar)
                tvContactName.text = item.name
                tvContactMainPhone.text = item.phoneNumbers.firstOrNull()

                setOnClickListener { onContactItemClicked(item) }
            }
        }
    }

    override fun getDiffUtilsCallback(
        oldItems: List<ContactItem>,
        newItems: List<ContactItem>
    ): DiffUtil.Callback {
        return object : DiffUtil.Callback() {
            override fun getOldListSize() = oldItems.size

            override fun getNewListSize() = newItems.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItems[oldItemPosition] == newItems[newItemPosition]
            }

        }
    }
}