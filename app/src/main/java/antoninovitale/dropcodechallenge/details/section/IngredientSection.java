package antoninovitale.dropcodechallenge.details.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.details.section.model.IngredientSectionModel;
import antoninovitale.dropcodechallenge.details.section.viewholder.IngredientSectionViewHolder;
import antoninovitale.dropcodechallenge.details.section.viewholder.SectionTitleViewHolder;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by antoninovitale on 29/08/2017.
 */
public class IngredientSection extends StatelessSection {
    public final static String MALT_TAG = "MALT";

    public final static String HOP_TAG = "HOP";

    private final List<IngredientSectionModel> ingredients;

    private final String sectionTitle;

    public IngredientSection(String title, List<IngredientSectionModel> ingredients) {
        super(getSectionParameters());
        this.ingredients = ingredients;
        this.sectionTitle = title;
    }

    private static SectionParameters getSectionParameters() {
        return new SectionParameters.Builder(R.layout.beer_detail_section_ingredient)
                .headerResourceId(R.layout.beer_detail_section_ingredient_header).build();
    }

    @Override
    public int getContentItemsTotal() {
        return ingredients.size();
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new SectionTitleViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        SectionTitleViewHolder vh = (SectionTitleViewHolder) holder;
        vh.title.setText(sectionTitle);
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new IngredientSectionViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        IngredientSectionModel model = ingredients.get(position);
        IngredientSectionViewHolder viewHolder = (IngredientSectionViewHolder) holder;
        viewHolder.name.setText(model.getName());
        viewHolder.amount.setText(model.getAmount());
    }

}