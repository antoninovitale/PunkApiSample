package com.ninovitale.punkapi.app.di

import android.app.Application
import com.ninovitale.punkapi.app.PunkApiApp
import com.ninovitale.punkapi.app.di.module.BeerDetailsModule
import com.ninovitale.punkapi.app.di.module.BeerListModule
import com.ninovitale.punkapi.app.di.module.BeerProviderModule
import com.ninovitale.punkapi.app.di.module.NetworkModule
import com.ninovitale.punkapi.app.di.subcomponent.BeerDetailsSubComponent
import com.ninovitale.punkapi.app.di.subcomponent.BeerListSubComponent
import com.ninovitale.punkapi.app.di.subcomponent.NetworkSubComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
        modules = [NetworkModule::class, BeerProviderModule::class, BeerListModule::class, BeerDetailsModule::class])
interface ApplicationComponent : AndroidInjector<PunkApiApp> {
    val networkSubComponentFactory: NetworkSubComponent.Factory

    val beerListSubComponentFactory: BeerListSubComponent.Factory

    val beerDetailsSubComponentFactory: BeerDetailsSubComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}