package com.ninovitale.punkapi.app.list.model

/**
 * Created by antoninovitale on 31/08/2017.
 */
enum class Attribute {
    STRONG, BITTER, STRONG_BITTER, NONE;

    companion object {
        fun getAttribute(abv: Double, ibu: Double): Attribute {
            return when {
                abv > 10 && ibu > 90 -> STRONG_BITTER
                abv > 10 -> STRONG
                ibu > 90 -> BITTER
                else -> NONE
            }
        }

        fun getAttributeLabel(
                attribute: Attribute?,
                attributeStrong: String?,
                attributeBitter: String?
        ): String? {
            var label: String? = null
            when (attribute) {
                STRONG -> label = attributeStrong
                BITTER -> label = attributeBitter
                STRONG_BITTER -> label = String.format("%s & %s", attributeStrong, attributeBitter)
                else -> {
                }
            }
            return label
        }
    }
}