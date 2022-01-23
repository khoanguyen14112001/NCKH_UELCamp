package nguyenhoanganhkhoa.com.customdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;

import nguyenhoanganhkhoa.com.myapplication.R;

public class CustomDialogThreeButton extends Dialog {
    public Button btnTakePhotos, btnChooseFromGallery, btnCancel;
    Activity activity;
    public CustomDialogThreeButton(@NonNull Context context, int dialoglayout) {

        super(context);
        this.activity = (Activity) context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(dialoglayout);

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

    private void linkView() {
        btnTakePhotos = findViewById(R.id.btnTakePhotos);
        btnCancel = findViewById(R.id.btnCancel);
        btnChooseFromGallery = findViewById(R.id.btnChooseFromGallery);
    }
}
