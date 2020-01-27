package com.ninovitale.punkapi.app.di.subcomponent

import com.ninovitale.punkapi.app.details.BeerDetailsActivity
import com.ninovitale.punkapi.app.details.BeerDetailsFragment
import com.ninovitale.punkapi.app.di.module.BeerDetailsModule
import com.ninovitale.punkapi.app.di.module.DetailsScope
import dagger.Subcomponent

@DetailsScope
@Subcomponent(modules = [BeerDetailsModule::class])
interface BeerDetailsSubComponent {
    fun inject(details: BeerDetailsActivity)
    fun inject(details: BeerDetailsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): BeerDetailsSubComponent
    }
}