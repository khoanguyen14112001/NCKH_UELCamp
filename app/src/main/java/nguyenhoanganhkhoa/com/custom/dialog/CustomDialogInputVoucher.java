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

public class CustomDialogInputVoucher extends Dialog {
    public Button btnApply;
    public TextView txtHeaderDialog, txtTextShow;
    public EditText edtVoucherInput;
    public ImageView imvCloseDialog;

    Activity activity;
    ReusedConstraint reusedConstraint = new ReusedConstraint(getContext());


    public CustomDialogInputVoucher(@NonNull Context context) {
        super(context);
        this.activity = (Activity) context;


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_voucher_apply);

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
        reusedConstraint.changeColor(txtTextShow,6,18,R.color.primary_yellow);
        addEvents();


    }



    private void addEvents() {

        imvCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void linkView() {
        btnApply = findViewById(R.id.btnApply);
        txtTextShow = findViewById(R.id.txtTextShow);
        edtVoucherInput = findViewById(R.id.edtVoucherInput);
        imvCloseDialog = findViewById(R.id.imvCloseDialog);
        txtHeaderDialog = findViewById(R.id.txtHeaderDialog);
    }


}
