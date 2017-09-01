package antoninovitale.dropcodechallenge.list;

import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.list.model.mapper.BeerListModelMapper;
import antoninovitale.dropcodechallenge.viewmodel.CurrentStatus;

/**
 * Created by antoninovitale on 31/08/2017.
 */
class BeerListPresenter implements BeerListContract.Actions {
    private BeerListContract.View view;

    BeerListPresenter(BeerListContract.View view) {
        this.view = view;
    }

    @Override
    public void onFloatingButtonClick() {
        view.refreshList();
    }

    @Override
    public void onRefresh() {
        view.refreshList();
    }

    @Override
    public void onChanged(List<Beer> beers) {
        view.setItems(BeerListModelMapper.convertBeers(beers));
    }

    @Override
    public void onChanged(CurrentStatus currentStatus) {
        if(currentStatus != null) {
            view.setRefreshing(currentStatus.isRefreshing());
            if(currentStatus.isError()) {
                view.showError();
            }
        }
    }

    @Override
    public void onListItemClick(int position) {
        view.selectItem(position);
        view.navigateToDetails();
    }

}