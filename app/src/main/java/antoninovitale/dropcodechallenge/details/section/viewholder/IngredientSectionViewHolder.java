package antoninovitale.dropcodechallenge.details.section.viewholder;

import android.view.View;
import android.widget.TextView;

import antoninovitale.dropcodechallenge.R;
import butterknife.BindView;

/**
 * Created by antoninovitale on 29/08/2017.
 */
public class IngredientSectionViewHolder extends BaseSectionViewHolder {

    @BindView(R.id.amount)
    public TextView amount;

    public IngredientSectionViewHolder(View itemView) {
        super(itemView);
    }

}