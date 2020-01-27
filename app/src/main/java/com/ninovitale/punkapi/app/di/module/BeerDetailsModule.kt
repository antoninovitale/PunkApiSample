package com.ninovitale.punkapi.app.di.module

import com.ninovitale.punkapi.app.details.BeerDetailsInteractor
import com.ninovitale.punkapi.app.details.BeerDetailsInteractorImpl
import com.ninovitale.punkapi.app.details.BeerDetailsPresenter
import com.ninovitale.punkapi.app.details.BeerDetailsPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class DetailsScope

@Module
object BeerDetailsModule {
    @Provides
    @JvmStatic
    fun provideBeerDetailsInteractor(): BeerDetailsInteractor = BeerDetailsInteractorImpl()

    @Provides
    @JvmStatic
    fun provideBeerDetailsPresenter(
            interactor: BeerDetailsInteractor): BeerDetailsPresenter = BeerDetailsPresenterImpl(
            interactor)
}