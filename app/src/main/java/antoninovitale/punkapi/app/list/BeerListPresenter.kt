package antoninovitale.punkapi.app.list

import antoninovitale.punkapi.app.api.model.Beer
import antoninovitale.punkapi.app.viewmodel.CurrentStatus

/**
 * Created by antoninovitale on 02/09/2017.
 */
interface BeerListPresenter {
    fun onRefresh()
    fun onChanged(beers: List<Beer?>?)
    fun onListItemClick(position: Int)
    fun onChanged(currentStatus: CurrentStatus?)
}