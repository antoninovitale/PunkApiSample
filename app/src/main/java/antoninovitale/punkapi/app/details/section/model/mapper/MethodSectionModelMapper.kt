package antoninovitale.punkapi.app.details.section.model.mapper

import antoninovitale.punkapi.app.api.model.Method
import antoninovitale.punkapi.app.api.model.Temp
import antoninovitale.punkapi.app.details.section.model.MethodSectionModel
import antoninovitale.punkapi.app.details.section.model.Status.IDLE
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Created by antoninovitale on 30/08/2017.
 */
object MethodSectionModelMapper {
    fun convertMethods(method: Method): List<MethodSectionModel> {
        val mashTemps = method.mashTemp
        if (mashTemps != null && mashTemps.isNotEmpty()) {
            return mashTemps.map { mashTemp ->
                with(mashTemp) {
                    MethodSectionModel(convertTemp(temp), SECONDS.toMillis(duration.toLong()), IDLE)
                }
            }
        }
        return emptyList()
    }

    private fun convertTemp(temp: Temp?): String? {
        return if (temp != null) String.format("%s %s", temp.value,
                temp.unit?.toUpperCase()?.get(0)) else null
    }
}