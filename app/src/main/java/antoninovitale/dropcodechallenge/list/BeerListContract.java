package antoninovitale.dropcodechallenge.list;

import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.list.model.IBeerListModel;
import antoninovitale.dropcodechallenge.viewmodel.CurrentStatus;

/**
 * Created by antoninovitale on 31/08/2017.
 */
interface BeerListContract {
    interface View {
        void setRefreshing(boolean value);

        void refreshList();

        void setItems(List<IBeerListModel> models);

        void selectItem(int position);

        void navigateToDetails();

        void showError();
    }

    interface Actions {
        void onFloatingButtonClick();

        void onRefresh();

        void onChanged(List<Beer> beers);

        void onListItemClick(int position);

        void onChanged(CurrentStatus currentStatus);
    }

}