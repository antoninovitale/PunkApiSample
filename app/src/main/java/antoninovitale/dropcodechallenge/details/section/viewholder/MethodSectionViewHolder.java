package antoninovitale.dropcodechallenge.details.section.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.util.countdownview.CountdownView;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antoninovitale on 30/08/2017.
 */
public class MethodSectionViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.name)
    public TextView name;

    @BindView(R.id.timer)
    public CountdownView timer;

    @BindView(R.id.status)
    public TextView status;

    @BindString(R.string.idle)
    public String idle;

    @BindString(R.string.running)
    public String running;

    @BindString(R.string.paused)
    public String paused;

    @BindString(R.string.done)
    public String done;

    public MethodSectionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}