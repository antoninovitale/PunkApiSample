package com.ninovitale.punkapi.app.details

import com.ninovitale.punkapi.app.api.model.Beer
import com.ninovitale.punkapi.app.details.BeerDetailsInteractor.OnSectionSetupListener
import com.ninovitale.punkapi.app.details.BeerDetailsInteractor.OnStatusCheckListener
import com.ninovitale.punkapi.app.details.section.model.Add.END
import com.ninovitale.punkapi.app.details.section.model.Add.MIDDLE
import com.ninovitale.punkapi.app.details.section.model.Add.START
import com.ninovitale.punkapi.app.details.section.model.HeaderSectionModel
import com.ninovitale.punkapi.app.details.section.model.IngredientSectionModel
import com.ninovitale.punkapi.app.details.section.model.IngredientType.HOP
import com.ninovitale.punkapi.app.details.section.model.IngredientType.MALT
import com.ninovitale.punkapi.app.details.section.model.MethodSectionModel
import com.ninovitale.punkapi.app.details.section.model.Status.DONE
import com.ninovitale.punkapi.app.details.section.model.Status.IDLE
import com.ninovitale.punkapi.app.details.section.model.Status.PAUSED
import com.ninovitale.punkapi.app.details.section.model.Status.RUNNING
import com.ninovitale.punkapi.app.details.section.model.mapper.IngredientSectionModelMapper
import com.ninovitale.punkapi.app.details.section.model.mapper.MethodSectionModelMapper
import com.ninovitale.punkapi.app.util.Utils

/**
 * Created by antoninovitale on 01/09/2017.
 */
internal class BeerDetailsInteractorImpl : BeerDetailsInteractor {
    private var headerSectionModel: HeaderSectionModel? = null
    private var malts: MutableList<IngredientSectionModel>? = null
    private var hops: MutableList<IngredientSectionModel>? = null
    private var methods: MutableList<MethodSectionModel>? = null

    override fun setupBeer(beer: Beer, onSectionSetupListener: OnSectionSetupListener) {
        if (headerSectionModel == null || !beer.name.equals(headerSectionModel?.name,
                        ignoreCase = true)) {
            headerSectionModel = HeaderSectionModel(beer.name ?: "",
                    Utils.formatPercentage(beer.abv), beer.description ?: "")
            val ingredients = beer.ingredients
            if (ingredients != null) {
                malts = IngredientSectionModelMapper.convertMalts(ingredients.malt).toMutableList()
                hops = IngredientSectionModelMapper.convertHops(ingredients.hops).toMutableList()
            }
            val method = beer.method
            if (method != null) {
                methods = MethodSectionModelMapper.convertMethods(method).toMutableList()
            }
        }
        headerSectionModel?.let { onSectionSetupListener.onHeaderSet(it) }
        onSectionSetupListener.onMaltsSet(malts ?: emptyList())
        onSectionSetupListener.onHopsSet(hops ?: emptyList())
        onSectionSetupListener.onMethodsSet(methods ?: emptyList())
    }

    override fun checkIngredientStatus(position: Int,
            onStatusCheckListener: OnStatusCheckListener) {
        var calculatedPosition = position
        var positionInSection = 0
        val malts = malts
        val hops = hops
        if (calculatedPosition > 0) {
            calculatedPosition-- //Header section counts as 1
            var model: IngredientSectionModel? = null
            when {
                malts.isNullOrEmpty() -> {
                    positionInSection = calculatedPosition - 1 //header in section must be subtracted
                    model = hops?.get(positionInSection)
                }
                calculatedPosition <= malts.size -> {
                    positionInSection = calculatedPosition - 1 //header in section must be subtracted
                    model = malts[positionInSection]
                }
                !hops.isNullOrEmpty() -> {
                    positionInSection = calculatedPosition - malts.size - 2 //headers in both sections must be subtracted
                    model = hops[positionInSection]
                }
            }
            if (model != null && model.status != DONE) {
                when (model.type) {
                    MALT -> {
                        model.status = DONE
                        malts?.set(positionInSection, model)
                        onStatusCheckListener.onMaltDone(positionInSection, position)
                    }
                    HOP -> if (checkHops(model, positionInSection)) {
                        onStatusCheckListener.onHopDone(positionInSection, position)
                    }
                    else -> {
                    }
                }
            }
        }
    }

    override fun checkMethodStatus(position: Int, onStatusCheckListener: OnStatusCheckListener) {
        val methods = methods ?: return
        val positionInSection = getMethodPositionInSection(position)
        if (positionInSection < 0 || positionInSection >= methods.size) {
            return
        }
        val model = methods[positionInSection]
        when (model.status) {
            IDLE -> if (model.duration == 0L) {
                model.status = DONE
            } else {
                model.status = RUNNING
            }
            RUNNING -> model.status = PAUSED
            PAUSED -> model.status = RUNNING
            DONE -> return
        }
        methods[positionInSection] = model
        onStatusCheckListener.onMethodStatusChanged(positionInSection, position, model.status)
    }

    override fun setMethodDone(position: Int, onStatusCheckListener: OnStatusCheckListener) {
        val methods = methods ?: return
        val positionInSection = getMethodPositionInSection(position)
        if (positionInSection < 0 || positionInSection >= methods.size) {
            return
        }
        methods[positionInSection].status = DONE
        onStatusCheckListener.onMethodDone(positionInSection, position)
    }

    override fun setMethodTimeElapsed(position: Int, millis: Long) {
        val methods = methods ?: return
        val positionInSection = getMethodPositionInSection(position)
        if (positionInSection < 0 || positionInSection >= methods.size) {
            return
        }
        methods[positionInSection].remainingTime = millis
    }

    private fun checkHops(model: IngredientSectionModel, positionInSection: Int): Boolean {
        var canDo = true
        val hops = hops ?: return false
        when (model.add) {
            START -> {
            }
            MIDDLE -> for (hop in hops) {
                if (hop.add == START && hop.status != DONE) {
                    canDo = false
                    break
                }
            }
            END -> for (hop in hops) {
                if (hop.add != END && hop.status != DONE) {
                    canDo = false
                    break
                }
            }
            else -> {
            }
        }
        if (canDo) {
            model.status = DONE
            hops[positionInSection] = model
        }
        return canDo
    }

    private fun getMethodPositionInSection(position: Int): Int {
        val malts = malts
        val hops = hops
        return when {
            malts.isNullOrEmpty() && !hops.isNullOrEmpty() -> {
                position - hops.size - 3 //headers must be subtracted
            }
            hops.isNullOrEmpty() && !malts.isNullOrEmpty() -> {
                position - malts.size - 3 //headers must be subtracted
            }
            !hops.isNullOrEmpty() && !malts.isNullOrEmpty() -> {
                position - malts.size - hops.size - 4 //headers must be subtracted
            }
            else -> position - 2 //headers must be subtracted
        }
    }
}