package studio.smartters.jewellary;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private String id,gos,type,url,sold,name;
    private ImageView descImage;
    private TextView nameText,soldText,favText;
    private DatabaseReference itemRef;
    private DBHelper db;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        id= Objects.requireNonNull(getIntent().getExtras()).getString("id"," ");
        gos=Objects.requireNonNull(getIntent().getExtras()).getString("gos"," ");
        type=Objects.requireNonNull(getIntent().getExtras()).getString("type"," ");
        itemRef= FirebaseDatabase.getInstance().getReference().child("items").child(gos).child(type);
        itemRef.keepSynced(true);
        descImage=findViewById(R.id.item_desc_image);
        nameText=findViewById(R.id.item_desc_name);
        soldText=findViewById(R.id.item_desc_sold);
        favText=findViewById(R.id.favorite_text);
        linearLayout=findViewById(R.id.lin_fav);
        linearLayout.setEnabled(false);
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(id)){
                    itemRef.child(id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            name=Objects.requireNonNull(dataSnapshot.child("name").getValue(String.class));
                            nameText.setText(name);
                            sold=Objects.requireNonNull(dataSnapshot.child("sold").getValue(String.class));
                            soldText.setText(sold);
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
                            linearLayout.setEnabled(true);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{
                    Toast.makeText(ViewItemActivity.this, "The product is no longer available", Toast.LENGTH_SHORT).show();
                    finish();
                }
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
                startActivity(new Intent(ViewItemActivity.this,EnquiryActivity.class).putExtra("id",id).putExtra("gos",gos).putExtra("type",type));
            }
        });
        whatsappBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("https://api.whatsapp.com/send?phone=+919439095207&text=Hii%20....I%20want%20to%20know%20about%20the%20Prduct%20"+gos+":"+type+",id:"+id));
                startActivity(i);
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
            db.addFav(new DbItem(id,url,gos,type,name,sold));
            favText.setText("Remove From Favorites");
        }
    }
}
