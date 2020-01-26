package com.ninovitale.punkapi.app.details.section

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ninovitale.punkapi.app.R
import com.ninovitale.punkapi.app.details.section.model.HeaderSectionModel
import com.ninovitale.punkapi.app.details.section.viewholder.HeaderSectionViewHolder
import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import kotlinx.android.synthetic.main.beer_detail_section_header.view.abv
import kotlinx.android.synthetic.main.beer_detail_section_header.view.description
import kotlinx.android.synthetic.main.beer_detail_section_header.view.name

/**
 * Created by antoninovitale on 28/08/2017.
 */
class HeaderSection(private val headerSectionModel: HeaderSectionModel) :
        Section(sectionParameters) {
    override fun getContentItemsTotal(): Int = 1

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return HeaderSectionViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as HeaderSectionViewHolder
        itemHolder.itemView.name.text = headerSectionModel.name
        itemHolder.itemView.abv.text = headerSectionModel.abvPercentage
        itemHolder.itemView.description.text = headerSectionModel.description
    }

    companion object {
        val TAG = HeaderSection::class.java.simpleName

        private val sectionParameters: SectionParameters
            get() = SectionParameters.builder()
                    .itemResourceId(R.layout.beer_detail_section_header)
                    .build()
    }
}