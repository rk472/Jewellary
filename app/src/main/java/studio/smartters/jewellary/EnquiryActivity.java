package studio.smartters.jewellary;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EnquiryActivity extends AppCompatActivity {
    private EditText nameText,numberText,descText;
    private String id,type,gos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        id=getIntent().getStringExtra("id");
        type=getIntent().getStringExtra("type");
        gos=getIntent().getStringExtra("gos");
        nameText=findViewById(R.id.enquiry_name);
        numberText=findViewById(R.id.enquiry_no);
        descText=findViewById(R.id.enquiry_desc);
    }

    public void closeAct(View view) {
        finish();
    }

    public void openEnquiry(View view) {
        String name=nameText.getText().toString().trim();
        String number=numberText.getText().toString().trim();
        String desc=descText.getText().toString().trim();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(number) || TextUtils.isEmpty(desc)){
            Toast.makeText(this, "All fields must be empty", Toast.LENGTH_SHORT).show();
        }else{
            final ProgressDialog p=new ProgressDialog(this);
            p.setCancelable(false);
            p.setMessage("Please Wait");
            p.show();
            Map<String,Object> m=new HashMap<>();
            m.put("name",name);
            m.put("number",number);
            m.put("desc",desc);
            m.put("gos",gos);
            m.put("type",type);
            m.put("id",id);
            FirebaseDatabase.getInstance().getReference().child("enquiry").updateChildren(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    p.dismiss();
                    Toast.makeText(EnquiryActivity.this, "Enquiry successfully submitted...\nThank you for your interest", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(EnquiryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    p.dismiss();
                }
            });
        }
    }

    public void cancelEnq(View view) {
        finish();
    }
}
