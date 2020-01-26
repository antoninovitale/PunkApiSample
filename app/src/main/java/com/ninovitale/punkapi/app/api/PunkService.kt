package com.ninovitale.punkapi.app.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by a.vitale on 29/08/2017.
 */
class PunkService(baseUrl: String, moshi: Moshi, okHttpClient: OkHttpClient) {
    private val apiRetrofit: Retrofit

    init {
        val builder = Retrofit.Builder().client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
        apiRetrofit = builder.build()
    }

    fun createApi(): PunkAPI {
        return apiRetrofit.create(PunkAPI::class.java)
    }
}