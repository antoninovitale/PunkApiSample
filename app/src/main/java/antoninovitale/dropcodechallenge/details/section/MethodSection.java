package antoninovitale.dropcodechallenge.details.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by antoninovitale on 29/08/2017.
 */
public class MethodSection extends StatelessSection {

    public MethodSection() {
        super(getSectionParameters());
    }

    private static SectionParameters getSectionParameters() {
        return null;
    }

    @Override
    public int getContentItemsTotal() {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return null;
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

}