package studio.smartters.jewellary.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import studio.smartters.jewellary.R;

public class ShowItemsFragment extends Fragment {
    private View v;
    private TextView tv;
    public static String myData,myType;
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
        tv = v.findViewById(R.id.demo_text);
        tv.setAllCaps(true);
        tv.setText("Catagory Selected is : "+myType+"\n Type Selected : "+myData);
        return v;
    }

}
