package com.ninovitale.punkapi.app.api.model

import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Hop(
        val name: String?,
        val amount: Amount?,
        val add: String?,
        val attribute: String?
) : Serializable