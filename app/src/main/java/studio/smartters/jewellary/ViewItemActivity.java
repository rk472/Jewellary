package studio.smartters.jewellary;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ViewItemActivity extends AppCompatActivity {
    private View dialogView;
    private LinearLayout whatsappBtn,appBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
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
                startActivity(new Intent(ViewItemActivity.this,EnquiryActivity.class));
            }
        });
        mBottomSheetDialog.setContentView(dialogView);
        mBottomSheetDialog.show();
    }
    public void addFav(View view) {
    }
}
