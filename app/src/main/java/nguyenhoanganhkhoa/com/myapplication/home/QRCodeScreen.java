package nguyenhoanganhkhoa.com.myapplication.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class QRCodeScreen extends AppCompatActivity {

    ImageView imvQRCodeScan, imvAvatarQRCode;
    TextView txtSecondUpdateQRCode, txtName, txtMoneyDisplay;
    View viewHoldImage;

    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_screen);

        linkView();
        getDataFromFirebase();
        displayBalanceFromFirebase();
        addEvents();

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
                                imvAvatarQRCode.setImageResource(idAva);
                            }
                            else{
                                if(getApplicationContext()!=null){
                                    Glide.with(getApplicationContext()).load(uri).into(imvAvatarQRCode);
                                }
                                else{
                                    Toast.makeText(QRCodeScreen.this,"Cannot get Application context",Toast.LENGTH_SHORT).show();
                                }
                            }
                            txtName.setText(fullname);
                        }
                        catch (Exception e){
                            Log.d("Error", "Fail to load value to views in QRCodeScreen " + e);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("Error", "Fail to load info in: "  + error.toString());
                    }
                });
    }

    private void addEvents() {
        reusedConstraint.addTimer(txtSecondUpdateQRCode,45000);
        viewHoldImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QRCodeScreen.this,TopUpMainScreen.class);
                startActivity(intent);
            }
        });


        imvQRCodeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void linkView() {
        imvQRCodeScan = findViewById(R.id.imvQRCodeScan);
        txtSecondUpdateQRCode= findViewById(R.id.txtSecondUpdateQRCode);
        viewHoldImage = findViewById(R.id.viewHoldImage);
        imvAvatarQRCode = findViewById(R.id.imvAvatarQRCode);
        txtName = findViewById(R.id.txtName);
        txtMoneyDisplay = findViewById(R.id.txtMoneyDisplay);
    }

    private void displayBalanceFromFirebase(){
        AppUtil.databaseReference.child(AppUtil.DATA_OBJECT).child(AppUtil.USERNAME_AFTER_LOGGIN)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long balance = snapshot.child(AppUtil.FB_BALANCE).getValue(Long.class);
                        double balanceDisplay = Double.parseDouble(String.valueOf(balance));
                        txtMoneyDisplay.setText(reusedConstraint.formatCurrency(balanceDisplay));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("Error", "Fail to load info in QRCode Screen" + error.toString());
                    }
                });
    }
}