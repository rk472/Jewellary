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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_favorite, container, false);
        DBHelper db=new DBHelper(getActivity());
        list=v.findViewById(R.id.fav_list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL,false));
        list.setAdapter(new FavAdapter(db.getFav(),(AppCompatActivity)getActivity()));
        //Toast.makeText(getActivity(), db.getCount()+"", Toast.LENGTH_SHORT).show();
        return v;
    }

}
