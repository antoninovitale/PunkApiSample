package com.ninovitale.punkapi.app.api.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Beer(
        val id: Int = 0,
        val name: String?,
        val tagline: String?,
        @Json(name = "first_brewed") val firstBrewed: String?,
        val description: String?,
        @Json(name = "image_url") val imageUrl: String?,
        val abv: Double = 0.0,
        val ibu: Double = 0.0,
        @Json(name = "target_fg") val targetFg: Double = 0.0,
        @Json(name = "target_og") val targetOg: Double = 0.0,
        val ebc: Double = 0.0,
        val srm: Double = 0.0,
        val ph: Double = 0.0,
        @Json(name = "attenuation_level") val attenuationLevel: Double = 0.0,
        val volume: Volume?,
        @Json(name = "boil_volume") val boilVolume: BoilVolume?,
        val method: Method?,
        val ingredients: Ingredients?,
        @Json(name = "food_pairing") val foodPairing: List<String>?,
        @Json(name = "brewers_tips") val brewersTips: String?,
        @Json(name = "contributed_by") val contributedBy: String?
) : Serializable