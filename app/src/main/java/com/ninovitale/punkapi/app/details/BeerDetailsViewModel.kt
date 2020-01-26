package com.ninovitale.punkapi.app.details

import androidx.lifecycle.ViewModel

/**
 * Created by antoninovitale on 02/09/2017.
 */
class BeerDetailsViewModel : ViewModel() {
    private var presenter: BeerDetailsPresenter? = null
    fun getPresenter(): BeerDetailsPresenter? {
        return presenter
    }

    fun setPresenter(presenter: BeerDetailsPresenter?) {
        if (this.presenter == null) {
            this.presenter = presenter
        }
    }

    override fun onCleared() {
        super.onCleared()
        presenter = null
    }
}