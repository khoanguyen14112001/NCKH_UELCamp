package nguyenhoanganhkhoa.com.myapplication.home.transfer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import nguyenhoanganhkhoa.com.myapplication.R;

public class TransferMainScreen extends AppCompatActivity {
    ImageView imvBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_main_screen);
        linkView();
        addFragment();
        addEvents();

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
                else TransferMainScreen.super.onBackPressed();
            }
        });
    }

    private void addFragment() {
        FragmentManager fragmentManager;
        fragmentManager = getSupportFragmentManager();
        try {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            TransferHomeFragment transferHomeFragment = new TransferHomeFragment();

            fragmentTransaction.add(R.id.lnFragmentRelace, transferHomeFragment);
            fragmentTransaction.commit();
        }
        catch (Exception e) {
            Log.d("Error", "Fail to addFragment in TopUpScreen: " + e);
        }
    }
}