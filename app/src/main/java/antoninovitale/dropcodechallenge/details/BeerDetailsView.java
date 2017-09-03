package antoninovitale.dropcodechallenge.details;

import java.util.List;

import antoninovitale.dropcodechallenge.details.section.model.HeaderSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.IngredientSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.MethodSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.Status;

/**
 * Created by antoninovitale on 02/09/2017.
 */
interface BeerDetailsView {
    void setupHeaderSection(HeaderSectionModel headerSectionModel);

    void setupMaltsSection(List<IngredientSectionModel> malts);

    void setupHopsSection(List<IngredientSectionModel> hops);

    void setupMethodSection(List<MethodSectionModel> methods);

    void notifyMaltDone(int position);

    void notifyHopDone(int position);

    void notifyMethodStatusChanged(int position, Status status);
}