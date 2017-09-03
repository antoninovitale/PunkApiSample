package antoninovitale.dropcodechallenge.details;

import android.arch.lifecycle.ViewModel;

/**
 * Created by antoninovitale on 02/09/2017.
 */
class BeerDetailsViewModel extends ViewModel {
    private BeerDetailsPresenter presenter;

    BeerDetailsPresenter getPresenter() {
        return presenter;
    }

    void setPresenter(BeerDetailsPresenter presenter) {
        if (this.presenter == null) {
            this.presenter = presenter;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        presenter = null;
    }

}