package com.ninovitale.punkapi.app.api.model

import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class MashTemp(val temp: Temp?, val duration: Double = 0.0) : Serializable