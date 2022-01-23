package nguyenhoanganhkhoa.com.myapplication.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DialogNotificationAdapter;
import nguyenhoanganhkhoa.com.customdialog.CustomBottomSheetFilter;
import nguyenhoanganhkhoa.com.customdialog.CustomBottomSheetFilterHistory;
import nguyenhoanganhkhoa.com.models.Notification;
import nguyenhoanganhkhoa.com.myapplication.R;

public class AllNotificationScreen extends AppCompatActivity {

    ImageView imvBackNotifications, imvFilterNotification;
    RadioButton radUnread, radAll, radTransaction, radFromAdmin;

    Fragment fragment = null;

    CustomBottomSheetFilterHistory bottomSheetFilter;

    SearchView searchView;


    private void linkView() {

        imvBackNotifications = findViewById(R.id.imvBackNotifications);

        radUnread = findViewById(R.id.radNotificationAllUnread);
        radAll = findViewById(R.id.radNotificationAllAll);
        radFromAdmin = findViewById(R.id.radFromAdmin);
        radTransaction = findViewById(R.id.radTransaction);

        imvFilterNotification = findViewById(R.id.imvFilterNotification);

        searchView = findViewById(R.id.svNotification);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notification_screen);

        linkView();

        inflateFragment();
        addSearchMethod();
        addEvents();

    }

    private void addSearchMethod() {
        if(radAll.isChecked()){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if(query.isEmpty()){
                        changeFragment(new AllNoticeFragment());
                        Log.d("TAG", "onQueryTextSubmit: empty" );
                    }
                    else{
                        changeFragment(new AllAllNoticeFragment());
                        Log.d("TAG", "onQueryTextSubmit: not empty" );
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if(newText.isEmpty()){
                        changeFragment(new AllNoticeFragment());
                        Log.d("TAG", "onQueryTextSubmit: empty" );
                    }
                    else{
                        changeFragment(new AllAllNoticeFragment());
                        Log.d("TAG", "onQueryTextSubmit: not empty" );

                    }
                    return false;
                }
            });
        }
    }

    private void inflateFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragment = new AllNoticeFragment();


        fragmentTransaction.add(R.id.layout_notification, fragment);
        fragmentTransaction.commit();
    }


    private void changeFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_notification, fragment);
        fragmentTransaction.commit();
    }

    private void addEvents() {
        imvFilterNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(bottomSheetFilter==null){
                     bottomSheetFilter = new CustomBottomSheetFilterHistory(AllNotificationScreen.this,R.style.BottomSheetDialogTheme,
                             R.layout.custom_bottomdialog_filter);

                 }
                bottomSheetFilter.show();
            }
        });


        imvBackNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

        radAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    changeFragment(new AllNoticeFragment());
                    addSearchMethod();
                }
            }
        });
        radUnread.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    changeFragment(new UnreadFNoticeFragment());
                }
            }
        });

        radTransaction.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    changeFragment(new TransNoticeFragment());
                }
            }
        });

        radFromAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    changeFragment(new FromAdminNoticeFragment());
                }
            }
        });


    }



}