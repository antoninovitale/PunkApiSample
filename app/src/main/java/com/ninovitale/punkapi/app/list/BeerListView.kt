package com.ninovitale.punkapi.app.list

import com.ninovitale.punkapi.app.list.model.IBeerListModel

/**
 * Created by antoninovitale on 02/09/2017.
 */
interface BeerListView {
    fun setRefreshing(value: Boolean)
    fun refreshList()
    fun setItems(models: List<IBeerListModel?>?)
    fun selectItem(position: Int)
    fun navigateToDetails()
    fun showError()
}