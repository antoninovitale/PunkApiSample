package antoninovitale.punkapi.app.api.model

import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Temp(var value: Double = 0.0, var unit: String? = null) : Serializable