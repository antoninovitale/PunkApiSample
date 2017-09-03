package antoninovitale.dropcodechallenge.details;

import antoninovitale.dropcodechallenge.api.model.Beer;

/**
 * Created by antoninovitale on 02/09/2017.
 */
interface BeerDetailsPresenter {
    void setView(BeerDetailsView view);

    void onChanged(Beer beer);

    void onStatusClick(int position);

    void onMethodStatusClick(int position);

    void onMethodEnd(int position);

    void onMethodTimeElapsed(int position, long millis);

}