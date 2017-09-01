package antoninovitale.dropcodechallenge.details.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.details.section.model.HeaderSectionModel;
import antoninovitale.dropcodechallenge.details.section.viewholder.HeaderSectionViewHolder;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by antoninovitale on 28/08/2017.
 */
public class HeaderSection extends StatelessSection {
    public final static String TAG = HeaderSection.class.getSimpleName();

    private final HeaderSectionModel headerSectionModel;

    public HeaderSection(HeaderSectionModel headerSectionModel) {
        super(getSectionParameters());
        this.headerSectionModel = headerSectionModel;
    }

    private static SectionParameters getSectionParameters() {
        return new SectionParameters.Builder(R.layout.beer_detail_section_header).build();
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new HeaderSectionViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        HeaderSectionViewHolder itemHolder = (HeaderSectionViewHolder) holder;
        itemHolder.name.setText(headerSectionModel.getName());
        itemHolder.abv.setText(headerSectionModel.getAbvPercentage());
        itemHolder.description.setText(headerSectionModel.getDescription());
    }

}