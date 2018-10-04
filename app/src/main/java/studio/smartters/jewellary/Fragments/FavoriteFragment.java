package studio.smartters.jewellary.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import studio.smartters.jewellary.R;
import studio.smartters.jewellary.adapter.FavAdapter;
import studio.smartters.jewellary.other.DBHelper;

public class FavoriteFragment extends Fragment {
    private RecyclerView list;
    private DBHelper db;
    private static FavoriteFragment instance;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_favorite, container, false);
        db=new DBHelper(getActivity());
        instance=this;
        list=v.findViewById(R.id.fav_list);
        list.setHasFixedSize(true);
        return v;
    }

    public static FavoriteFragment getInstance() {
        return instance;
    }

    public void refresh(){
        list.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL,false));
        list.setAdapter(new FavAdapter(db.getFav(),(AppCompatActivity)getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }
}
