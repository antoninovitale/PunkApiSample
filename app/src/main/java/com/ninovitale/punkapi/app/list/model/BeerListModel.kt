package com.ninovitale.punkapi.app.list.model

/**
 * Created by antoninovitale on 31/08/2017.
 */
data class BeerListModel constructor(
        override val name: String?,
        override val tagLine: String?,
        override val abvPercentage: String,
        override val imageUrl: String?,
        override val attribute: Attribute
) : IBeerListModel