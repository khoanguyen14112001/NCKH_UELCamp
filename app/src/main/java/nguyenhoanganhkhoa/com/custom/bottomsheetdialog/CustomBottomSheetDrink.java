package nguyenhoanganhkhoa.com.custom.bottomsheetdialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import nguyenhoanganhkhoa.com.myapplication.R;

public class CustomBottomSheetDrink extends BottomSheetDialog {
    public ImageView imvThumbDrink;
    public TextView txtTitleDrink, txtNameDrink, txtTypeDrink, txtPrepriceDrink, txtAftpriceDrink;
    public Button btnAddToCart;
    public ConstraintLayout layout_preprice;
    public LinearLayout layout_drink_detail;




    private void linkView() {
        imvThumbDrink = findViewById(R.id.imvThumbDrink);
        txtTitleDrink = findViewById(R.id.txtTitleDrink);
        txtNameDrink = findViewById(R.id.txtNameDrink);
        txtTypeDrink = findViewById(R.id.txtTypeDrink);
        txtPrepriceDrink = findViewById(R.id.txtPrepriceDrink);
        txtAftpriceDrink = findViewById(R.id.txtAftpriceDrink);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        layout_preprice = findViewById(R.id.layout_preprice);
        layout_drink_detail = findViewById(R.id.layout_drink_detail);
    }

    public CustomBottomSheetDrink(@NonNull Context context, int theme) {


        super(context, theme);


//        setContentView(R.layout.custom_bottomdialog_drink);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.custom_bottomdialog_drink, null);
        setContentView(dialogView);
        linkView();

//        FrameLayout bottomSheet = findViewById(R.id.design_bottom_sheet);
//        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
//        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();
//
//        int windowHeight = getWindowHeight((Activity)context);
//
//        if (layoutParams != null) {
//            layoutParams.height = windowHeight * 4/5;
//        }

    }

//    private int getWindowHeight(Activity activity) {
//        // Calculate window height for fullscreen use
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        return displayMetrics.heightPixels;
//    }

}
