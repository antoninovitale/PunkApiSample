package antoninovitale.dropcodechallenge.details.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.details.section.model.IngredientSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.Status;
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

    private final OnIngredientStatusClickListener onIngredientStatusClickListener;

    public IngredientSection(String title, List<IngredientSectionModel> ingredients,
                             OnIngredientStatusClickListener onIngredientStatusClickListener) {
        super(getSectionParameters());
        this.ingredients = ingredients;
        this.sectionTitle = title;
        this.onIngredientStatusClickListener = onIngredientStatusClickListener;
    }

    private static SectionParameters getSectionParameters() {
        return new SectionParameters.Builder(R.layout.beer_detail_section_ingredient)
                .headerResourceId(R.layout.simple_header).build();
    }

    public void setItemStatus(int position) {
        ingredients.get(position).setStatus(Status.DONE);
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
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, int position) {
        IngredientSectionModel model = ingredients.get(position);
        IngredientSectionViewHolder viewHolder = (IngredientSectionViewHolder) holder;
        viewHolder.name.setText(model.getName());
        viewHolder.amount.setText(model.getAmount());
        viewHolder.status.setText(Status.getStatusLabel(model.getStatus(), viewHolder.idle,
                viewHolder.running, viewHolder.paused, viewHolder.done));
        viewHolder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onIngredientStatusClickListener.onStatusClick(holder.getAdapterPosition());
            }
        });
    }

    public interface OnIngredientStatusClickListener {
        void onStatusClick(int position);
    }

}