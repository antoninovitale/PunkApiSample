package com.ninovitale.punkapi.app.details.section

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ninovitale.punkapi.app.R
import com.ninovitale.punkapi.app.details.section.model.IngredientSectionModel
import com.ninovitale.punkapi.app.details.section.model.Status.Companion.getStatusLabel
import com.ninovitale.punkapi.app.details.section.model.Status.DONE
import com.ninovitale.punkapi.app.details.section.viewholder.IngredientSectionViewHolder
import com.ninovitale.punkapi.app.details.section.viewholder.SectionTitleViewHolder
import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import kotlinx.android.synthetic.main.beer_detail_section_ingredient.view.amount
import kotlinx.android.synthetic.main.beer_detail_section_ingredient.view.name
import kotlinx.android.synthetic.main.beer_detail_section_ingredient.view.status
import kotlinx.android.synthetic.main.simple_header.view.header_title

/**
 * Created by antoninovitale on 29/08/2017.
 */
class IngredientSection(
        private val sectionTitle: String,
        private val ingredients: List<IngredientSectionModel>,
        private val onIngredientStatusClickListener: OnIngredientStatusClickListener
) : Section(sectionParameters) {
    fun setItemStatus(position: Int) {
        ingredients[position].status = DONE
    }

    override fun getContentItemsTotal(): Int {
        return ingredients.size
    }

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return SectionTitleViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder) {
        val vh = holder as SectionTitleViewHolder
        vh.itemView.header_title.text = sectionTitle
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return IngredientSectionViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = ingredients[position]
        val viewHolder = holder as IngredientSectionViewHolder
        viewHolder.itemView.name.text = model.name
        viewHolder.itemView.amount.text = model.amount
        viewHolder.itemView.status.text = getStatusLabel(model.status, viewHolder.idle,
                viewHolder.running, viewHolder.paused, viewHolder.done)
        viewHolder.itemView.status.setOnClickListener {
            onIngredientStatusClickListener.onStatusClick(holder.getAdapterPosition())
        }
    }

    interface OnIngredientStatusClickListener {
        fun onStatusClick(position: Int)
    }

    companion object {
        const val MALT_TAG = "MALT"
        const val HOP_TAG = "HOP"
        private val sectionParameters: SectionParameters
            get() = SectionParameters.builder()
                    .itemResourceId(R.layout.beer_detail_section_ingredient)
                    .headerResourceId(R.layout.simple_header)
                    .build()
    }
}