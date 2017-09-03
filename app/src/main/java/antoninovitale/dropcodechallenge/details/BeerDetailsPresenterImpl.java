package antoninovitale.dropcodechallenge.details;

import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.details.section.model.HeaderSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.IngredientSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.MethodSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.Status;

/**
 * Created by antoninovitale on 31/08/2017.
 */
public class BeerDetailsPresenterImpl implements BeerDetailsPresenter, BeerDetailsInteractor
        .OnStatusCheckListener, BeerDetailsInteractor.OnSectionSetupListener {
    private final BeerDetailsInteractor interactor;

    private BeerDetailsView view;

    public BeerDetailsPresenterImpl() {
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
    public void onMethodStatusClick(int position) {
        interactor.checkMethodStatus(position, this);
    }

    @Override
    public void onMethodEnd(int position) {
        interactor.setMethodDone(position, this);
    }

    @Override
    public void onMethodTimeElapsed(int position, long millis) {
        interactor.setMethodTimeElapsed(position, millis);
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
    public void onMethodDone(int position) {
        view.notifyMethodStatusChanged(position, Status.DONE);
    }

    @Override
    public void onMethodStatusChanged(int position, Status status) {
        view.notifyMethodStatusChanged(position, status);
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