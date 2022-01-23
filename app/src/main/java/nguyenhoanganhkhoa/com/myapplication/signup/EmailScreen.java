package nguyenhoanganhkhoa.com.myapplication.signup;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.login.LoginScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class EmailScreen extends AppCompatActivity {
    EditText edtEmail;
    TextView txtErrorEmail,txtLoginHere;
    Button btnVerifyEmail;
    ImageView imvComebackEmailScreen;

    ReusedConstraint reusedConstraint = new ReusedConstraint(EmailScreen.this);

    private void linkView() {
        edtEmail = findViewById(R.id.edtEmail);
        txtErrorEmail = findViewById(R.id.txtErrorEmail);
        txtLoginHere = findViewById(R.id.txtLoginHere);
        btnVerifyEmail = findViewById(R.id.btnVerifyEmail);
        imvComebackEmailScreen = findViewById(R.id.imvComebackEmailScreen);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_screen);

        linkView();
        addConditions();
        addEvents();
    }

    private void addConditions() {
        String s = AppUtil.eMessageForSignUp;
        if(s.equals(AppUtil.LOCK_CONDITION_SIGNUP))
            addTimerLock();
        else if (s.equals(""))
            return;


    }

    private void addTimerLock() {


        new CountDownTimer(30000, 10) { //Set Timer for 5 seconds
            public void onTick(long millisUntilFinished) {
                btnVerifyEmail.setBackground(getDrawable(R.drawable.button_login_block));
                btnVerifyEmail.setTextColor(getColor(R.color.xamBlcok));
                edtEmail.setBackground(getDrawable(R.drawable.edt_custom_block));
                edtEmail.setHintTextColor(getColor(R.color.xamChu));
                edtEmail.setCompoundDrawableTintList(getColorStateList(R.color.xamBlockIcon));
                btnVerifyEmail.setEnabled(false);
                edtEmail.setEnabled(false);

            }


            @Override
            public void onFinish() {
                reusedConstraint.setCustomColor(edtEmail,R.drawable.custom_edt,R.color.blackUI,R.color.blackUI);
                edtEmail.setHintTextColor(getColor(R.color.xamChu));
                btnVerifyEmail.setEnabled(true);
                btnVerifyEmail.setBackground(getDrawable(R.drawable.custom_button));
                btnVerifyEmail.setTextColor(getColor( R.color.blackUI));
                edtEmail.setEnabled(true);
                AppUtil.eMessageForSignUp="";
            }
        }.start();
    }

    private Boolean validateEmail(){
        String email = edtEmail.getText().toString().trim();
        String emailPattern1 = "[a-zA-Z0-9._-]+@st.uel.edu.vn+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@uel.edu.vn+";

        if (email.isEmpty()){
            txtErrorEmail.setText(R.string.field_cannot_be_empty);
            txtErrorEmail.setTextSize(15);
            edtEmail.setHintTextColor(getColor(R.color.red));
            reusedConstraint.setCustomColor(edtEmail,R.drawable.edt_custom_error,R.color.red,R.color.red);
            return false;
        }

         else if (!email.matches(emailPattern1) && !email.matches(emailPattern2)) {
             txtErrorEmail.setText(R.string.invalid_email_address);
             txtErrorEmail.setTextSize(15);
             reusedConstraint.setCustomColor(edtEmail,R.drawable.edt_custom_error,R.color.red,R.color.red);
             return false;
        }

        else {
            reusedConstraint.setCustomColor(edtEmail,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            edtEmail.setHintTextColor(getColor(R.color.xamChu));
            txtErrorEmail.setText(null);
            txtErrorEmail.setTextSize(0);
            return true;
        }

    }
    private Boolean validateEmailTextChange(){
        String email = edtEmail.getText().toString();

        if (email.isEmpty()){
            txtErrorEmail.setText(R.string.field_cannot_be_empty);
            txtErrorEmail.setTextSize(15);
            edtEmail.setHintTextColor(getColor(R.color.red));
            reusedConstraint.setCustomColor(edtEmail,R.drawable.edt_custom_error,R.color.red,R.color.red);
            return false;
        }
        else
            reusedConstraint.setCustomColor(edtEmail,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            edtEmail.setHintTextColor(getColor(R.color.xamChu));

            txtErrorEmail.setText(null);
            txtErrorEmail.setTextSize(0);
            return true;

    }

    @Override
    public void onBackPressed() {
        Intent openMainActivity = new Intent(EmailScreen.this, LoginScreen.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(openMainActivity, 0);
    }

    private void addEvents() {
        imvComebackEmailScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMainActivity = new Intent(EmailScreen.this, LoginScreen.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openMainActivity, 0);
            }
        });

        txtLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMainActivity = new Intent(EmailScreen.this, LoginScreen.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openMainActivity, 0);
            }
        });
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                validateEmailTextChange();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnVerifyEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!validateEmail())
                {
                    edtEmail.clearFocus();
                }
                else
                {
                    AppUtil.EMAIL_S = edtEmail.getText().toString().trim();
                    Intent intent = new Intent(EmailScreen.this, SignUpScreen_UserInfo.class);
                    startActivity(intent);
                }
            }
        });

    }



}