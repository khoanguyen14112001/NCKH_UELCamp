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

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class SetNewPasswordScreen extends AppCompatActivity {

    EditText edtCurrentPassword;
    Button btnNext;
    ImageView imgToggleClose1, imvResetBack;
    TextView txtErrorCurrentPassword;

    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password_screen);

        linkView();
        addEvent();


    }

    private void addEvent() {
        imvResetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPassword();
            }
        });
        edtCurrentPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateFEPassword();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imgToggleClose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.showHidePassword(edtCurrentPassword,view);
            }
        });

    }

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("account")
            .child(AppUtil.DATA_OBJECT).child(AppUtil.USERNAME_AFTER_LOGGIN);

    private void checkPassword(){
        if(validateFEPassword()){
            String password = edtCurrentPassword.getText().toString().trim();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                String pass = snapshot.child(AppUtil.FB_PASSWORD).getValue(String.class);
                                if(password.equals(pass)){
                                    setCorrectPass();
                                    startActivity(new Intent(SetNewPasswordScreen.this,EnterNewPasswordScreen.class));
                                }
                                else{
                                    txtErrorCurrentPassword.setText(R.string.wrong_password_please_try_again);
                                    setErrorWrongPass();
                                }
                            }
                            catch (Exception e){
                                Log.d("Error", "Cannot get password from FB in SetNewPasswordScreen: " + e);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
    }
    private void setErrorWrongPass(){
        txtErrorCurrentPassword.setTextSize(15);
        reusedConstraint.setCustomColor(edtCurrentPassword,R.drawable.edt_custom_error,R.color.red,R.color.red);
        imgToggleClose1.setImageTintList(getColorStateList(R.color.red));
        edtCurrentPassword.setHintTextColor(getColor(R.color.red));
    }

    private boolean validateFEPassword(){
        String password = edtCurrentPassword.getText().toString().trim();
        if (password.isEmpty()){
            txtErrorCurrentPassword.setText(R.string.field_cannot_be_empty);
            setErrorWrongPass();
            return false;
        }

        else {
            setCorrectPass();
            return true;
        }
    }

    private void setCorrectPass() {
        reusedConstraint.setCustomColor(edtCurrentPassword,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
        edtCurrentPassword.setHintTextColor(getColor(R.color.xamChu));
        imgToggleClose1.setImageTintList(getColorStateList(R.color.black80));
        txtErrorCurrentPassword.setText(null);
        txtErrorCurrentPassword.setTextSize(0);
    }

    private void linkView() {
        edtCurrentPassword = findViewById(R.id.edtCurrentPassword);
        btnNext = findViewById(R.id.btnNext);
        imgToggleClose1 = findViewById(R.id.imgToggleClose1);
        txtErrorCurrentPassword = findViewById(R.id.txtErrorCurrentPassword);
        imvResetBack = findViewById(R.id.imvResetBack);
    }
}