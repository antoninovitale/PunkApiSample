package antoninovitale.punkapi.app.api.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Method(
        @Json(name = "mash_temp") var mashTemp: List<MashTemp>? = null,
        var fermentation: Fermentation? = null
) : Serializable