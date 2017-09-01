package antoninovitale.dropcodechallenge.list.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.list.model.Attribute;
import antoninovitale.dropcodechallenge.list.model.BeerListModel;
import antoninovitale.dropcodechallenge.list.model.IBeerListModel;
import antoninovitale.dropcodechallenge.util.Utils;

/**
 * Created by antoninovitale on 31/08/2017.
 */
public class BeerListModelMapper {

    public static List<IBeerListModel> convertBeers(List<Beer> beers) {
        List<IBeerListModel> models = Collections.emptyList();
        if (beers != null && !beers.isEmpty()) {
            models = new ArrayList<>(beers.size());
            for (Beer beer : beers) {
                IBeerListModel model = convertBeer(beer);
                if (model != null) {
                    models.add(model);
                }
            }
        }
        return models;
    }

    private static IBeerListModel convertBeer(Beer beer) {
        return beer != null ? new BeerListModel(beer.getName(), beer.getTagline(), Utils
                .formatPercentage(beer.getAbv()), Attribute.getAttribute(beer.getAbv(), beer
                .getIbu()), beer.getImageUrl()) : null;
    }

}