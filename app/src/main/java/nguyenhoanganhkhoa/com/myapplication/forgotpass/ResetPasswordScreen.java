package nguyenhoanganhkhoa.com.myapplication.forgotpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.login.LoginScreen;
import nguyenhoanganhkhoa.com.myapplication.signup.EmailScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

public class ResetPasswordScreen extends AppCompatActivity {
    EditText edtPhone;
    TextView txtErrorPhone;
    Button btnSendPassword;
    ImageView imvRestBack;

    private void linkView() {
        edtPhone = findViewById(R.id.edtPhone);
        txtErrorPhone = findViewById(R.id.txtErrorPhone);
        btnSendPassword = findViewById(R.id.btnSendPassword);
        imvRestBack = findViewById(R.id.imvResetBack);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_screen);
        linkView();
        addCondition();
        addEvents();

    }

    private void addCondition() {
        String s = AppUtil.eMessage;

        if(s.equals(AppUtil.LOCK_CONDITION_FORGOTPASS))
            addTimerLock();
        else if (s.equals(""))
            return;

    }
    private void addTimerLock() {


        new CountDownTimer(1800000, 10) { //Set Timer for 5 seconds
            public void onTick(long millisUntilFinished) {
                btnSendPassword.setBackground(getDrawable(R.drawable.button_login_block));
                btnSendPassword.setTextColor(getColor(R.color.xamBlcok));
                edtPhone.setBackground(getDrawable(R.drawable.edt_custom_block));
                edtPhone.setHintTextColor(getColor(R.color.xamChu));
                edtPhone.setCompoundDrawableTintList(getColorStateList(R.color.xamBlockIcon));
                btnSendPassword.setEnabled(false);
                edtPhone.setEnabled(false);

            }

            @Override
            public void onFinish() {
                setCustomColor(edtPhone,R.drawable.custom_edt,R.color.blackUI,R.color.blackUI);
                edtPhone.setHintTextColor(getColor(R.color.xamChu));
                btnSendPassword.setEnabled(true);
                btnSendPassword.setBackground(getDrawable(R.drawable.custom_button));
                btnSendPassword.setTextColor(getColor( R.color.blackUI));
                edtPhone.setEnabled(true);
                AppUtil.eMessage="";
            }
        }.start();
    }




    private void setCustomColor(EditText edtCanSua, int edtColor, int iconColor, int textColor)
    {
        // Ch???nh m??u cho thanh edit text khi g???p error, focus, ...

        edtCanSua.setBackground(getDrawable(edtColor));
        edtCanSua.setCompoundDrawableTintList(getColorStateList(iconColor));
        edtCanSua.setTextColor(getColorStateList(textColor));
    }


    private Boolean validatePhoner(){
        String s = edtPhone.getText().toString().trim();

        if (s.isEmpty()){
            txtErrorPhone.setText(R.string.field_cannot_be_empty);
            txtErrorPhone.setTextSize(15);
            setCustomColor(edtPhone,R.drawable.edt_custom_error,R.color.red,R.color.red);
            edtPhone.setHintTextColor(getColor(R.color.red));
            return false;
        }


        else {
            setCustomColor(edtPhone,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            edtPhone.setHintTextColor(getColor(R.color.xamChu));

            txtErrorPhone.setText(null);
            txtErrorPhone.setTextSize(0);

            btnSendPassword.setEnabled(true);
            btnSendPassword.setBackground(getDrawable(R.drawable.custom_button));
            btnSendPassword.setTextColor(getColor( R.color.blackUI));
            return true;
        }

    }
    private void addEvents() {
        btnSendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate password v?? email
                if(!validatePhoner()){
                    edtPhone.clearFocus();
                }


                else{
                    if(!edtPhone.getText().toString().trim().equals(AppUtil.PHONE_APP))
                    {
                        txtErrorPhone.setText(R.string.your_phone_does_not_exists);
                        txtErrorPhone.setTextSize(15);
                        setCustomColor(edtPhone,R.drawable.edt_custom_error,R.color.red,R.color.red);
                    }

                    else{
                        Intent intent = new Intent(ResetPasswordScreen.this, VerificationScreen.class);
                        startActivity(intent);
                        setCustomColor(edtPhone,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
                    }
                }





            }
        });

        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validatePhoner();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imvRestBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ph???i startActivity ki???u nh?? v???y th?? l??c m??? c??i m??n h??nh nh???p phone, b???m tr??? v??? xong
                // b???m v?? l???i th?? n?? m???i ti???p t???c countdown c??i timer m?? kh??ng b??? crash.

                Intent intent = new Intent(ResetPasswordScreen.this, LoginScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ResetPasswordScreen.this, LoginScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}