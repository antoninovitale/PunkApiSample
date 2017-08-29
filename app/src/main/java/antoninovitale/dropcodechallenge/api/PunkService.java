package antoninovitale.dropcodechallenge.api;

import java.util.concurrent.TimeUnit;

import antoninovitale.dropcodechallenge.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by a.vitale on 29/08/2017.
 */
public class PunkService {

    private static final int TIMEOUT = 60;

    private static PunkAPI punkAPI = null;

    public static PunkAPI getApiClient() {
        if (punkAPI == null) {
            initApiClient();
        }
        return punkAPI;
    }

    private static synchronized void initApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();

        punkAPI = new Retrofit.Builder()
                .baseUrl(PunkAPI.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PunkAPI.class);
    }

}