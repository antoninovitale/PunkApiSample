package antoninovitale.punkapi.app.list.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import antoninovitale.punkapi.app.R

/**
 * Created by antoninovitale on 28/08/2017.
 */
class ItemViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    val strongLabel = view.context.getString(R.string.strong)
    val bitterLabel = view.context.getString(R.string.bitter)
}