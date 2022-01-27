package nguyenhoanganhkhoa.com.myapplication.home.transfer;

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

public class TransferMain2Screen extends AppCompatActivity {
    ImageView imvBack;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_main2_screen);
        linkView();
        addFragment();
        addEvents();
        reusedConstraint.openNav(this);
    }

    private void linkView() {
        imvBack = findViewById(R.id.imvBackTopUpMain);

    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getFragmentManager().getBackStackEntryCount()>0) {
                    getFragmentManager().popBackStack();
                }
                else TransferMain2Screen.super.onBackPressed();
            }
        });
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
            else TransferMain2Screen.super.onBackPressed();
        }
    }

    private void addFragment() {
        FragmentManager fragmentManager;
        fragmentManager = getSupportFragmentManager();
        try {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


            fragmentTransaction.add(R.id.lnFragmentReplaceTransfer, new TransferMoneyFragment());
            fragmentTransaction.commit();
        }
        catch (Exception e) {
            Log.d("Error", "Fail to addFragment in TransferMainScreen: " + e);
        }
    }
}