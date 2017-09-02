package antoninovitale.dropcodechallenge.details;

import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.details.section.model.HeaderSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.IngredientSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.MethodSectionModel;

/**
 * Created by antoninovitale on 31/08/2017.
 */
class BeerDetailsPresenterImpl implements BeerDetailsPresenter, BeerDetailsInteractor
        .OnStatusCheckListener, BeerDetailsInteractor.OnSectionSetupListener {
    private BeerDetailsView view;

    private final BeerDetailsInteractor interactor;

    BeerDetailsPresenterImpl(BeerDetailsView view) {
        this.view = view;
        this.interactor = new BeerDetailsInteractorImpl();
    }

    @Override
    public void setView(BeerDetailsView view) {
        this.view = view;
    }

    @Override
    public void onChanged(Beer beer) {
        interactor.setupBeer(beer, this);
    }

    @Override
    public void onStatusClick(int position) {
        interactor.checkIngredientStatus(position, this);
    }


    @Override
    public void onMaltDone(int position) {
        view.notifyMaltDone(position);
    }

    @Override
    public void onHopDone(int position) {
        view.notifyHopDone(position);
    }

    @Override
    public void onHeaderSet(HeaderSectionModel model) {
        view.setupHeaderSection(model);
    }

    @Override
    public void onMaltsSet(List<IngredientSectionModel> malts) {
        view.setupMaltsSection(malts);
    }

    @Override
    public void onHopsSet(List<IngredientSectionModel> hops) {
        view.setupHopsSection(hops);
    }

    @Override
    public void onMethodsSet(List<MethodSectionModel> methods) {
        view.setupMethodSection(methods);
    }

}