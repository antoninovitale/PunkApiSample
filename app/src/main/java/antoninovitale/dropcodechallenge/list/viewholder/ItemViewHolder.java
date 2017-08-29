package antoninovitale.dropcodechallenge.list.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import antoninovitale.dropcodechallenge.R;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antoninovitale on 28/08/2017.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.img)
    public ImageView img;

    @BindView(R.id.name)
    public TextView name;

    @BindView(R.id.abv)
    public TextView abv;

    @BindView(R.id.tagline)
    public TextView tagline;

    @BindView(R.id.attribute)
    public TextView attribute;

    @BindString(R.string.strong)
    public String attributeStrong;

    @BindString(R.string.bitter)
    public String attributeBitter;

    public ItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

}