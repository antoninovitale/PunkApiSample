package antoninovitale.dropcodechallenge.details.section.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Amount;
import antoninovitale.dropcodechallenge.api.model.Hop;
import antoninovitale.dropcodechallenge.api.model.Malt;
import antoninovitale.dropcodechallenge.details.section.model.IngredientSectionModel;

/**
 * Created by antoninovitale on 29/08/2017.
 */
public class IngredientSectionModelMapper {

    private static IngredientSectionModel convertHop(Hop hop) {
        IngredientSectionModel model = null;
        if (hop != null) {
            model = new IngredientSectionModel(hop.getName(), convertAmount(hop.getAmount()), hop
                    .getAdd());
        }
        return model;
    }

    private static IngredientSectionModel convertMalt(Malt malt) {
        IngredientSectionModel model = null;
        if (malt != null) {
            model = new IngredientSectionModel(malt.getName(), convertAmount(malt.getAmount()),
                    null);
        }
        return model;
    }

    private static String convertAmount(Amount amount) {
        return amount != null ? String.format("%s %s", amount.getValue(), amount.getUnit()) : null;
    }

    public static List<IngredientSectionModel> convertHops(List<Hop> hops) {
        List<IngredientSectionModel> models = Collections.emptyList();
        if (hops != null && !hops.isEmpty()) {
            models = new ArrayList<>(hops.size());
            for (Hop hop : hops) {
                IngredientSectionModel model = convertHop(hop);
                if (model != null) {
                    models.add(model);
                }
            }
        }
        return models;
    }

    public static List<IngredientSectionModel> convertMalts(List<Malt> malts) {
        List<IngredientSectionModel> models = Collections.emptyList();
        if (malts != null && !malts.isEmpty()) {
            models = new ArrayList<>(malts.size());
            for (Malt malt : malts) {
                IngredientSectionModel model = convertMalt(malt);
                if (model != null) {
                    models.add(model);
                }
            }
        }
        return models;
    }

}