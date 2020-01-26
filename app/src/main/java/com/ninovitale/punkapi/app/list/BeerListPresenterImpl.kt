package com.ninovitale.punkapi.app.list

import com.ninovitale.punkapi.app.api.model.Beer
import com.ninovitale.punkapi.app.list.model.mapper.BeerListModelMapper
import com.ninovitale.punkapi.app.viewmodel.CurrentStatus

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