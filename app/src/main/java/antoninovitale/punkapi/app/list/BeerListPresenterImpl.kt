package antoninovitale.punkapi.app.list

import antoninovitale.punkapi.app.api.model.Beer
import antoninovitale.punkapi.app.list.model.mapper.BeerListModelMapper
import antoninovitale.punkapi.app.viewmodel.CurrentStatus

/**
 * Created by antoninovitale on 31/08/2017.
 */
class BeerListPresenterImpl constructor(private val view: BeerListView) : BeerListPresenter {
    override fun onRefresh() {
        view.refreshList()
    }

    override fun onChanged(beers: List<Beer?>?) {
        view.setItems(BeerListModelMapper.convertBeers(beers))
    }

    override fun onChanged(currentStatus: CurrentStatus?) {
        if (currentStatus != null) {
            view.setRefreshing(currentStatus.isRefreshing)
            if (currentStatus.isError) {
                view.showError()
            }
        }
    }

    override fun onListItemClick(position: Int) {
        view.selectItem(position)
        view.navigateToDetails()
    }
}