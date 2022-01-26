package nguyenhoanganhkhoa.com.myapplication.home.transfer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import nguyenhoanganhkhoa.com.myapplication.R;

public class TransferMain2Screen extends AppCompatActivity {
    ImageView imvBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_main2_screen);
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
                else TransferMain2Screen.super.onBackPressed();
            }
        });
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