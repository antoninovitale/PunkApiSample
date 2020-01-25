package antoninovitale.punkapi.app.api.model

import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Hop(
        var name: String? = null,
        var amount: Amount? = null,
        var add: String? = null,
        var attribute: String? = null
) : Serializable