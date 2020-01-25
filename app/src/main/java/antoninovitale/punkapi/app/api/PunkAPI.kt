package antoninovitale.punkapi.app.api

import antoninovitale.punkapi.app.api.model.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by antoninovitale on 28/08/2017.
 */
interface PunkAPI {
    @get:GET("v2/beers/random")
    val randomBeer: Call<List<Beer?>?>?

    @GET("v2/beers")
    fun getBeers(
            @Query("page") page: String?,
            @Query("per_page") count: String?): Call<List<Beer?>?>?

    companion object {
        const val API_BASE_URL = "https://api.punkapi.com"
    }
}