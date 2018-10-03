package studio.smartters.jewellary.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import studio.smartters.jewellary.R;

public class CatagoryFragment extends Fragment {
    private View v;
    public static String myData;
    private RelativeLayout textArea,btnNeckless,btnBangle,btnEarring,btnPendant,btnMangalsutra,btnRings,btnNoseRing,btnPayal,btnCoin;
    private AppCompatActivity main;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            myData = bundle.getString("gors", " ");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_catagory, container, false);
        main = (AppCompatActivity)getActivity();
        //Setting Animations
        Animation slideLeftRight = AnimationUtils.loadAnimation(main, R.anim.slide_from_left);
        Animation slideRightLeft = AnimationUtils.loadAnimation(main, R.anim.slide_from_right);
        Animation slideTopBottom = AnimationUtils.loadAnimation(main, R.anim.slide_in_down);
        Animation expandIn = AnimationUtils.loadAnimation(main, R.anim.expand_in);
        //Fetching All Views
        textArea = v.findViewById(R.id.catagoty_text_all);
        btnNeckless = v.findViewById(R.id.necklace_btn);
        btnBangle = v.findViewById(R.id.bangle_btn);
        btnEarring = v.findViewById(R.id.earring_btn);
        btnPendant= v.findViewById(R.id.pendant_btn);
        btnMangalsutra = v.findViewById(R.id.mangalsutra_btn);
        btnRings = v.findViewById(R.id.rings_btn);
        btnNoseRing = v.findViewById(R.id.nosering_btn);
        btnPayal = v.findViewById(R.id.payal_btn);
        btnCoin = v.findViewById(R.id.coins_btn);
        //Start Animation
        textArea.startAnimation(slideTopBottom);
        btnNeckless.startAnimation(slideLeftRight);
        btnPendant.startAnimation(slideLeftRight);
        btnNoseRing.startAnimation(slideLeftRight);
        btnEarring.startAnimation(slideRightLeft);
        btnRings.startAnimation(slideRightLeft);
        btnCoin.startAnimation(slideRightLeft);
        btnBangle.startAnimation(expandIn);
        btnMangalsutra.startAnimation(expandIn);
        btnPayal.startAnimation(expandIn);
        //Setting OnClick in all Items
        btnBangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ShowItemsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gors",myData);
                bundle.putString("type","bangle");
                fragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, fragment,"item_show")
                        .commit();
            }
        });
        btnNeckless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ShowItemsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gors",myData);
                bundle.putString("type","necklace");
                fragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, fragment,"item_show")
                        .commit();
            }
        });
        btnEarring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ShowItemsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gors",myData);
                bundle.putString("type","earring");
                fragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, fragment,"item_show")
                        .commit();
            }
        });
        btnPendant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ShowItemsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gors",myData);
                bundle.putString("type","pendant");
                fragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, fragment,"item_show")
                        .commit();
            }
        });
        btnMangalsutra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ShowItemsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gors",myData);
                bundle.putString("type","mangalsutra");
                fragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, fragment,"item_show")
                        .commit();
            }
        });
        btnRings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ShowItemsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gors",myData);
                bundle.putString("type","ring");
                fragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, fragment,"item_show")
                        .commit();
            }
        });
        btnNoseRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ShowItemsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gors",myData);
                bundle.putString("type","nosering");
                fragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, fragment,"item_show")
                        .commit();
            }
        });
        btnPayal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ShowItemsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gors",myData);
                bundle.putString("type","payal");
                fragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, fragment,"item_show")
                        .commit();
            }
        });
        btnCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ShowItemsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gors",myData);
                bundle.putString("type","coin");
                fragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, fragment,"item_show")
                        .commit();
            }
        });
        return v;
    }
}
