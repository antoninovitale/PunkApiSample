package com.ninovitale.punkapi.app.list

import com.ninovitale.punkapi.app.api.model.Beer
import com.ninovitale.punkapi.app.list.model.mapper.BeerListModelMapper
import com.ninovitale.punkapi.app.viewmodel.CurrentStatus
import javax.inject.Inject

/**
 * Created by antoninovitale on 31/08/2017.
 */
class BeerListPresenterImpl @Inject constructor() : BeerListPresenter {
    private var view: BeerListView? = null

    override fun setView(view: BeerListView) {
        this.view = view
    }

    override fun onRefresh() {
        view?.refreshList()
    }

    override fun onChanged(beers: List<Beer?>?) {
        view?.setItems(BeerListModelMapper.convertBeers(beers))
    }

    override fun onChanged(currentStatus: CurrentStatus?) {
        if (currentStatus != null) {
            view?.setRefreshing(currentStatus.isRefreshing)
            if (currentStatus.isError) {
                view?.showError()
            }
        }
    }

    override fun onListItemClick(position: Int) {
        view?.selectItem(position)
        view?.navigateToDetails()
    }

    override fun dispose() {
        view = null
    }
}