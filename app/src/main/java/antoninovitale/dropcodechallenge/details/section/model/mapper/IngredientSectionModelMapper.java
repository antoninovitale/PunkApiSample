package antoninovitale.dropcodechallenge.details.section.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Amount;
import antoninovitale.dropcodechallenge.api.model.Hop;
import antoninovitale.dropcodechallenge.api.model.Malt;
import antoninovitale.dropcodechallenge.details.section.model.Add;
import antoninovitale.dropcodechallenge.details.section.model.IngredientSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.IngredientType;
import antoninovitale.dropcodechallenge.details.section.model.Status;

/**
 * Created by antoninovitale on 29/08/2017.
 */
public class IngredientSectionModelMapper {

    private static Comparator<IngredientSectionModel> ingredientSectionModelComparator = new
            Comparator<IngredientSectionModel>() {

                @Override
                public int compare(IngredientSectionModel model1, IngredientSectionModel model2) {
                    if (model1.getAdd() == model2.getAdd()) {
                        return 0;
                    }
                    switch (model1.getAdd()) {
                        case START:
                            return -1;
                        case MIDDLE: {
                            if (model2.getAdd() == Add.START) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                        case END:
                            return 1;
                    }
                    return 0;
                }

            };

    private static IngredientSectionModel convertHop(Hop hop) {
        IngredientSectionModel model = null;
        if (hop != null) {
            model = new IngredientSectionModel(hop.getName(), convertAmount(hop.getAmount()),
                    IngredientType.HOP, Status.IDLE, Add.getAdd(hop.getAdd()));
        }
        return model;
    }

    private static IngredientSectionModel convertMalt(Malt malt) {
        IngredientSectionModel model = null;
        if (malt != null) {
            model = new IngredientSectionModel(malt.getName(), convertAmount(malt.getAmount()),
                    IngredientType.MALT, Status.IDLE, Add.NONE);
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
            if (!models.isEmpty()) {
                Collections.sort(models, ingredientSectionModelComparator);
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
            if (!models.isEmpty()) {
                Collections.sort(models, ingredientSectionModelComparator);
            }
        }
        return models;
    }

}