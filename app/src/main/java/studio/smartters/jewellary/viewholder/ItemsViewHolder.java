package studio.smartters.jewellary.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import studio.smartters.jewellary.R;
import studio.smartters.jewellary.other.DBHelper;
import studio.smartters.jewellary.pojo.DbItem;

public class ItemsViewHolder extends RecyclerView.ViewHolder {
    private ImageView img;
    private ToggleButton fav;
    public ItemsViewHolder(View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.item_image);
        fav=itemView.findViewById(R.id.button_favorite);
    }
    public void setImage(String url, Context c){
        Picasso.with(c).load(url).into(img);
    }
    public void setEnquiry(String id,Context c){

    }
    public void setFavourite(final Context c, final String id,final String url,final String gos,final String name){

            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c, (!fav.isChecked()?"Removed From":"Added to")+" favourites", Toast.LENGTH_SHORT).show();
                    DBHelper db=new DBHelper(c);
                    if(fav.isChecked())
                        db.addFav(new DbItem(id,url,gos,name));
                    else
                        db.removeFav(id);
                }
            });
    }
    public void setFav(boolean isFav){
        fav.setChecked(isFav);
    }
}
