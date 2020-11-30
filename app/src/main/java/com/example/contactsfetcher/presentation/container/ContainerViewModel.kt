package com.example.contactsfetcher.presentation.container

import androidx.lifecycle.ViewModel
import com.example.contactsfetcher.core.navigation.MainRouter
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder

class ContainerViewModel(
    private val navigatorHolder: NavigatorHolder,
    private val router: MainRouter
) : ViewModel() {

    fun onCreate() {
        router.openContactsScreen()
    }

    fun onResume(navigator: Navigator) {
        navigatorHolder.setNavigator(navigator)
    }

    fun onPause() {
        navigatorHolder.removeNavigator()
    }
}