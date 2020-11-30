package com.example.contactsfetcher.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.contactsfetcher.core.extensions.put
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> argumentNotNull(): ReadWriteProperty<Fragment, T> = FragmentArgumentDelegate()

private class FragmentArgumentDelegate<T> : ReadWriteProperty<Fragment, T> {
    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val key = property.name
        return thisRef.arguments
            ?.get(key) as? T
            ?: throw IllegalStateException("Property ${property.name} could not be read")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val args = thisRef.arguments ?: Bundle().also(thisRef::setArguments)
        val key = property.name
        args.put(key, value)
    }
}