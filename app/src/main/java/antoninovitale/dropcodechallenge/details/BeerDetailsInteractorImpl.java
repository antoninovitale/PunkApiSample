package antoninovitale.dropcodechallenge.details;

import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.api.model.Ingredients;
import antoninovitale.dropcodechallenge.api.model.Method;
import antoninovitale.dropcodechallenge.details.section.model.Add;
import antoninovitale.dropcodechallenge.details.section.model.HeaderSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.IngredientSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.MethodSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.Status;
import antoninovitale.dropcodechallenge.details.section.model.mapper.IngredientSectionModelMapper;
import antoninovitale.dropcodechallenge.details.section.model.mapper.MethodSectionModelMapper;
import antoninovitale.dropcodechallenge.util.Utils;

/**
 * Created by antoninovitale on 01/09/2017.
 */
class BeerDetailsInteractorImpl implements BeerDetailsInteractor {

    private HeaderSectionModel headerSectionModel;

    private List<IngredientSectionModel> malts;

    private List<IngredientSectionModel> hops;

    private List<MethodSectionModel> methods;

    @Override
    public void setupBeer(Beer beer, OnSectionSetupListener onSectionSetupListener) {
        boolean needUpdate = false;
        if (headerSectionModel == null || !beer.getName().equalsIgnoreCase(headerSectionModel
                .getName())) {
            needUpdate = true;
        }

        if (needUpdate) {
            headerSectionModel = new HeaderSectionModel(beer.getName(),
                    Utils.formatPercentage(beer.getAbv()), beer.getDescription());
            Ingredients ingredients = beer.getIngredients();
            if (ingredients != null) {
                malts = IngredientSectionModelMapper.convertMalts(ingredients.getMalt());
                hops = IngredientSectionModelMapper.convertHops(ingredients.getHops());
            }
            Method method = beer.getMethod();
            if (method != null) {
                methods = MethodSectionModelMapper.convertMethods(method);
            }
        }

        onSectionSetupListener.onHeaderSet(headerSectionModel);
        onSectionSetupListener.onMaltsSet(malts);
        onSectionSetupListener.onHopsSet(hops);
        onSectionSetupListener.onMethodsSet(methods);
    }

    @Override
    public void checkIngredientStatus(int position, OnStatusCheckListener onStatusCheckListener) {
        int positionInSection = 0;
        if (position > 0) {
            position--; //Header section counts as 1
            IngredientSectionModel model = null;
            if (malts.isEmpty()) {
                positionInSection = position - 1; //header in section must be subtracted
                model = hops.get(positionInSection);
            } else if (position <= malts.size()) {
                positionInSection = position - 1; //header in section must be subtracted
                model = malts.get(positionInSection);
            } else if (!hops.isEmpty()) {
                positionInSection = position - malts.size() - 2; //headers in both sections must
                // be subtracted
                model = hops.get(positionInSection);
            }
            if (model != null && model.getStatus() != Status.DONE) {
                switch (model.getType()) {
                    case MALT:
                        model.setStatus(Status.DONE);
                        malts.set(positionInSection, model);
                        onStatusCheckListener.onMaltDone(positionInSection);
                        break;
                    case HOP:
                        if (checkHops(hops, model, positionInSection)) {
                            onStatusCheckListener.onHopDone(positionInSection);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private boolean checkHops(List<IngredientSectionModel> hops, IngredientSectionModel model,
                              int positionInSection) {
        boolean canDo = true;
        switch (model.getAdd()) {
            case START:
                break;
            case MIDDLE:
                for (IngredientSectionModel hop : hops) {
                    if (hop.getAdd() == Add.START && hop.getStatus() != Status.DONE) {
                        canDo = false;
                        break;
                    }
                }
                break;
            case END:
                for (IngredientSectionModel hop : hops) {
                    if (hop.getAdd() != Add.END && hop.getStatus() != Status.DONE) {
                        canDo = false;
                        break;
                    }
                }
                break;
        }
        if (canDo) {
            model.setStatus(Status.DONE);
            hops.set(positionInSection, model);
        }
        return canDo;
    }

}