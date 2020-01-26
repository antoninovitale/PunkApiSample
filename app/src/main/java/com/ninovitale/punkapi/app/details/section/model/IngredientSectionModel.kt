package com.ninovitale.punkapi.app.details.section.model

/**
 * Created by antoninovitale on 29/08/2017.
 */
class IngredientSectionModel(
        val name: String?,
        val amount: String?,
        val type: IngredientType,
        var status: Status,
        val add: Add
)