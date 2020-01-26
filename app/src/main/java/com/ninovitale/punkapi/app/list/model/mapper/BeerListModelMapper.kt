package com.ninovitale.punkapi.app.list.model.mapper

import com.ninovitale.punkapi.app.api.model.Beer
import com.ninovitale.punkapi.app.list.model.Attribute
import com.ninovitale.punkapi.app.list.model.BeerListModel
import com.ninovitale.punkapi.app.list.model.IBeerListModel
import com.ninovitale.punkapi.app.util.Utils
import java.util.ArrayList

/**
 * Created by antoninovitale on 31/08/2017.
 */
object BeerListModelMapper {
    @JvmStatic
    fun convertBeers(beers: List<Beer?>?): List<IBeerListModel?> {
        var models: List<IBeerListModel?> = emptyList()
        if (beers != null && beers.isNotEmpty()) {
            models = ArrayList(beers.size)
            beers.mapNotNull { beer -> convertBeer(beer).also { models.add(it) } }
        }
        return models
    }

    private fun convertBeer(beer: Beer?): IBeerListModel? {
        return if (beer != null) BeerListModel(beer.name, beer.tagline,
                Utils.formatPercentage(beer.abv), beer.imageUrl,
                Attribute.getAttribute(beer.abv, beer.ibu)) else null
    }
}