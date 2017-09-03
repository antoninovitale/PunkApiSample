package antoninovitale.dropcodechallenge.details.section.viewholder;

import android.view.View;

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.util.countdownview.CountdownView;
import butterknife.BindView;

/**
 * Created by antoninovitale on 30/08/2017.
 */
public class MethodSectionViewHolder extends BaseSectionViewHolder {

    @BindView(R.id.timer)
    public CountdownView timer;

    public MethodSectionViewHolder(View itemView) {
        super(itemView);
    }

}