package com.ninovitale.punkapi.app.di.module

import com.ninovitale.punkapi.app.list.BeerListPresenter
import com.ninovitale.punkapi.app.list.BeerListPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ListScope

@Module
object BeerListModule {
    @Provides
    @JvmStatic
    fun provideBeerListPresenter(): BeerListPresenter = BeerListPresenterImpl()
}