package nguyenhoanganhkhoa.com.myapplication.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DeviceAdapter;
import nguyenhoanganhkhoa.com.adapter.HistoryAdapter;
import nguyenhoanganhkhoa.com.customdialog.CustomDialogTwoButton;
import nguyenhoanganhkhoa.com.models.Devices;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.login.LoginScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class LoginSettingScreen extends AppCompatActivity {

    ImageView imvSettingBack, imvAvatar;
    public Button btnSignOutAllDevices;

    TextView txtName;
    RecyclerView rcvDevices;

    boolean isSignOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_setting_screen);
        linkView();
        getDataFromFirebase();
        initAdapter(getListDevices());
        addEvents();

    }

    private void initAdapter(List<Devices> list) {
        try {
            DeviceAdapter deviceAdapter = new DeviceAdapter(this,R.layout.item_device);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
            rcvDevices.setLayoutManager(linearLayoutManager);
            deviceAdapter.setData(list);
            rcvDevices.setAdapter(deviceAdapter);
        }

        catch (Exception e){
            Log.d("Error", "Cannot load adapter in LoginSettingScreen: " + e);
        }


    }

    private List<Devices> getListDevices() {
        List<Devices> list = new ArrayList<>();
        isSignOut = false;
        list.add(new Devices(R.drawable.img_iphone,"iPhone 8 plus","Iphone",
                "Wednesday, Dec 01, 2021 at 00:04:37 in Thu Duc, HCMC",true));
        list.add(new Devices(R.drawable.img_iphone,"iPhone 8 plus","Iphone",
                "Friday, Jul 06, 2021 at 17:07:58 in California, United States",false));
        list.add(new Devices(R.drawable.img_iphone,"Samsung Galazy Z Flip3 5G","Iphone",
                null,false));
         return list;
    }

    private void addEvents() {
        btnSignOutAllDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isSignOut){
                    CustomDialogTwoButton customDialogTwoButton = new CustomDialogTwoButton(LoginSettingScreen.this,R.layout.custom_dialog_signout_all);
                    customDialogTwoButton.btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            initAdapter(getListDeviceAfterSignOut());
                            customDialogTwoButton.dismiss();
                        }
                    });
                    customDialogTwoButton.btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            customDialogTwoButton.dismiss();
                        }
                    });
                    customDialogTwoButton.show();
                }
                else{
                    Toast.makeText(LoginSettingScreen.this,"You have already sign out all devices", Toast.LENGTH_SHORT).show();
                }

            }
        });
        imvSettingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void linkView() {
        btnSignOutAllDevices=findViewById(R.id.btnSignOutAllDevices);
        imvSettingBack=findViewById(R.id.imvSettingBack);
        imvAvatar=findViewById(R.id.imvAvatar);
        txtName=findViewById(R.id.txtName);
        rcvDevices=findViewById(R.id.rcvDevices);
    }

    private List<Devices> getListDeviceAfterSignOut(){
        List<Devices> list = new ArrayList<>();
        list.add(getListDevices().get(0));
        isSignOut = true;
        return list;
    }

    public void getDataFromFirebase(){
        AppUtil.databaseReference.child(AppUtil.DATA_OBJECT).child(AppUtil.USERNAME_AFTER_LOGGIN)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            String fullname = snapshot.child(AppUtil.FB_FULLNAME).getValue(String.class);
                            String uri = snapshot.child(AppUtil.FB_IMAGES_BITMAP).getValue(String.class);
                            if(uri.isEmpty()|uri.equals("Null")){
                                long avatar = snapshot.child(AppUtil.FB_AVATAR).getValue(Long.class);
                                int idAva = Integer.parseInt(String.valueOf(avatar));
                                imvAvatar.setImageResource(idAva);
                            }
                            else{
                                if(getApplicationContext()!=null){
                                    Glide.with(getApplicationContext()).load(uri).into(imvAvatar);
                                }
                            }
                            txtName.setText(fullname);
                        }
                        catch (Exception e){
                            Log.d("Error", "Fail to set value to views in LoginSettingScreen " + e);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("Error", "Fail to load info in: " + error.toString());
                    }
                });
    }


}