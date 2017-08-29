package antoninovitale.dropcodechallenge.details.section.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import antoninovitale.dropcodechallenge.R;
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
    public ToggleButton status;

    public IngredientSectionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}