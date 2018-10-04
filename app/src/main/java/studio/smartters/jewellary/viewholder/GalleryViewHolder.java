package studio.smartters.jewellary.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import studio.smartters.jewellary.R;

public class GalleryViewHolder extends RecyclerView.ViewHolder {
    private ImageView img;
    public GalleryViewHolder(View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.gallery_image);
    }
    public void setImage(final String url, final Context c)
    {
        Picasso.with(c).load(url).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.pic_item)
                .into(img, new Callback() {
                    @Override
                    public void onSuccess() {
                    }
                    @Override
                    public void onError() {
                        Picasso.with(c).load(url).placeholder(R.drawable.pic_item).into(img);
                    }
                });
    }

}
