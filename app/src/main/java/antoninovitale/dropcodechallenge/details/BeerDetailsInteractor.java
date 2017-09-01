package antoninovitale.dropcodechallenge.details;

import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.details.section.model.HeaderSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.IngredientSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.MethodSectionModel;

/**
 * Created by antoninovitale on 01/09/2017.
 */
public interface BeerDetailsInteractor {
    interface OnStatusCheckListener {
        void onMaltDone(int position);

        void onHopDone(int position);
    }

    interface OnSectionSetupListener {
        void onHeaderSet(HeaderSectionModel model);

        void onMaltsSet(List<IngredientSectionModel> malts);

        void onHopsSet(List<IngredientSectionModel> hops);

        void onMethodsSet(List<MethodSectionModel> methods);
    }

    void setupBeer(Beer beer, OnSectionSetupListener onSectionSetupListener);

    void checkIngredientStatus(int position, OnStatusCheckListener onStatusCheckListener);

}