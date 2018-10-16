package studio.smartters.jewellary.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import studio.smartters.jewellary.R;
import studio.smartters.jewellary.pojo.Gallery;
import studio.smartters.jewellary.viewholder.GalleryViewHolder;

public class GalleryFragment extends Fragment {
    private RecyclerView list;
    private DatabaseReference galleryRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_gallery, container, false);
        galleryRef= FirebaseDatabase.getInstance().getReference().child("gallery");
        galleryRef.keepSynced(true);
        list=v.findViewById(R.id.gallery_list);
        FirebaseRecyclerOptions<Gallery> options=new FirebaseRecyclerOptions.Builder<Gallery>()
                                    .setQuery(galleryRef,Gallery.class).build();
        FirebaseRecyclerAdapter<Gallery,GalleryViewHolder> f=new FirebaseRecyclerAdapter<Gallery, GalleryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull GalleryViewHolder holder, int position, @NonNull Gallery model) {
                      holder.setImage(model.getImage(),getActivity());
            }

            @NonNull
            @Override
            public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new GalleryViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.gallery_row,parent,false));
            }
        };
        f.startListening();
        list.setAdapter(f);
        list.setHasFixedSize(true);
        list.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        return v;
    }

}
