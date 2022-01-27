package nguyenhoanganhkhoa.com.myapplication.home.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import nguyenhoanganhkhoa.com.custom.bottomsheetdialog.CustomBottomSheetComponent;
import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogTwoButton;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.aboutus.AboutUsScreen;
import nguyenhoanganhkhoa.com.myapplication.home.homepage.HomePageScreen;
import nguyenhoanganhkhoa.com.myapplication.login.LoginScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class SettingScreen extends AppCompatActivity {
    ImageView imvSettingBack ;
    LinearLayout lnSignOutSetting, lnInfoApp;
    TextView txtLoginSetting, txtSetTime, txtLanguage,txtMinutes, txtSetAPass;
    CustomBottomSheetComponent bottomSheetDialogLanguage = null;
    CustomBottomSheetComponent bottomSheetDialogTime = null;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    TextView txtLanguageSpec;
    private void linkView() {
        imvSettingBack = findViewById(R.id.imvSettingBack);
        lnSignOutSetting= findViewById(R.id.lnSignoutSetting);
        txtLoginSetting = findViewById(R.id.txtLoginSetting);
        txtLanguage=findViewById(R.id.txtLanguage);
        txtSetTime=findViewById(R.id.txtSetTime);
        txtMinutes=findViewById(R.id.txtMinutes);
        txtLanguageSpec=findViewById(R.id.txtLanguageSpec);
        txtSetAPass=findViewById(R.id.txtSetAPass);
        lnInfoApp=findViewById(R.id.lnInfoApp);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);
        linkView();
        addEvents();
        reusedConstraint.openNav(this);
    }

    private void addEvents() {
        lnInfoApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingScreen.this, AboutUsScreen.class));
            }
        });
        txtSetAPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingScreen.this, SetNewPasswordScreen.class));
            }
        });
        txtMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBottomSheetTime();
            }
        });
        lnSignOutSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogTwoButton customDialogTwoButton = new CustomDialogTwoButton(SettingScreen.this,R.layout.custom_dialog_signout);
                customDialogTwoButton.btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SettingScreen.this, LoginScreen.class);
                        startActivity(intent);
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
        });
        txtLanguageSpec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBottomSheetLanguage();
            }
        });

        imvSettingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AppUtil.SIGNAL_COMEBACK_FOR_SETTING == AppUtil.SIGNAL_TO_HOME){
                    startActivity(new Intent(SettingScreen.this, HomePageScreen.class));
                    AppUtil.SIGNAL_COMEBACK_FOR_SETTING = 0;
                }
                else{
                    AppUtil.SIGNAL_COMEBACK_FOR_SETTING = 0;
                    finish();
                }
            }
        });

        txtLoginSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingScreen.this, LoginSettingScreen.class);
                startActivity(intent);
            }
        });
        txtLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBottomSheetLanguage();
            }
        });
        txtSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBottomSheetTime();
            }
        });
    }


    private void createBottomSheetTime() {
        if(bottomSheetDialogTime ==null){
            bottomSheetDialogTime = new CustomBottomSheetComponent(SettingScreen.this,R.style.BottomSheetDialogTheme,R.layout.custom_bottomdialog_time);
        }
        bottomSheetDialogTime.show();

    }

    private void createBottomSheetLanguage() {
        if(bottomSheetDialogLanguage ==null){
            bottomSheetDialogLanguage = new CustomBottomSheetComponent(SettingScreen.this,R.style.BottomSheetDialogTheme,R.layout.custom_bottomdialog_language);
        }
        bottomSheetDialogLanguage.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        if(drawerLayout.isDrawerVisible(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }
        else{
            if(AppUtil.SIGNAL_COMEBACK_FOR_SETTING == AppUtil.SIGNAL_TO_HOME){
                startActivity(new Intent(SettingScreen.this,HomePageScreen.class));
                AppUtil.SIGNAL_COMEBACK_FOR_SETTING = 0;
            }
            else{
                AppUtil.SIGNAL_COMEBACK_FOR_SETTING = 0;
                finish();
            }
        }

    }
}