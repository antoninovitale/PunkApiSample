package com.ninovitale.punkapi.app.details

import com.ninovitale.punkapi.app.api.model.Beer

/**
 * Created by antoninovitale on 02/09/2017.
 */
interface BeerDetailsPresenter {
    fun setView(view: BeerDetailsView?)
    fun onChanged(beer: Beer)
    fun onStatusClick(position: Int)
    fun onMethodStatusClick(position: Int)
    fun onMethodEnd(position: Int)
    fun onMethodTimeElapsed(position: Int, millis: Long)
}