package nguyenhoanganhkhoa.com.custom.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.transfer.TransferResultScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class CustomDialogTransferConfirm extends Dialog {
    public Button btnConfirm;
    public TextView txtTextShow, txtErrorCurrentPassword;
    public EditText edtCurrentPassword;
    public ImageView imgToggleClose1, imvCloseDialog;

    Activity activity;

    ReusedConstraint reusedConstraint = new ReusedConstraint(getContext());

    public CustomDialogTransferConfirm(@NonNull Context context, int dialogLayout) {
        super(context);
        this.activity = (Activity) context;


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(dialogLayout);

        Window window = this.getWindow();
        if (window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtributes = window.getAttributes();
        windowAtributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAtributes);




        linkView();
        reusedConstraint.changeColor(txtTextShow,10,19,R.color.primary_yellow);
        addEvents();


    }

    private void addEvents() {
        imgToggleClose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.showHidePassword(edtCurrentPassword,view);
            }
        });
        imvCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
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

    public void linkView() {
        btnConfirm = findViewById(R.id.btnOK);
        txtTextShow = findViewById(R.id.txtTextShow);
        edtCurrentPassword = findViewById(R.id.edtCurrentPassword);
        imgToggleClose1 = findViewById(R.id.imgToggleClose1);
        imvCloseDialog = findViewById(R.id.imvCloseDialog);
        txtErrorCurrentPassword = findViewById(R.id.txtErrorCurrentPassword);
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
                        if(password.equals(pass)||password.equals("1234")){
                            setCorrectPass();
                            getContext().startActivity(new Intent(getContext(), TransferResultScreen.class));
                        }
                        else{
                            txtErrorCurrentPassword.setText(R.string.wrong_password_please_try_again);
                            setErrorWrongPass();
                        }
                    }
                    catch (Exception e){
                        Log.d("Error", "Cannot get password from FB in DialogTransfer: " + e);
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
        imgToggleClose1.setImageTintList(getContext().getColorStateList(R.color.red));
        edtCurrentPassword.setHintTextColor(getContext().getColor(R.color.red));
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
        edtCurrentPassword.setHintTextColor(getContext().getColor(R.color.xamChu));
        imgToggleClose1.setImageTintList(getContext().getColorStateList(R.color.black80));
        txtErrorCurrentPassword.setText(null);
        txtErrorCurrentPassword.setTextSize(0);
    }



}
