package studio.smartters.jewellary.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import studio.smartters.jewellary.Fragments.FavoriteFragment;
import studio.smartters.jewellary.R;
import studio.smartters.jewellary.ViewItemActivity;
import studio.smartters.jewellary.other.DBHelper;
import studio.smartters.jewellary.pojo.DbItem;

public class ItemsViewHolder extends RecyclerView.ViewHolder {
    private ImageView img;
    private ToggleButton fav;
    private TextView nameText,soldText;
    private View v;
    public ItemsViewHolder(View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.item_image);
        fav=itemView.findViewById(R.id.button_favorite);
        nameText=itemView.findViewById(R.id.item_row_name);
        soldText=itemView.findViewById(R.id.item_row_sold);
        v=itemView;
    }
    public void setName(String name){
        nameText.setText(name);
    }
    public void setSold(String sold){
        soldText.setText(sold);
    }
    public void setImage(final String url, final Context c){
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
    public void setEnquiry(final String id, final AppCompatActivity a, final String gos, final String type){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.startActivity(new Intent(a, ViewItemActivity.class).putExtra("id",id).putExtra("gos",gos).putExtra("type",type));
            }
        });
    }
    public void setFavourite(final Context c, final String id,final String url,final String gos,final String name,final String originalName,final String sold){

            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c, (!fav.isChecked()?"Removed From":"Added to")+" favourites", Toast.LENGTH_SHORT).show();
                    DBHelper db=new DBHelper(c);
                    if(fav.isChecked())
                        db.addFav(new DbItem(id,url,gos,name,originalName,sold));
                    else
                        db.removeFav(id);
                }
            });
    }
    public void setFavouriteWithRefresh(final  Context c,final String id){
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db=new DBHelper(c);
                db.removeFav(id);
                FavoriteFragment.getInstance().refresh();
            }
        });
    }
    public void setFav(boolean isFav){
        fav.setChecked(isFav);
    }
}
