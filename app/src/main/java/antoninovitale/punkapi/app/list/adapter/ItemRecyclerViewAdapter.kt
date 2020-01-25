package antoninovitale.punkapi.app.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import antoninovitale.punkapi.app.R
import antoninovitale.punkapi.app.list.model.Attribute
import antoninovitale.punkapi.app.list.model.IBeerListModel
import antoninovitale.punkapi.app.list.viewholder.ItemViewHolder
import antoninovitale.punkapi.app.util.MyImageLoader
import kotlinx.android.synthetic.main.beer_list_content.view.abv
import kotlinx.android.synthetic.main.beer_list_content.view.attribute
import kotlinx.android.synthetic.main.beer_list_content.view.img
import kotlinx.android.synthetic.main.beer_list_content.view.name
import kotlinx.android.synthetic.main.beer_list_content.view.tagline
import java.util.ArrayList

/**
 * Created by antoninovitale on 28/08/2017.
 */
class ItemRecyclerViewAdapter(private val onItemClickListener: OnItemClickListener) :
        RecyclerView.Adapter<ItemViewHolder?>() {
    private var items: List<IBeerListModel?>?

    init {
        items = ArrayList()
    }

    fun setItems(items: List<IBeerListModel?>?) {
        this.items = items
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.beer_list_content, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val beer = getItem(position) ?: return
        holder.itemView.tag = beer
        MyImageLoader.getInstance()?.loadImage(holder.itemView.context, beer.imageUrl,
                holder.itemView.img)
        holder.itemView.name.text = beer.name
        holder.itemView.abv.text = beer.abvPercentage
        holder.itemView.tagline.text = beer.tagLine
        val label: String? = Attribute.getAttributeLabel(beer.attribute, holder.strongLabel,
                holder.bitterLabel)
        if (!label.isNullOrBlank()) {
            holder.itemView.attribute.visibility = View.VISIBLE
            holder.itemView.attribute.text = label
        } else {
            holder.itemView.attribute.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(holder.adapterPosition)
        }
    }

    private fun getItem(position: Int): IBeerListModel? {
        return if (position < itemCount) items?.get(position) else null
    }

    override fun getItemCount(): Int = items?.size ?: 0

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}