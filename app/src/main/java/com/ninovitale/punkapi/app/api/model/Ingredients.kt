package com.ninovitale.punkapi.app.api.model

import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Ingredients(
        val malt: List<Malt>?,
        val hops: List<Hop>?,
        val yeast: String?
) : Serializable