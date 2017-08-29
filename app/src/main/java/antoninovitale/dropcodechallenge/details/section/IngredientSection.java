package antoninovitale.dropcodechallenge.details.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.details.section.viewholder.IngredientSectionViewHolder;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by antoninovitale on 29/08/2017.
 */
public class IngredientSection extends StatelessSection {

    public IngredientSection() {
        super(getSectionParameters());
    }

    private static SectionParameters getSectionParameters() {
        return new SectionParameters.Builder(R.layout.beer_detail_section_ingredient).build();
    }

    @Override
    public int getContentItemsTotal() {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new IngredientSectionViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
