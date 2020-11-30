package com.example.contactsfetcher.di.modules

import com.example.contactsfetcher.core.navigation.MainRouter
import com.example.contactsfetcher.core.navigation.MainRouterImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module

val navigationModule = module {
    single<MainRouter> { MainRouterImpl() }
    single { get<MainRouter>() as Router }
    single { Cicerone.create(customRouter = get<Router>()).getNavigatorHolder() }
}