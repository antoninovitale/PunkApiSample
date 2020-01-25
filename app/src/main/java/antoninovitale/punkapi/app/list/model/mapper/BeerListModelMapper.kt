package antoninovitale.punkapi.app.list.model.mapper

import antoninovitale.punkapi.app.api.model.Beer
import antoninovitale.punkapi.app.list.model.Attribute
import antoninovitale.punkapi.app.list.model.BeerListModel
import antoninovitale.punkapi.app.list.model.IBeerListModel
import antoninovitale.punkapi.app.util.Utils
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