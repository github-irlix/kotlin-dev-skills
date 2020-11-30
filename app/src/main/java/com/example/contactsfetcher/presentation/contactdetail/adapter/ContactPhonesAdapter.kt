package com.example.contactsfetcher.presentation.contactdetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsfetcher.R
import com.example.contactsfetcher.core.BaseAdapter
import kotlinx.android.synthetic.main.item_contact_phone.view.*

class ContactPhonesAdapter(
    items: MutableList<String> = mutableListOf(),
) : BaseAdapter<ContactPhonesAdapter.ContactPhoneViewHolder, String>(items) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactPhoneViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact_phone, parent, false)
        return ContactPhoneViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactPhoneViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ContactPhoneViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(phone: String) {
            itemView.tvContactPhone.text = phone
        }
    }

    override fun getDiffUtilsCallback(
        oldItems: List<String>,
        newItems: List<String>
    ): DiffUtil.Callback {
        return object : DiffUtil.Callback() {

            override fun getOldListSize() = oldItems.size

            override fun getNewListSize() = newItems.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItems[oldItemPosition] == newItems[newItemPosition]
            }
        }
    }
}