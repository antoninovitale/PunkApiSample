package antoninovitale.dropcodechallenge.details.section.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import antoninovitale.dropcodechallenge.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antoninovitale on 29/08/2017.
 */
public class SectionTitleViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.header_title)
    public TextView title;

    public SectionTitleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}