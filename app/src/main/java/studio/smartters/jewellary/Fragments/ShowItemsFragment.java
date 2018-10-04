package studio.smartters.jewellary.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import studio.smartters.jewellary.R;
import studio.smartters.jewellary.other.DBHelper;
import studio.smartters.jewellary.pojo.Items;
import studio.smartters.jewellary.viewholder.ItemsViewHolder;

public class ShowItemsFragment extends Fragment {
    private View v;
    public static String myData,myType;
    private RecyclerView list;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            myData = bundle.getString("gors", " ");
            myType = bundle.getString("type", " ");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_show_items, container, false);
        list=v.findViewById(R.id.item_list);
        final DBHelper db=new DBHelper(getActivity());
        DatabaseReference itemRef= FirebaseDatabase.getInstance().getReference("items/"+myData+"/"+myType);
        FirebaseRecyclerOptions<Items> options=new FirebaseRecyclerOptions.Builder<Items>()
                                                                .setQuery(itemRef,Items.class)
                                                                .build();
        FirebaseRecyclerAdapter<Items,ItemsViewHolder> f=new FirebaseRecyclerAdapter<Items, ItemsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ItemsViewHolder holder, int position, @NonNull Items model) {
                  holder.setImage(model.getImage(),getActivity());
                  holder.setFavourite(getActivity(),getRef(position).getKey(),model.getImage(),myData,myType);
                  holder.setFav(db.isFav(getRef(position).getKey()));
            }

            @NonNull
            @Override
            public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ItemsViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.all_items_row,parent,false));
            }
        };
        f.startListening();
        list.setLayoutManager(new GridLayoutManager(getActivity(), 2,LinearLayoutManager.VERTICAL,false));
        list.setHasFixedSize(true);
        list.setAdapter(f);
        return v;
    }

}
