package antoninovitale.punkapi.app.api

import antoninovitale.punkapi.app.BuildConfig
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import se.ansman.kotshi.KotshiJsonAdapterFactory
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Created by a.vitale on 29/08/2017.
 */
object PunkService {
    private const val TIMEOUT = 60
    private var punkAPI: PunkAPI? = null
    @JvmStatic
    val apiClient: PunkAPI?
        get() {
            if (punkAPI == null) {
                initApiClient()
            }
            return punkAPI
        }

    @Synchronized
    private fun initApiClient() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) BODY else NONE
        val client = Builder()
                .connectTimeout(TIMEOUT.toLong(), SECONDS)
                .readTimeout(TIMEOUT.toLong(), SECONDS)
                .writeTimeout(TIMEOUT.toLong(), SECONDS)
                .addInterceptor(interceptor).build()
        val moshi = Moshi.Builder()
                .add(ApplicationJsonAdapterFactory.INSTANCE)
                .build()
        punkAPI = Retrofit.Builder()
                .baseUrl(PunkAPI.API_BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build().create(PunkAPI::class.java)
    }
}

@KotshiJsonAdapterFactory
abstract class ApplicationJsonAdapterFactory : JsonAdapter.Factory {
    companion object {
        val INSTANCE: ApplicationJsonAdapterFactory = KotshiApplicationJsonAdapterFactory
    }
}