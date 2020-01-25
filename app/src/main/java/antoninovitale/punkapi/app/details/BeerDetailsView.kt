package antoninovitale.punkapi.app.details

import antoninovitale.punkapi.app.details.section.model.HeaderSectionModel
import antoninovitale.punkapi.app.details.section.model.IngredientSectionModel
import antoninovitale.punkapi.app.details.section.model.MethodSectionModel
import antoninovitale.punkapi.app.details.section.model.Status

/**
 * Created by antoninovitale on 02/09/2017.
 */
interface BeerDetailsView {
    fun setupHeaderSection(headerSectionModel: HeaderSectionModel)
    fun setupMaltsSection(malts: List<IngredientSectionModel>)
    fun setupHopsSection(hops: List<IngredientSectionModel>)
    fun setupMethodSection(methods: List<MethodSectionModel>)
    fun notifyMaltDone(positionInSection: Int, globalPosition: Int)
    fun notifyHopDone(positionInSection: Int, globalPosition: Int)
    fun notifyMethodStatusChanged(positionInSection: Int, globalPosition: Int, status: Status)
    fun clearSections()
}