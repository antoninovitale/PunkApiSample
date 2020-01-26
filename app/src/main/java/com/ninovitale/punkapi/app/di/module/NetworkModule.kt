package com.ninovitale.punkapi.app.di.module

import com.ninovitale.punkapi.app.api.PunkAPI
import com.ninovitale.punkapi.app.api.PunkService
import com.ninovitale.punkapi.app.di.subcomponent.NetworkSubComponent
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import se.ansman.kotshi.KotshiJsonAdapterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(subcomponents = [NetworkSubComponent::class])
object NetworkModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun providePunkService(
            @Named("base_url") baseUrl: String,
            client: OkHttpClient,
            moshi: Moshi
    ): PunkService {
        return PunkService(baseUrl, moshi, client)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun providePunkApi(punkService: PunkService): PunkAPI = punkService.createApi()

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Named("base_url")
    @JvmStatic
    fun provideApiEnvironment(): String {
        return PunkAPI.API_BASE_URL
    }
}

@KotshiJsonAdapterFactory
abstract class ApplicationJsonAdapterFactory : JsonAdapter.Factory {
    companion object {
        val INSTANCE: ApplicationJsonAdapterFactory = KotshiApplicationJsonAdapterFactory
    }
}