package nguyenhoanganhkhoa.com.myapplication.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import nguyenhoanganhkhoa.com.customdialog.CustomDialog;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.forgotpass.CreateNewPasswordScreen;
import nguyenhoanganhkhoa.com.myapplication.forgotpass.ResetPasswordScreen;
import nguyenhoanganhkhoa.com.myapplication.login.LoginScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class EnterNewPasswordScreen extends AppCompatActivity {
    EditText edtNewPassword, edtConfirmPassword;
    ImageView imgPasswordToggle1, imgPasswordToggle2, imvComeback;
    TextView txtErrorChangePass, txtErrorConfirmPass,txtVerificationCode;
    Button btnUpdate;

    ReusedConstraint reusedConstraint = new ReusedConstraint(EnterNewPasswordScreen.this);

    private void linkView() {
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        imgPasswordToggle1 = findViewById(R.id.imgToggleClose1);
        imgPasswordToggle2 = findViewById(R.id.imgToggleClose2);
        txtErrorChangePass = findViewById(R.id.txtErrorChangePass);
        txtErrorConfirmPass = findViewById(R.id.txtErrorConfirmPass);
        btnUpdate = findViewById(R.id.btnUpdate);
        imvComeback = findViewById(R.id.imvComebackCreateNewPassword);
        txtVerificationCode = findViewById(R.id.txtVerificationCode);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_password_screen);
        linkView();
        addEvents();
    }

    private void clearFocus(){
        edtConfirmPassword.clearFocus();
        edtNewPassword.clearFocus();
    }

    private Boolean validatePassword(){

        String passwordInput = edtNewPassword.getText().toString().trim();

        if(passwordInput.isEmpty())
        {
            txtErrorChangePass.setTextSize(15);
            reusedConstraint.setCustomColor(edtNewPassword,R.drawable.edt_custom_error,R.color.red,R.color.red);
            imgPasswordToggle1.setImageTintList(getResources().getColorStateList(R.color.red));
            txtErrorChangePass.setText(R.string.field_cannot_be_empty);
            edtNewPassword.setHintTextColor(getColor(R.color.red));
            return false;

        }

        else if(!AppUtil.PASSWORD_PATTERN.matcher(passwordInput).matches())
        {
            if(passwordInput.length()<=8)
            {
                txtErrorChangePass.setText(R.string.your_password_is_too_weak);

            }
            else if(passwordInput.length()>15)
            {
                txtErrorChangePass.setText(R.string.your_password_is_too_long);
            }
            txtErrorChangePass.setTextSize(15);
            reusedConstraint.setCustomColor(edtNewPassword,R.drawable.edt_custom_error,R.color.red,R.color.red);
            imgPasswordToggle1.setImageTintList(getResources().getColorStateList(R.color.red));

            btnUpdate.setBackground(getDrawable(R.drawable.button_login_block));
            btnUpdate.setTextColor(getColor(R.color.xamBlcok));
            btnUpdate.setEnabled(false);
            return false;
        }
        else {
            reusedConstraint.setCustomColor(edtNewPassword,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            imgPasswordToggle1.setImageTintList(getResources().getColorStateList(R.color.black80));
            txtErrorChangePass.setText(null);
            txtErrorChangePass.setTextSize(0);
            edtNewPassword.setHintTextColor(getColor(R.color.xamChu));
            btnUpdate.setEnabled(true);
            btnUpdate.setBackground(getDrawable(R.drawable.custom_button));
            btnUpdate.setTextColor(getColor(R.color.blackUI));

            return true;

        }
    }
    private Boolean validateConfirmPassword(){
        String password = edtConfirmPassword.getText().toString().trim();

        if (password.isEmpty()){
            txtErrorConfirmPass.setText(R.string.field_cannot_be_empty);

            txtErrorConfirmPass.setTextSize(15);
            reusedConstraint.setCustomColor(edtConfirmPassword,R.drawable.edt_custom_error,R.color.red,R.color.red);
            edtConfirmPassword.setHintTextColor(getColor(R.color.red));

            imgPasswordToggle2.setImageTintList(getResources().getColorStateList(R.color.red));
            return false;
        }

        else if(!password.equals(edtNewPassword.getText().toString().trim()))
        {
            txtErrorConfirmPass.setText(R.string.your_password_must_be_match);

            txtErrorConfirmPass.setTextSize(15);
            reusedConstraint.setCustomColor(edtConfirmPassword,R.drawable.edt_custom_error,R.color.red,R.color.red);
            edtConfirmPassword.setHintTextColor(getColor(R.color.red));

            imgPasswordToggle2.setImageTintList(getResources().getColorStateList(R.color.red));
            return false;
        }

        else {

            reusedConstraint.setCustomColor(edtConfirmPassword,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            edtConfirmPassword.setHintTextColor(getColor(R.color.xamChu));

            imgPasswordToggle2.setImageTintList(getResources().getColorStateList(R.color.black80));

            txtErrorConfirmPass.setText(null);
            txtErrorConfirmPass.setTextSize(0);
            return true;
        }

    }
    private Boolean validateConfirmPasswordTextChange(){
        String password = edtConfirmPassword.getText().toString().trim();

        if (password.isEmpty()){
            txtErrorConfirmPass.setText(R.string.field_cannot_be_empty);
            txtErrorConfirmPass.setTextSize(15);
            reusedConstraint.setCustomColor(edtConfirmPassword,R.drawable.edt_custom_error,R.color.red,R.color.red);
            imgPasswordToggle2.setImageTintList(getColorStateList(R.color.red));
            edtConfirmPassword.setHintTextColor(getColor(R.color.red));

            return false;
        }

        else {

            reusedConstraint.setCustomColor(edtConfirmPassword,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            edtConfirmPassword.setHintTextColor(getColor(R.color.xamChu));
            imgPasswordToggle2.setImageTintList(getColorStateList(R.color.black80));


            txtErrorConfirmPass.setText(null);
            txtErrorConfirmPass.setTextSize(0);
            return true;
        }

    }

    private void addEvents() {

        imvComeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edtNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validatePassword();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateConfirmPasswordTextChange();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imgPasswordToggle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.showHidePassword(edtNewPassword,view);

            }
        });

        imgPasswordToggle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.showHidePassword(edtConfirmPassword,view);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(!validatePassword() | !validateConfirmPassword())) {
                    updatePasswordToFirebase();
                }
                clearFocus();
            }
        });
    }

    private void updatePasswordToFirebase(){
        String pass = edtNewPassword.getText().toString().trim();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    databaseReference.child(AppUtil.FB_PASSWORD).setValue(pass);
                    openDialog();
                }
                catch (Exception e){
                    Log.d("Error", "Failed to set password to firebase: " + e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "Failed to set password to firebase: " + error);
            }
        });
    }

    private void openDialog(){
        CustomDialog customDialog = new CustomDialog(EnterNewPasswordScreen.this, R.layout.custom_dialog_update_password_inhome);
        customDialog.btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterNewPasswordScreen.this, SettingScreen.class);
                AppUtil.SIGNAL_COMEBACK_FOR_SETTING = AppUtil.SIGNAL_TO_HOME;
                startActivity(intent);
            }
        });
        customDialog.show();
    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("account")
            .child(AppUtil.DATA_OBJECT).child(AppUtil.USERNAME_AFTER_LOGGIN);
}