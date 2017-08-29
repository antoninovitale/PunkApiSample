package antoninovitale.dropcodechallenge.details.section.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.util.ExpandableTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antoninovitale on 28/08/2017.
 */
public class HeaderSectionViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.name)
    public TextView name;

    @BindView(R.id.abv)
    public TextView abv;

    @BindView(R.id.description)
    public ExpandableTextView description;

    public HeaderSectionViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

}