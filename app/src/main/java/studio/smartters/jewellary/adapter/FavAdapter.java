package studio.smartters.jewellary.adapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import studio.smartters.jewellary.R;
import studio.smartters.jewellary.pojo.DbItem;
import studio.smartters.jewellary.viewholder.ItemsViewHolder;

public class FavAdapter extends RecyclerView.Adapter<ItemsViewHolder> {
    private List<DbItem> list;
    private AppCompatActivity a;

    public FavAdapter(List<DbItem> list, AppCompatActivity a) {
        this.list = list;
        this.a = a;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemsViewHolder(LayoutInflater.from(a).inflate(R.layout.all_items_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        holder.setFav(true);
        holder.setImage(list.get(position).getUrl(),a);
        holder.setFavouriteWithRefresh(a,list.get(position).getId());
        holder.setEnquiry(list.get(position).getId(),a,list.get(position).getGos(),list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
