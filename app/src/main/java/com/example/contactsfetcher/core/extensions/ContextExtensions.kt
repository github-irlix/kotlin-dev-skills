package com.example.contactsfetcher.core.extensions

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.example.contactsfetcher.R

fun Context.alert(
    @StringRes titleResId: Int,
    @StringRes messageResId: Int,
    @StringRes positiveActionText: Int = R.string.ok,
    positiveAction: (dialog: DialogInterface) -> Unit = { it.dismiss() }
) {
    AlertDialog.Builder(this)
        .setTitle(titleResId)
        .setMessage(messageResId)
        .setPositiveButton(positiveActionText) { dialog, _ ->
            positiveAction(dialog)
        }
        .create()
        .show()
}