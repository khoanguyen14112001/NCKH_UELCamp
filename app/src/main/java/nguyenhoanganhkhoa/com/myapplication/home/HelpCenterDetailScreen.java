package nguyenhoanganhkhoa.com.myapplication.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import nguyenhoanganhkhoa.com.myapplication.R;

public class HelpCenterDetailScreen extends AppCompatActivity {

    ImageView imvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center_detail_screen);

        linkView();
        addFragment();
        addEvents();



    }


    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()>0) {
            getFragmentManager().popBackStack();
        }
        else HelpCenterDetailScreen.super.onBackPressed();
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