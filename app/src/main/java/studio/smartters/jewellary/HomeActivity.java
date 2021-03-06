package studio.smartters.jewellary;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import studio.smartters.jewellary.Fragments.AboutFragment;
import studio.smartters.jewellary.Fragments.CatagoryFragment;
import studio.smartters.jewellary.Fragments.ContactFragment;
import studio.smartters.jewellary.Fragments.FavoriteFragment;
import studio.smartters.jewellary.Fragments.GalleryFragment;
import studio.smartters.jewellary.Fragments.HomeFragment;
import studio.smartters.jewellary.Fragments.ShowItemsFragment;

public class HomeActivity extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            String tag = "other";
            Fragment f = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    f = new HomeFragment();
                    tag = "home";
                    break;
                case R.id.navigation_fav:
                    f = new FavoriteFragment();
                    break;
                case R.id.navigation_gallery:
                    f = new GalleryFragment();
                    break;
                case R.id.navigation_contact:
                    f = new ContactFragment();
                    break;
                case R.id.navigation_about:
                    f = new AboutFragment();
                    break;
            }

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, f, tag);
            fragmentTransaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new HomeFragment(), "home");
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentById(R.id.main_container).getTag().equals("home"))
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("Do You really want to Exit ?")
                    .setPositiveButton("Yes, Sure", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HomeActivity.super.onBackPressed();
                        }
                    }).setNegativeButton("No, Don't", null).show();

        else if (getSupportFragmentManager().findFragmentById(R.id.main_container).getTag().equals("other")) {
            Fragment f = new HomeFragment();
            navigation.setSelectedItemId(R.id.navigation_home);
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, f, "home");
            fragmentTransaction.commit();
        } else if (getSupportFragmentManager().findFragmentById(R.id.main_container).getTag().equals("item_show")) {
            Fragment fragment = new CatagoryFragment();
            Bundle bundle = new Bundle();
            bundle.putString("gors", ShowItemsFragment.myData);
            fragment.setArguments(bundle);
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, fragment, "cat")
                    .commit();
        } else if (getSupportFragmentManager().findFragmentById(R.id.main_container).getTag().equals("cat")) {
            Fragment f = new HomeFragment();
            navigation.setSelectedItemId(R.id.navigation_home);
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, f, "home");
            fragmentTransaction.commit();
        }
    }

    public void map(View v) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=20.614708,86.3987593"));
        startActivity(intent);
    }

    public void whatsApp(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=+919439095207&text=Hii%20....I%20want%20to%20know%20about%20Subhadra%20Jewellers"));
        startActivity(i);
    }

    public void call(View v) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
            return;
        }
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:+917008576627"));
        startActivity(i);
    }

    public void mail(View v) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"javatech04@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Inquiry");
        i.putExtra(Intent.EXTRA_TEXT, "Hello I want to know about JT... !");
        i.setType("message/rfc822");
        startActivity(Intent.createChooser(i, "Send Email"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:+917008576627"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(i);
    }
}
