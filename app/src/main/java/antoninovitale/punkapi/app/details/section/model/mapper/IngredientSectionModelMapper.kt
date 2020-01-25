package antoninovitale.punkapi.app.details.section.model.mapper

import antoninovitale.punkapi.app.api.model.Amount
import antoninovitale.punkapi.app.api.model.Hop
import antoninovitale.punkapi.app.api.model.Malt
import antoninovitale.punkapi.app.details.section.model.Add
import antoninovitale.punkapi.app.details.section.model.Add.END
import antoninovitale.punkapi.app.details.section.model.Add.MIDDLE
import antoninovitale.punkapi.app.details.section.model.Add.NONE
import antoninovitale.punkapi.app.details.section.model.Add.START
import antoninovitale.punkapi.app.details.section.model.IngredientSectionModel
import antoninovitale.punkapi.app.details.section.model.IngredientType.HOP
import antoninovitale.punkapi.app.details.section.model.IngredientType.MALT
import antoninovitale.punkapi.app.details.section.model.Status.IDLE
import java.util.Comparator

/**
 * Created by antoninovitale on 29/08/2017.
 */
object IngredientSectionModelMapper {
    private val ingredientSectionModelComparator = Comparator { model1: IngredientSectionModel, model2: IngredientSectionModel ->
        if (model1.add == model2.add) return@Comparator 0
        return@Comparator when (model1.add) {
            START -> -1
            MIDDLE -> if (model2.add == START) 1 else -1
            END -> 1
            NONE -> 0
        }
    }

    private fun convertHop(hop: Hop?): IngredientSectionModel? {
        var model: IngredientSectionModel? = null
        if (hop != null) {
            model = IngredientSectionModel(hop.name, convertAmount(hop.amount), HOP, IDLE,
                    Add.getAdd(hop.add))
        }
        return model
    }

    private fun convertMalt(malt: Malt?): IngredientSectionModel? {
        var model: IngredientSectionModel? = null
        if (malt != null) {
            model = IngredientSectionModel(malt.name, convertAmount(malt.amount), MALT, IDLE, NONE)
        }
        return model
    }

    private fun convertAmount(amount: Amount?): String? {
        return if (amount != null) String.format("%s %s", amount.value, amount.unit) else null
    }

    fun convertHops(hops: List<Hop?>?): List<IngredientSectionModel> {
        if (hops != null && hops.isNotEmpty()) {
            return hops
                    .mapNotNull { hop -> convertHop(hop) }
                    .sortedWith(ingredientSectionModelComparator)
        }
        return emptyList()
    }

    fun convertMalts(malts: List<Malt?>?): List<IngredientSectionModel> {
        if (malts != null && malts.isNotEmpty()) {
            return malts
                    .mapNotNull { malt -> convertMalt(malt) }
                    .sortedWith(ingredientSectionModelComparator)
        }
        return emptyList()
    }
}