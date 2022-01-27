package nguyenhoanganhkhoa.com.myapplication.home.topup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class TopUpMainScreen extends AppCompatActivity {
    ImageView imvBack;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_main_screen);

        linkView();
        addFragment();
        addEvents();
        reusedConstraint.openNav(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        if(drawerLayout.isDrawerVisible(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }
        else{
            if(getFragmentManager().getBackStackEntryCount()>0) {
                getFragmentManager().popBackStack();
            }
            else TopUpMainScreen.super.onBackPressed();
        }

    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getFragmentManager().getBackStackEntryCount()>0) {
                    getFragmentManager().popBackStack();
                }
                else TopUpMainScreen.super.onBackPressed();
            }
        });
    }

    private void addFragment() {
        FragmentManager fragmentManager;
        fragmentManager = getSupportFragmentManager();
        try {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            TopUpWalletFragment topUpWalletFragment = new TopUpWalletFragment();

            fragmentTransaction.add(R.id.lnFragmentRelace, topUpWalletFragment);
            fragmentTransaction.commit();
        }
        catch (Exception e) {
            Log.d("Error", "Fail to addFragment in TopUpScreen: " + e);
        }

    }

    private void linkView() {
        imvBack = findViewById(R.id.imvBackTopUpMain);
    }
}