package antoninovitale.dropcodechallenge.details.section.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import antoninovitale.dropcodechallenge.R;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antoninovitale on 29/08/2017.
 */
public class IngredientSectionViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.name)
    public TextView name;

    @BindView(R.id.amount)
    public TextView amount;

    @BindView(R.id.status)
    public TextView status;

    @BindString(R.string.idle)
    public String idle;

    @BindString(R.string.done)
    public String done;

    public IngredientSectionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}