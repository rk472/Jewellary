package studio.smartters.jewellary.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
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
    private Button enquiry;
    private ToggleButton fav;
    public ItemsViewHolder(View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.item_image);
        enquiry=itemView.findViewById(R.id.inquiry_btn);
        fav=itemView.findViewById(R.id.button_favorite);
    }
    public void setImage(String url, Context c){
        Picasso.with(c).load(url).into(img);
    }
    public void setEnquiry(String id,Context c){

    }
    public void setFavourite(final Context c, final String id,final String url,final String gos,final String name){
            fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Toast.makeText(c, (!isChecked?"Removed From":"Added to")+" favourites", Toast.LENGTH_SHORT).show();
                    DBHelper db=new DBHelper(c);
                    if(isChecked)
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
