package studio.smartters.jewellary;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import studio.smartters.jewellary.other.DBHelper;
import studio.smartters.jewellary.pojo.DbItem;

public class ViewItemActivity extends AppCompatActivity {
    private View dialogView;
    private LinearLayout whatsappBtn,appBtn;
    private String id,gos,type,url;
    private ImageView descImage;
    private TextView nameText,soldText,favText;
    private DatabaseReference itemRef;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        id= Objects.requireNonNull(getIntent().getExtras()).getString("id"," ");
        gos=Objects.requireNonNull(getIntent().getExtras()).getString("gos"," ");
        type=Objects.requireNonNull(getIntent().getExtras()).getString("type"," ");
        itemRef= FirebaseDatabase.getInstance().getReference().child("items").child(gos).child(type).child(id);
        itemRef.keepSynced(true);
        descImage=findViewById(R.id.item_desc_image);
        nameText=findViewById(R.id.item_desc_name);
        soldText=findViewById(R.id.item_desc_sold);
        favText=findViewById(R.id.favorite_text);
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nameText.setText(Objects.requireNonNull(dataSnapshot.child("name").getValue(String.class)));
                soldText.setText(Objects.requireNonNull(dataSnapshot.child("sold").getValue(String.class)));
                url=dataSnapshot.child("image").getValue(String.class);
                Picasso.with(ViewItemActivity.this).load(url).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.pic_item)
                        .into(descImage, new Callback() {
                            @Override
                            public void onSuccess() {
                            }
                            @Override
                            public void onError() {
                                Picasso.with(ViewItemActivity.this).load(url).placeholder(R.drawable.pic_item).into(descImage);
                            }
                        });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        db=new DBHelper(this);
        if(db.isFav(id)){
            favText.setText("Remove From Favorites");
        }


    }

    public void closeAct(View view) {
        finish();
    }

    public void openEnquiry(View view) {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(ViewItemActivity.this);
        dialogView = ViewItemActivity.this.getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null);
        whatsappBtn = dialogView.findViewById(R.id.lin_whatsapp);
        appBtn = dialogView.findViewById(R.id.lin_app);
        appBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ViewItemActivity.this,EnquiryActivity.class).putExtra("id",id));
            }
        });
        whatsappBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBottomSheetDialog.setContentView(dialogView);
        mBottomSheetDialog.show();

    }
    public void addFav(View view) {
        if(favText.getText().toString().equalsIgnoreCase("Remove From Favorites")){
            db.removeFav(id);
            favText.setText("Add to favorites");
        }else{
            db.addFav(new DbItem(id,url,gos,type));
            favText.setText("Remove From Favorites");
        }
    }
}
