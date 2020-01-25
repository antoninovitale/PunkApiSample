package antoninovitale.punkapi.app.details.section.model

/**
 * Created by antoninovitale on 31/08/2017.
 */
enum class Add {
    START, MIDDLE, END, NONE;

    companion object {
        fun getAdd(add: String?): Add {
            return if (add == null) NONE else when (add) {
                "start" -> START
                "middle" -> MIDDLE
                "end" -> END
                else -> NONE
            }
        }
    }
}