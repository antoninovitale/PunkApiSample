package antoninovitale.dropcodechallenge.details.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.details.section.model.MethodSectionModel;
import antoninovitale.dropcodechallenge.details.section.viewholder.MethodSectionViewHolder;
import antoninovitale.dropcodechallenge.details.section.viewholder.SectionTitleViewHolder;
import antoninovitale.dropcodechallenge.util.countdownview.CountdownView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by antoninovitale on 29/08/2017.
 */
public class MethodSection extends StatelessSection {
    public static final String TAG = MethodSection.class.getSimpleName();

    private final List<MethodSectionModel> methods;

    private final String sectionTitle;

    public MethodSection(String title, List<MethodSectionModel> methods) {
        super(getSectionParameters());
        this.sectionTitle = title;
        this.methods = methods;
    }

    private static SectionParameters getSectionParameters() {
        return new SectionParameters.Builder(R.layout.beer_detail_section_method)
                .headerResourceId(R.layout.simple_header)
                .build();
    }

    @Override
    public int getContentItemsTotal() {
        return methods.size();
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
        return new MethodSectionViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        final MethodSectionViewHolder vh = (MethodSectionViewHolder) viewHolder;
        MethodSectionModel model = methods.get(i);
        vh.name.setText(model.getTemp());
        long duration = model.getDuration();
        if (duration == 0) {
            vh.timer.setVisibility(View.GONE);
            vh.status.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    vh.status.setText(vh.done);
                }
            });
        } else {
            vh.timer.setVisibility(View.VISIBLE);
            vh.timer.updateShow(duration);
            vh.timer.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                @Override
                public void onEnd(CountdownView cv) {
                    vh.status.setText(vh.done);
                }
            });
            vh.status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String status = vh.status.getText().toString();
                    if (status.equalsIgnoreCase(vh.done)) {
                        return;
                    }
                    if (status.equalsIgnoreCase(vh.idle)) {
                        vh.timer.start(methods.get(i).getDuration());
                        vh.status.setText(vh.running);
                        return;
                    }
                    if (vh.timer.isTimerRunning()) {
                        vh.timer.pause();
                        vh.status.setText(vh.paused);
                    } else if (vh.timer.isTimerPaused()) {
                        vh.timer.restart();
                        vh.status.setText(vh.running);
                    }
                }
            });
        }
    }

}