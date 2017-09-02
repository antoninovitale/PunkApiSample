package antoninovitale.dropcodechallenge.list;

import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.viewmodel.CurrentStatus;

/**
 * Created by antoninovitale on 02/09/2017.
 */
public interface BeerListPresenter {
    void onFloatingButtonClick();

    void onRefresh();

    void onChanged(List<Beer> beers);

    void onListItemClick(int position);

    void onChanged(CurrentStatus currentStatus);
}