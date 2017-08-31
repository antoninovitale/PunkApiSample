package antoninovitale.dropcodechallenge.list.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.list.model.Attribute;
import antoninovitale.dropcodechallenge.list.model.BeerListModel;
import antoninovitale.dropcodechallenge.util.Utils;

/**
 * Created by antoninovitale on 31/08/2017.
 */
public class BeerListModelMapper {

    public static List<BeerListModel> convertBeers(List<Beer> beers) {
        List<BeerListModel> models = Collections.emptyList();
        if (beers != null && !beers.isEmpty()) {
            models = new ArrayList<>(beers.size());
            for (Beer beer : beers) {
                BeerListModel model = convertBeer(beer);
                if (model != null) {
                    models.add(model);
                }
            }
        }
        return models;
    }

    private static BeerListModel convertBeer(Beer beer) {
        return beer != null ? new BeerListModel(beer.getName(), beer.getTagline(), Utils
                .formatPercentage(beer.getAbv()), getAttribute(beer.getAbv(), beer.getIbu()),
                beer.getImageUrl()) : null;
    }

    private static Attribute getAttribute(double abv, double ibu) {
        Attribute attribute;
        if (abv > 10 && ibu > 90) {
            attribute = Attribute.STRONG_BITTER;
        } else if (abv > 10) {
            attribute = Attribute.STRONG;
        } else if (ibu > 90) {
            attribute = Attribute.BITTER;
        } else {
            attribute = Attribute.NONE;
        }
        return attribute;
    }

}