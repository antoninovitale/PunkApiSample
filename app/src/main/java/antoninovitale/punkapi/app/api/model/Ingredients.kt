package antoninovitale.punkapi.app.api.model

import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Ingredients(
        var malt: List<Malt>? = null,
        var hops: List<Hop>? = null,
        var yeast: String? = null
) : Serializable