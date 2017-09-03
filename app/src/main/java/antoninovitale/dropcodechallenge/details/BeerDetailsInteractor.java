package antoninovitale.dropcodechallenge.details;

import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.details.section.model.HeaderSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.IngredientSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.MethodSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.Status;

/**
 * Created by antoninovitale on 01/09/2017.
 */
interface BeerDetailsInteractor {
    interface OnStatusCheckListener {

        void onMaltDone(int position);

        void onHopDone(int position);

        void onMethodDone(int position);

        void onMethodStatusChanged(int position, Status status);

    }

    interface OnSectionSetupListener {

        void onHeaderSet(HeaderSectionModel model);

        void onMaltsSet(List<IngredientSectionModel> malts);

        void onHopsSet(List<IngredientSectionModel> hops);

        void onMethodsSet(List<MethodSectionModel> methods);

    }

    void setupBeer(Beer beer, OnSectionSetupListener onSectionSetupListener);

    void checkIngredientStatus(int position, OnStatusCheckListener onStatusCheckListener);

    void checkMethodStatus(int position, OnStatusCheckListener onStatusCheckListener);

    void setMethodDone(int position, OnStatusCheckListener onStatusCheckListener);

    void setMethodTimeElapsed(int position, long millis);

}