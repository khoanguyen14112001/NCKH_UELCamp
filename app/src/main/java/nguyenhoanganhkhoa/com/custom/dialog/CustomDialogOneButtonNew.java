package nguyenhoanganhkhoa.com.custom.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import nguyenhoanganhkhoa.com.myapplication.R;

public class CustomDialogOneButtonNew extends Dialog {
    public Button btnOK;
    public TextView txtTitleDialog, txtContentDialog;
    Activity activity;
    public CustomDialogOneButtonNew(@NonNull Context context) {

        super(context);
        this.activity = (Activity) context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_one_button);

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




    }



    public void linkView() {
        btnOK = findViewById(R.id.btnOK);
        txtTitleDialog = findViewById(R.id.txtTitleDialog);
        txtContentDialog = findViewById(R.id.txtContentDialog);
    }
}
