package com.ninovitale.punkapi.app.di.subcomponent

import com.ninovitale.punkapi.app.di.module.BeerListModule
import com.ninovitale.punkapi.app.di.module.ListScope
import com.ninovitale.punkapi.app.list.BeerListActivity
import dagger.Subcomponent

@ListScope
@Subcomponent(modules = [BeerListModule::class])
interface BeerListSubComponent {
    fun inject(activity: BeerListActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): BeerListSubComponent
    }
}