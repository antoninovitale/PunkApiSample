package antoninovitale.punkapi.app.details

import antoninovitale.punkapi.app.api.model.Beer
import antoninovitale.punkapi.app.details.section.model.HeaderSectionModel
import antoninovitale.punkapi.app.details.section.model.IngredientSectionModel
import antoninovitale.punkapi.app.details.section.model.MethodSectionModel
import antoninovitale.punkapi.app.details.section.model.Status

/**
 * Created by antoninovitale on 01/09/2017.
 */
internal interface BeerDetailsInteractor {
    fun setupBeer(beer: Beer, onSectionSetupListener: OnSectionSetupListener)
    fun checkIngredientStatus(position: Int, onStatusCheckListener: OnStatusCheckListener)
    fun checkMethodStatus(position: Int, onStatusCheckListener: OnStatusCheckListener)
    fun setMethodDone(position: Int, onStatusCheckListener: OnStatusCheckListener)
    fun setMethodTimeElapsed(position: Int, millis: Long)

    interface OnStatusCheckListener {
        fun onMaltDone(positionInSection: Int, globalPosition: Int)
        fun onHopDone(positionInSection: Int, globalPosition: Int)
        fun onMethodDone(positionInSection: Int, globalPosition: Int)
        fun onMethodStatusChanged(positionInSection: Int, globalPosition: Int, status: Status)
    }

    interface OnSectionSetupListener {
        fun onHeaderSet(model: HeaderSectionModel)
        fun onMaltsSet(malts: List<IngredientSectionModel>)
        fun onHopsSet(hops: List<IngredientSectionModel>)
        fun onMethodsSet(methods: List<MethodSectionModel>)
    }
}