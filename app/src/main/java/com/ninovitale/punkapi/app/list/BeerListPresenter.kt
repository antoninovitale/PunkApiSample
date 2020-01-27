package com.ninovitale.punkapi.app.list

import com.ninovitale.punkapi.app.api.model.Beer
import com.ninovitale.punkapi.app.viewmodel.CurrentStatus

/**
 * Created by antoninovitale on 02/09/2017.
 */
interface BeerListPresenter {
    fun setView(view: BeerListView)
    fun onRefresh()
    fun onChanged(beers: List<Beer?>?)
    fun onListItemClick(position: Int)
    fun onChanged(currentStatus: CurrentStatus?)
    fun dispose()
}