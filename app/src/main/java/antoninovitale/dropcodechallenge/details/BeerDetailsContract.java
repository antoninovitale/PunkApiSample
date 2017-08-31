package antoninovitale.dropcodechallenge.details;

import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.details.section.model.HeaderSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.IngredientSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.MethodSectionModel;

/**
 * Created by antoninovitale on 31/08/2017.
 */
public interface BeerDetailsContract {
    interface View {

        void setupHeaderSection(HeaderSectionModel headerSectionModel);

        void setupMaltsSection(List<IngredientSectionModel> malts);

        void setupHopsSection(List<IngredientSectionModel> hops);

        void setupMethodSection(List<MethodSectionModel> methods);

        void notifyMaltSection(int position);

        void notifyHopSection(int position);
    }

    interface Actions {

        void onChanged(Beer beer);

        void onStatusClick(int position);
    }

}