package nguyenhoanganhkhoa.com.myapplication.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nguyenhoanganhkhoa.com.customdialog.CustomDialog;
import nguyenhoanganhkhoa.com.myapplication.signup.EmailScreen;
import nguyenhoanganhkhoa.com.myapplication.home.HomePageScreen;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.forgotpass.ResetPasswordScreen;
import nguyenhoanganhkhoa.com.myapplication.signup.PersonalInformationSetScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;


public class LoginScreen extends AppCompatActivity {

    EditText edtUsername,edtPassword;
    Button btnLogin,btnOK;
    TextView txtErrorPassword, txtErrorUsername,txtErrorLogTooMuch,txtForgotPassword,txtSignUp;
    ImageView imgPasswordToggleClose;

    ReusedConstraint reusedConstraint = new ReusedConstraint(LoginScreen.this);

    String username, password;

    private void linkView() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnOK = findViewById(R.id.btnOK);
        txtErrorUsername = findViewById(R.id.txtErrorUsername);
        txtErrorPassword = findViewById(R.id.txtErrorPassword);
        imgPasswordToggleClose = findViewById(R.id.imgToggleClose);
        txtErrorLogTooMuch = findViewById(R.id.txtErrorLogTooMuch);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        txtSignUp = findViewById(R.id.txtSignUp);
    }



    private Boolean validateUsername(){
        String username = edtUsername.getText().toString().trim();
        if (username.isEmpty()){

            txtErrorUsername.setText(R.string.field_cannot_be_empty);
            txtErrorUsername.setTextSize(15);
            edtUsername.setHintTextColor(getColor(R.color.red));
            reusedConstraint.setCustomColor(edtUsername,R.drawable.edt_custom_error,R.color.red,R.color.red);
            return false;
        }

//

        else {
            reusedConstraint.setCustomColor(edtUsername,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            edtUsername.setHintTextColor(getColor(R.color.xamChu));

            txtErrorUsername.setText(null);
            txtErrorUsername.setTextSize(0);
            return true;
        }

    }



    private Boolean validateUsernameButton(){
        String username = edtUsername.getText().toString().trim();
        if (username.isEmpty()){

            txtErrorUsername.setText(R.string.field_cannot_be_empty);
            txtErrorUsername.setTextSize(15);
            edtUsername.setHintTextColor(getColor(R.color.red));
            reusedConstraint.setCustomColor(edtUsername,R.drawable.edt_custom_error,R.color.red,R.color.red);
            return false;
        }
        // ("[a-zA-Z0-9-_]+")

        if(!username.matches("[a-zA-Z0-9-]+")){
            txtErrorUsername.setText(R.string.username_must_not_contain);
            txtErrorUsername.setTextSize(15);
            edtUsername.setHintTextColor(getColor(R.color.red));
            reusedConstraint.setCustomColor(edtUsername,R.drawable.edt_custom_error,R.color.red,R.color.red);
            return false;
        }

//

        else {
            reusedConstraint.setCustomColor(edtUsername,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            edtUsername.setHintTextColor(getColor(R.color.xamChu));

            txtErrorUsername.setText(null);
            txtErrorUsername.setTextSize(0);
            return true;
        }

    }
    private Boolean validatePassword(){
        String password = edtPassword.getText().toString().trim();

        if (password.isEmpty()){
            txtErrorPassword.setText(R.string.field_cannot_be_empty);
            txtErrorPassword.setTextSize(15);
            reusedConstraint.setCustomColor(edtPassword,R.drawable.edt_custom_error,R.color.red,R.color.red);
            imgPasswordToggleClose.setImageTintList(getColorStateList(R.color.red));
            edtPassword.setHintTextColor(getColor(R.color.red));

            return false;
        }

        else {

            reusedConstraint.setCustomColor(edtPassword,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            edtPassword.setHintTextColor(getColor(R.color.xamChu));
            imgPasswordToggleClose.setImageTintList(getColorStateList(R.color.black80));


            txtErrorPassword.setText(null);
            txtErrorPassword.setTextSize(0);
            return true;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        linkView();
        addEvents();
        PersonalInformationSetScreen.selectedFaculty = 0;
        AppUtil.USERNAME_AFTER_LOGGIN = "";

    }


    int attempt = 1;
    int trytime = 0;






    private void addEvents() {

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, EmailScreen.class);
                startActivity(intent);
            }
        });
        edtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateUsername();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtPassword.addTextChangedListener(new TextWatcher() {
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
        imgPasswordToggleClose.setOnClickListener(new View.OnClickListener() {
            // Ẩn hiện password
            @Override
            public void onClick(View view) {
                reusedConstraint.showHidePassword(edtPassword,view);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate password và email
                if( !validateUsernameButton()| !validatePassword()){
                    edtUsername.clearFocus();
                    edtPassword.clearFocus();
                }
                else {
                    validationAccount();
                }
            }
        });
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMainActivity = new Intent(LoginScreen.this, ResetPasswordScreen.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openMainActivity, 0);

            }
        });


    }

    private void checkSignin(boolean isValidAccount) {
        // Nếu lần thử > 4 --> bát user chờ 30s, tăng trytime (số lần bị khóa) lên 1 lần

        if(attempt > 3)
        {
            setAttempt(30000);
            trytime++;
            edtUsername.clearFocus();
            edtPassword.clearFocus();
        }
        // Nếu lần thử <= 4 --> thực hiện verify email và password

        else if(attempt <3| attempt ==3)
        {
            // Nếu đúng thì cho user đi tiếp, reset lại attemp và trytime

            if(isValidAccount)
            {
                Intent intent = new Intent(LoginScreen.this, HomePageScreen.class);
                startActivity(intent);
                attempt =1;
                trytime=0;
            }
            // Nếu sai thì bắt đầu làm tiếp như sau:
            else
            {
                // int attemp = 1; (Hiển thị dialog)
                // int trytime = 0; (khóa 30s)

                //LUỒNG THỨ TỰ
                // attemp = 3
                // trytime = 3


                // 4 lần attemp --> 1 lần try time
                // 3 lần trytime --> khóa màn hình luôn 30 PHÚT
                // Nếu số lần bị khóa < 3 lần --> tạo hộp thoại dialog thông báo sai password và email
                if(trytime<3)
                {
                    CustomDialog customDialog = new CustomDialog(LoginScreen.this,R.layout.custom_dialog_login);
                    customDialog.btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            customDialog.dismiss();
                        }
                    });
                    customDialog.show();
                    attempt++;
                    edtUsername.clearFocus();
                    edtPassword.clearFocus();
                }
                // Nếu lần bị khóa thứ 3 rồi, set time cho user phải chờ đến 1 tiếng, sau đó set trytime lại từ đầu

                if(trytime==3)
                {
                    setAttempt(3600000);
                    trytime = 0;
                    edtUsername.clearFocus();
                    edtPassword.clearFocus();
                }

            }

        }
    }


    private void setAttempt(int time) {
        // Nếu attemp(lần thử) > 3 --> block hết phần nhập của user và mở lại sau 30s
        btnLogin.setEnabled(false);
        edtUsername.setEnabled(false);
        edtPassword.setEnabled(false);
        imgPasswordToggleClose.setEnabled(false);

        edtPassword.setCompoundDrawableTintList(getColorStateList(R.color.xamBlcok));
        edtPassword.setBackground(getDrawable(R.drawable.edt_custom_block));

        edtPassword.setHintTextColor(getColor(R.color.xamChu));



        edtUsername.setCompoundDrawableTintList(getColorStateList(R.color.xamBlockIcon));
        edtUsername.setBackground(getDrawable(R.drawable.edt_custom_block));
        edtUsername.setHintTextColor(getColor(R.color.xamChu));

        btnLogin.setBackground(getDrawable(R.drawable.button_login_block));
        btnLogin.setTextColor(getColor(R.color.xamBlcok));

        // set time mở lại cho user
        new CountDownTimer(time, 10) {
            public void onTick(long millisUntilFinished) {
                long remainedSecs = millisUntilFinished / 1000;
                if(time ==30000) {
                    txtErrorLogTooMuch.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
                    txtErrorLogTooMuch.setText(getString(R.string.you_have_4_failed_login_attempts) + getString(R.string.try_again_in) + (remainedSecs % 60) + getString(R.string.seconds));
                }
                else
                {
                    txtErrorLogTooMuch.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
                    txtErrorLogTooMuch.setText(R.string.try_again_after_an_hour);
                }
            }


            @Override
            public void onFinish() {
                edtUsername.setEnabled(true);
                edtPassword.setEnabled(true);
                btnLogin.setEnabled(true);
                imgPasswordToggleClose.setEnabled(true);

                edtPassword.setCompoundDrawableTintList(getColorStateList(R.color.blackUI));
                edtPassword.setBackground(getDrawable(R.drawable.custom_edt));
                edtPassword.setHintTextColor(getResources().getColor(R.color.xamChu));

                btnLogin.setBackground(getDrawable(R.drawable.custom_button));
                btnLogin.setTextColor(getColor(R.color.blackUI));

                edtUsername.setCompoundDrawableTintList(getColorStateList(R.color.blackUI));
                edtUsername.setBackground(getDrawable(R.drawable.custom_edt));
                edtUsername.setHintTextColor(getColor(R.color.xamChu));

                txtErrorLogTooMuch.setText("");
                txtErrorLogTooMuch.setTextSize(0);
            }
        }.start();
        // Chỉnh lại attemp ở mức 1
        attempt = 1;

    }

    public void onBackPressed() {
        // Do nothing
    }

    private void validationAccount () {
        username = edtUsername.getText().toString().trim();
        password = edtPassword.getText().toString().trim();
        AppUtil.databaseReference.child(AppUtil.DATA_OBJECT).child(username)
                                 .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isValidAccount;
                String pass = "";
                try {
                    pass = snapshot.child(AppUtil.FB_PASSWORD).getValue(String.class);
                }
                catch (Exception e){
                    Log.d("Error", "Fail to get password from Firebase: " + e);
                }

                if(snapshot.exists())
                {
                    if(pass.equals(password)){
                        isValidAccount = true;
                        AppUtil.USERNAME_AFTER_LOGGIN = username;
                    }
                    else{
                        isValidAccount = false;
                    }
                }
                else {
                    isValidAccount = false;
                }
                checkSignin(isValidAccount);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}