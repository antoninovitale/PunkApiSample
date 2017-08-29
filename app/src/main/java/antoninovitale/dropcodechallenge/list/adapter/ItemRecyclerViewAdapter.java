package antoninovitale.dropcodechallenge.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.list.viewholder.ItemViewHolder;
import antoninovitale.dropcodechallenge.util.MyImageLoader;
import antoninovitale.dropcodechallenge.util.Utils;

/**
 * Created by antoninovitale on 28/08/2017.
 */
public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private final OnItemClickListener onItemClickListener;

    private List<Beer> items;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public ItemRecyclerViewAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        items = new ArrayList<>();
    }

    public void setItems(List<Beer> items) {
        this.items = items;
        this.notifyItemRangeChanged(0, items.size());
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beer_list_content, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        Beer beer = items.get(position);
        holder.itemView.setTag(beer);
        MyImageLoader.getInstance().loadImage(holder.itemView.getContext(), beer.getImageUrl(),
                holder.img);
        holder.name.setText(beer.getName());
        holder.abv.setText(Utils.formatPercentage(beer.getAbv()));
        holder.tagline.setText(beer.getTagline());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
