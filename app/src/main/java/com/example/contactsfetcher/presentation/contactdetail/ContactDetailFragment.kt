package com.example.contactsfetcher.presentation.contactdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.contactsfetcher.R
import com.example.contactsfetcher.core.argumentNotNull
import com.example.contactsfetcher.data.model.ContactItem
import com.example.contactsfetcher.presentation.contactdetail.adapter.ContactPhonesAdapter
import kotlinx.android.synthetic.main.fragment_contact_detail.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.core.parameter.parametersOf

class ContactDetailFragment : Fragment(R.layout.fragment_contact_detail) {

    companion object {
        fun newInstance(contactItem: ContactItem): ContactDetailFragment {
            return ContactDetailFragment().apply {
                this.contactItem = contactItem
            }
        }
    }

    private var contactItem by argumentNotNull<ContactItem>()
    private val adapter by lazy { ContactPhonesAdapter() }

    private val viewModel by lifecycleScope.inject<ContactDetailViewModel> {
        parametersOf(contactItem)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()

        initViews()
        observe()
    }

    private fun initViews() {
        rvPhones.adapter = adapter
    }

    private fun observe() {
        viewModel.contactPhones.observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load(it.avatarUrl)
                .placeholder(R.drawable.ic_contact_placeholder)
                .into(imgContactAvatar)
            tvContactName.text = it.name
            adapter.updateItems(it.phoneNumbers)
        }
    }
}