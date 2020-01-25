package antoninovitale.punkapi.app.details

import antoninovitale.punkapi.app.api.model.Beer
import antoninovitale.punkapi.app.details.BeerDetailsInteractor.OnSectionSetupListener
import antoninovitale.punkapi.app.details.BeerDetailsInteractor.OnStatusCheckListener
import antoninovitale.punkapi.app.details.section.model.HeaderSectionModel
import antoninovitale.punkapi.app.details.section.model.IngredientSectionModel
import antoninovitale.punkapi.app.details.section.model.MethodSectionModel
import antoninovitale.punkapi.app.details.section.model.Status
import antoninovitale.punkapi.app.details.section.model.Status.DONE

/**
 * Created by antoninovitale on 31/08/2017.
 */
class BeerDetailsPresenterImpl : BeerDetailsPresenter, OnStatusCheckListener,
        OnSectionSetupListener {
    private val interactor: BeerDetailsInteractor
    private var view: BeerDetailsView? = null

    init {
        interactor = BeerDetailsInteractorImpl()
    }

    override fun setView(view: BeerDetailsView?) {
        this.view = view
    }

    override fun onChanged(beer: Beer) {
        view?.clearSections()
        interactor.setupBeer(beer, this)
    }

    override fun onStatusClick(position: Int) {
        interactor.checkIngredientStatus(position, this)
    }

    override fun onMethodStatusClick(position: Int) {
        interactor.checkMethodStatus(position, this)
    }

    override fun onMethodEnd(position: Int) {
        interactor.setMethodDone(position, this)
    }

    override fun onMethodTimeElapsed(position: Int, millis: Long) {
        interactor.setMethodTimeElapsed(position, millis)
    }

    override fun onMaltDone(positionInSection: Int, globalPosition: Int) {
        view?.notifyMaltDone(positionInSection, globalPosition)
    }

    override fun onHopDone(positionInSection: Int, globalPosition: Int) {
        view?.notifyHopDone(positionInSection, globalPosition)
    }

    override fun onMethodDone(positionInSection: Int, globalPosition: Int) {
        view?.notifyMethodStatusChanged(positionInSection, globalPosition, DONE)
    }

    override fun onMethodStatusChanged(positionInSection: Int, globalPosition: Int, status: Status) {
        view?.notifyMethodStatusChanged(positionInSection, globalPosition, status)
    }

    override fun onHeaderSet(model: HeaderSectionModel) {
        view?.setupHeaderSection(model)
    }

    override fun onMaltsSet(malts: List<IngredientSectionModel>) {
        view?.setupMaltsSection(malts)
    }

    override fun onHopsSet(hops: List<IngredientSectionModel>) {
        view?.setupHopsSection(hops)
    }

    override fun onMethodsSet(methods: List<MethodSectionModel>) {
        view?.setupMethodSection(methods)
    }
}