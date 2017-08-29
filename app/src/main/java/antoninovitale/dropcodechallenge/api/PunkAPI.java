package antoninovitale.dropcodechallenge.api;

import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by antoninovitale on 28/08/2017.
 */
public interface PunkAPI {
    String API_BASE_URL = "https://api.punkapi.com";

    @GET("v2/beers/random")
    Call<List<Beer>> getRandomBeer();

    @GET("v2/beers")
    Call<List<Beer>> getBeers(@Query("page") String page, @Query("per_page") String count);

}