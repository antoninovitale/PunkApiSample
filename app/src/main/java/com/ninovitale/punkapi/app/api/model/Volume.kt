package com.ninovitale.punkapi.app.api.model

import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Volume(val value: Double = 0.0, val unit: String?) : Serializable