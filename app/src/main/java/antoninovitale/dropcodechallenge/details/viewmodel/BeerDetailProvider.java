package antoninovitale.dropcodechallenge.details.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import antoninovitale.dropcodechallenge.api.model.Beer;

/**
 * Created by antoninovitale on 28/08/2017.
 */
public class BeerDetailProvider extends ViewModel {
    private final MutableLiveData<Beer> beer = new MutableLiveData<>();

    public MutableLiveData<Beer> getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        if (beer != null) {
            this.beer.setValue(beer);
        }
    }

}