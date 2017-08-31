package antoninovitale.dropcodechallenge.list;

import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.list.model.BeerListModel;

/**
 * Created by antoninovitale on 31/08/2017.
 */
interface BeerListContract {
    interface View {
        void setRefreshing(boolean value);

        void refreshList();

        void setItems(List<BeerListModel> models);

        void selectItem(int position);

        void navigateToDetails();
    }

    interface Actions {
        void onFloatingButtonClick();

        void onRefresh();

        void onChanged(List<Beer> beers);

        void onChanged(Boolean value);

        void onListItemClick(int position);
    }

}