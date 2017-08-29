package antoninovitale.dropcodechallenge.list.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import antoninovitale.dropcodechallenge.api.PunkService;
import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.util.DebugLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antoninovitale on 28/08/2017.
 */
public class BeerProvider extends ViewModel {
    private MutableLiveData<List<Beer>> beers;

    private final MutableLiveData<Boolean> refreshing = new MutableLiveData<>();

    private final MutableLiveData<Beer> selectedBeer = new MutableLiveData<>();

    public LiveData<List<Beer>> getBeers() {
        if (beers == null) {
            beers = new MutableLiveData<>();
            loadBeers();
        }
        return beers;
    }

    public void loadBeers() {
        setRefreshing(true);
        Call<List<Beer>> call = PunkService.getApiClient().getBeers(null, null);
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                    beers.setValue(response.body());
                } else {
                    DebugLog.log("loadBeers", "there was an error");
                }
                setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                DebugLog.log("loadBeers", "" + t.getMessage(), t);
                setRefreshing(false);
            }
        });
    }

    public void setSelectedBeer(int position) {
        List<Beer> beersValue = this.beers.getValue();
        if (beersValue != null && position < beersValue.size()) {
            selectedBeer.setValue(beersValue.get(position));
        }
    }

    public MutableLiveData<Beer> getSelectedBeer() {
        return selectedBeer;
    }

    private void setRefreshing(boolean refreshing) {
        this.refreshing.setValue(refreshing);
    }

    public MutableLiveData<Boolean> getRefreshing() {
        return refreshing;
    }

}