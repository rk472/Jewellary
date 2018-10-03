package studio.smartters.jewellary.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import studio.smartters.jewellary.R;

public class HomeFragment extends Fragment {
    private View v;
    private AppCompatActivity main;
    private RelativeLayout r_gold,r_silver;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        main = (AppCompatActivity)getActivity();
        r_gold = v.findViewById(R.id.gold_part);
        r_silver = v.findViewById(R.id.silver_part);
        r_gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(main, "Gold Clicked", Toast.LENGTH_SHORT).show();
                Fragment fragment = new CatagoryFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gors","gold");
                fragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, fragment,"cat")
                        .commit();
            }
        });
        r_silver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(main, "Silver Clicked", Toast.LENGTH_SHORT).show();
                Fragment fragment = new CatagoryFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gors","silver");
                fragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, fragment,"cat")
                        .commit();
            }
        });
        return v;
    }

}
