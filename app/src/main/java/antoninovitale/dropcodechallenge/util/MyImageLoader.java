package antoninovitale.dropcodechallenge.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import antoninovitale.dropcodechallenge.R;

/**
 * Created by antoninovitale on 14/08/2017.
 */
public class MyImageLoader {

    private static MyImageLoader instance;

    private MyImageLoader() {
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new MyImageLoader();
        }
    }

    public static MyImageLoader getInstance() {
        if (instance == null) createInstance();
        return instance;
    }

    public void loadImage(Context context, String url, ImageView view) {
        Glide.with(context).load(url)
                .placeholder(R.drawable.ic_placeholder)
                .fallback(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .crossFade()
                .fitCenter()
                .into(view);
    }

}
