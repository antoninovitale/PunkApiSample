package antoninovitale.punkapi.app.api.model

import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Malt(var name: String? = null, var amount: Amount? = null) : Serializable