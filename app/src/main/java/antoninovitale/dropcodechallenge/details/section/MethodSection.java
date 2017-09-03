package antoninovitale.dropcodechallenge.details.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.details.section.model.MethodSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.Status;
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

    private final OnMethodStatusClickListener onMethodStatusClickListener;

    private final OnMethodEndListener onMethodEndListener;

    private final OnTimeElapsedListener onTimeElapsedListener;

    public interface OnMethodStatusClickListener {
        void onMethodStatusClick(int position);
    }

    public interface OnMethodEndListener {
        void onMethodEnd(int position);
    }

    public interface OnTimeElapsedListener {
        void onTimeElapsed(int position, long millis);
    }

    public MethodSection(String title, List<MethodSectionModel> methods,
                         OnMethodStatusClickListener onMethodStatusClickListener,
                         OnMethodEndListener onMethodEndListener, OnTimeElapsedListener
                                 onTimeElapsedListener) {
        super(getSectionParameters());
        this.sectionTitle = title;
        this.methods = methods;
        this.onMethodStatusClickListener = onMethodStatusClickListener;
        this.onMethodEndListener = onMethodEndListener;
        this.onTimeElapsedListener = onTimeElapsedListener;
    }

    private static SectionParameters getSectionParameters() {
        return new SectionParameters.Builder(R.layout.beer_detail_section_method)
                .headerResourceId(R.layout.simple_header)
                .build();
    }

    public void setItemStatus(int position, Status status) {
        methods.get(position).setStatus(status);
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
        } else {
            vh.timer.setVisibility(View.VISIBLE);
            Status status = model.getStatus();
            vh.status.setText(Status.getStatusLabel(status, vh.idle, vh.running, vh.paused, vh
                    .done));
            switch (status) {
                case IDLE:
                    vh.timer.updateShow(duration);
                    break;
                case RUNNING:
                    if (model.getRemainingTime() == 0) {
                        vh.timer.start(duration);
                    } else {
                        vh.timer.start(model.getRemainingTime());
                    }
                    break;
                case PAUSED:
                    vh.timer.pause();
                    vh.timer.updateShow(model.getRemainingTime());
                    break;
                case DONE:
                    vh.timer.stop();
                    break;
            }
            vh.timer.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                @Override
                public void onEnd(CountdownView cv) {
                    onMethodEndListener.onMethodEnd(vh.getAdapterPosition());
                }
            });
            vh.timer.setOnCountdownIntervalListener(1000, new CountdownView
                    .OnCountdownIntervalListener() {

                @Override
                public void onInterval(CountdownView cv, long remainTime) {
                    onTimeElapsedListener.onTimeElapsed(vh.getAdapterPosition(), remainTime);
                }
            });
        }
        vh.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMethodStatusClickListener.onMethodStatusClick(vh.getAdapterPosition());
            }
        });
    }

}