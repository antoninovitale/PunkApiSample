package com.ninovitale.punkapi.app.di.module

import androidx.lifecycle.ViewModelProvider
import com.ninovitale.punkapi.app.api.PunkAPI
import com.ninovitale.punkapi.app.viewmodel.BeerProviderFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ViewModelModule {
    @Provides
    @JvmStatic
    @Singleton
    fun provideViewModelFactory(punkAPI: PunkAPI): ViewModelProvider.Factory = BeerProviderFactory(
            punkAPI)
}