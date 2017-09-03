package antoninovitale.dropcodechallenge.viewmodel;

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
    private final MutableLiveData<CurrentStatus> currentStatus = new MutableLiveData<>();

    private final MutableLiveData<Beer> selectedBeer = new MutableLiveData<>();

    //TODO convert API models to UI models eventually
    private MutableLiveData<List<Beer>> beers;

    public LiveData<List<Beer>> getBeers() {
        if (beers == null) {
            beers = new MutableLiveData<>();
            loadBeers();
        }
        return beers;
    }

    public void loadBeers() {
        setCurrentStatus(true, false);
        //instead of getting a random beer I download the full list
        Call<List<Beer>> call = PunkService.getApiClient().getBeers(null, null);
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>>
                    response) {
                boolean error = false;
                if (response.isSuccessful() && response.body() != null) {
                    beers.setValue(response.body());
                } else {
                    DebugLog.log("loadBeers", "there was an error");
                    error = true;
                }
                setCurrentStatus(false, error);
            }

            @Override
            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                DebugLog.log("loadBeers", "" + t.getMessage(), t);
                setCurrentStatus(false, true);
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

    public void setSelectedBeer(Beer beer) {
        if (beer != null && selectedBeer.getValue() == null) {
            this.selectedBeer.setValue(beer);
        }
    }

    private void setCurrentStatus(boolean refreshing, boolean error) {
        this.currentStatus.setValue(new CurrentStatus(refreshing, error));
    }

    public MutableLiveData<CurrentStatus> getCurrentStatus() {
        return currentStatus;
    }

}