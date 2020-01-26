package com.ninovitale.punkapi.app.api.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Method(
        @Json(name = "mash_temp") val mashTemp: List<MashTemp>?,
        val fermentation: Fermentation?
) : Serializable