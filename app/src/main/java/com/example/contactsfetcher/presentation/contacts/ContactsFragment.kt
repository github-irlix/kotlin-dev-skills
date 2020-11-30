package com.example.contactsfetcher.presentation.contacts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.contactsfetcher.R
import com.example.contactsfetcher.core.extensions.alert
import com.example.contactsfetcher.presentation.contacts.adapter.ContactsAdapter
import kotlinx.android.synthetic.main.fragment_contacts.*
import org.koin.androidx.scope.lifecycleScope


class ContactsFragment : Fragment(R.layout.fragment_contacts) {

    companion object {
        private const val REQUEST_CODE_CONTACTS = 1

        fun newInstance(): ContactsFragment {
            return ContactsFragment()
        }
    }

    private val viewModel by lifecycleScope.inject<ContactsViewModel>()

    private val adapter: ContactsAdapter by lazy {
        ContactsAdapter(onContactItemClicked = viewModel::onContactItemClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()

        initViews()
        observe()
    }

    private fun initViews() {
        rvContacts.adapter = adapter
    }

    private fun observe() {
        viewModel.checkPermissionsEvents.observe(viewLifecycleOwner) {
            requestReadContactPermission()
        }

        viewModel.contactItemsEvents.observe(viewLifecycleOwner) {
            adapter.updateItems(it)
        }

        viewModel.loaderVisibilityEvents.observe(viewLifecycleOwner) {
            loader.isVisible = it
        }

        viewModel.showErrorEvents.observe(viewLifecycleOwner) {
            requireContext().alert(R.string.error, messageResId = it)
        }
    }

    private fun requestReadContactPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.READ_CONTACTS),
            REQUEST_CODE_CONTACTS
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_CONTACTS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.onPermissionGranted()
            } else {
                viewModel.onPermissionDenied()
            }
            return
        }
    }
}