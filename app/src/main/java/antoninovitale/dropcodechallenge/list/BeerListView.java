package antoninovitale.dropcodechallenge.list;

import java.util.List;

import antoninovitale.dropcodechallenge.list.model.IBeerListModel;

/**
 * Created by antoninovitale on 02/09/2017.
 */
public interface BeerListView {
    void setRefreshing(boolean value);

    void refreshList();

    void setItems(List<IBeerListModel> models);

    void selectItem(int position);

    void navigateToDetails();

    void showError();
}
