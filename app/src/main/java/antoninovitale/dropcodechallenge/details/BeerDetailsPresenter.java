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

/**
 * Created by antoninovitale on 31/08/2017.
 */
class BeerDetailsPresenter implements BeerDetailsContract.Actions {
    private final BeerDetailsContract.View view;

    private List<IngredientSectionModel> malts;

    private List<IngredientSectionModel> hops;

    BeerDetailsPresenter(BeerDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void onChanged(Beer beer) {
        HeaderSectionModel headerSectionModel = new HeaderSectionModel(beer.getName(), beer
                .getAbv(), beer.getDescription());
        view.setupHeaderSection(headerSectionModel);
        Ingredients ingredients = beer.getIngredients();
        if (ingredients != null) {
            malts = IngredientSectionModelMapper.convertMalts(ingredients.getMalt());
            view.setupMaltsSection(malts);
            hops = IngredientSectionModelMapper.convertHops(ingredients.getHops());
            view.setupHopsSection(hops);
        }
        Method method = beer.getMethod();
        if (method != null) {
            List<MethodSectionModel> methods = MethodSectionModelMapper.convertMethods(method);
            view.setupMethodSection(methods);
        }
    }

    @Override
    public void onStatusClick(int position) {
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
                positionInSection = position - malts.size() - 2; //headers in both sections must be subtracted
                model = hops.get(positionInSection);
            }
            if (model != null && model.getStatus() != Status.DONE) {
                switch (model.getType()) {
                    case MALT:
                        model.setStatus(Status.DONE);
                        malts.set(positionInSection, model);
                        view.notifyMaltSection(positionInSection);
                        break;
                    case HOP:
                        checkHops(model, positionInSection);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void checkHops(IngredientSectionModel model, int positionInSection) {
        boolean canDo = true;
        switch (model.getAdd()) {
            case START:
                break;
            case MIDDLE:
                for (IngredientSectionModel hop : hops) {
                    if(hop.getAdd() == Add.START && hop.getStatus() != Status.DONE) {
                        canDo = false;
                        break;
                    }
                }
                break;
            case END:
                for (IngredientSectionModel hop : hops) {
                    if(hop.getAdd() != Add.END && hop.getStatus() != Status.DONE) {
                        canDo = false;
                        break;
                    }
                }
                break;
        }
        if(canDo) {
            model.setStatus(Status.DONE);
            hops.set(positionInSection, model);
            view.notifyHopSection(positionInSection);
        }
    }

}