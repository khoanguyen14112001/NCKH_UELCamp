package nguyenhoanganhkhoa.com.myapplication.home.helpcenter;

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

public class HelpCenterDetailScreen extends AppCompatActivity {

    ImageView imvBack;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center_detail_screen);

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
            else HelpCenterDetailScreen.super.onBackPressed();
        }

    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getFragmentManager().getBackStackEntryCount()>0) {
                    getFragmentManager().popBackStack();
                }
                else HelpCenterDetailScreen.super.onBackPressed();
            }
        });
    }

    private void linkView() {
        imvBack = findViewById(R.id.imvBack);
    }

    private void addFragment() {
        FragmentManager fragmentManager;
        fragmentManager = getSupportFragmentManager();
        try {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            HelpCenterOutFragment fragment = new HelpCenterOutFragment();

            fragmentTransaction.add(R.id.layout_fragment, fragment);
            fragmentTransaction.commit();
        }
        catch (Exception e){
            Log.d("Error", "Fail to add fragment in HelpCenterScreen: " + e);
        }

    }


}