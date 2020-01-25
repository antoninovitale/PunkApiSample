package antoninovitale.punkapi.app.api.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Beer(
        var id: Int = 0,
        var name: String? = null,
        var tagline: String? = null,
        @Json(name = "first_brewed")
        var firstBrewed: String? = null,
        var description: String? = null,
        @Json(name = "image_url")
        var imageUrl: String? = null,
        var abv: Double = 0.0,
        var ibu: Double = 0.0,
        @Json(name = "target_fg")
        var targetFg: Double = 0.0,
        @Json(name = "target_og")
        var targetOg: Double = 0.0,
        var ebc: Double = 0.0,
        var srm: Double = 0.0,
        var ph: Double = 0.0,
        @Json(name = "attenuation_level")
        var attenuationLevel: Double = 0.0,
        var volume: Volume? = null,
        @Json(name = "boil_volume")
        var boilVolume: BoilVolume? = null,
        var method: Method? = null,
        var ingredients: Ingredients? = null,
        @Json(name = "food_pairing")
        var foodPairing: List<String>? = null,
        @Json(name = "brewers_tips")
        var brewersTips: String? = null,
        @Json(name = "contributed_by")
        var contributedBy: String? = null
) : Serializable