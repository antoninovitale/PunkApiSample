package com.ninovitale.punkapi.app.api.model

import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Malt(val name: String?, val amount: Amount?) : Serializable