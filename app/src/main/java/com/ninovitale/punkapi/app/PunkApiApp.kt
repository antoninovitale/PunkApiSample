package com.ninovitale.punkapi.app

import android.app.Application
import com.ninovitale.punkapi.app.di.ApplicationComponent
import com.ninovitale.punkapi.app.di.DaggerApplicationComponent
import com.ninovitale.punkapi.app.di.subcomponent.BeerDetailsSubComponent
import com.ninovitale.punkapi.app.di.subcomponent.BeerListSubComponent
import com.ninovitale.punkapi.app.di.subcomponent.NetworkSubComponent

class PunkApiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        BaseApplication.init(this)
    }
}

class BaseApplication private constructor(builder: Builder) : BaseProvider {
    private var applicationComponent: ApplicationComponent

    init {
        applicationComponent = DaggerApplicationComponent
                .factory()
                .create(builder.application)
    }

    private class Builder(val application: Application) {
        @Synchronized
        fun build(): BaseApplication {
            if (instance == null) {
                instance = BaseApplication(this)
            }
            return instance as BaseApplication
        }
    }

    companion object {
        var instance: BaseApplication? = null
            private set

        fun init(application: Application) {
            Builder(application).build()
        }
    }

    override fun provideNetworkSubComponent(): NetworkSubComponent {
        return applicationComponent.networkSubComponentFactory.create()
    }

    override fun provideBeerListSubComponent(): BeerListSubComponent {
        return applicationComponent.beerListSubComponentFactory.create()
    }

    override fun provideBeerDetailsSubComponent(): BeerDetailsSubComponent {
        return applicationComponent.beerDetailsSubComponentFactory.create()
    }
}

interface BaseProvider {
    fun provideNetworkSubComponent(): NetworkSubComponent
    fun provideBeerListSubComponent(): BeerListSubComponent
    fun provideBeerDetailsSubComponent(): BeerDetailsSubComponent
}

interface BaseApplicationProvider {
    val baseProvider: BaseProvider
        get() = BaseApplication.instance as BaseProvider
}