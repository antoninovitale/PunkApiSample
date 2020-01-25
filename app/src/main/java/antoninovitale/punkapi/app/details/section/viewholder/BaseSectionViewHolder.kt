package antoninovitale.punkapi.app.details.section.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import antoninovitale.punkapi.app.R

/**
 * Created by antoninovitale on 03/09/2017.
 */
open class BaseSectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val idle = itemView.context.getString(R.string.idle)
    val running = itemView.context.getString(R.string.running)
    val paused = itemView.context.getString(R.string.paused)
    val done = itemView.context.getString(R.string.done)
}