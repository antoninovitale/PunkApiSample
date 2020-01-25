package antoninovitale.punkapi.app.api.model

import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class MashTemp(var temp: Temp? = null, var duration: Double = 0.0) : Serializable