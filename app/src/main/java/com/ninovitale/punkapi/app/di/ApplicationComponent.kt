package com.ninovitale.punkapi.app.di

import android.app.Application
import com.ninovitale.punkapi.app.PunkApiApp
import com.ninovitale.punkapi.app.di.module.NetworkModule
import com.ninovitale.punkapi.app.di.module.ViewModelModule
import com.ninovitale.punkapi.app.di.subcomponent.NetworkSubComponent
import com.ninovitale.punkapi.app.di.subcomponent.ViewModelSubComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface ApplicationComponent : AndroidInjector<PunkApiApp> {
    val networkSubComponentFactory : NetworkSubComponent.Factory

    val viewModelSubComponentFactory: ViewModelSubComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}