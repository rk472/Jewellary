package studio.smartters.jewellary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EnquiryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
    }

    public void closeAct(View view) {
        finish();
    }

    public void openEnquiry(View view) {

    }

    public void cancelEnq(View view) {
        finish();
    }
}
