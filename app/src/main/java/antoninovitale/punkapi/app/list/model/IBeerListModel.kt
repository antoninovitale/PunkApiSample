package antoninovitale.punkapi.app.list.model

/**
 * Created by antoninovitale on 01/09/2017.
 */
interface IBeerListModel {
    val name: String?
    val tagLine: String?
    val abvPercentage: String
    val imageUrl: String?
    val attribute: Attribute
}