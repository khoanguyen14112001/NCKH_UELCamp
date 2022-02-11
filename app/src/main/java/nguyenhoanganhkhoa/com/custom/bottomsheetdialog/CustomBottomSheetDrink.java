package nguyenhoanganhkhoa.com.custom.bottomsheetdialog;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import nguyenhoanganhkhoa.com.myapplication.R;

public class CustomBottomSheetDrink extends BottomSheetDialog {
    public ImageView imvThumbDrink;
    public TextView txtTitleDrink, txtNameDrink, txtTypeDrink, txtPrepriceDrink, txtAftpriceDrink;
    public Button btnAddToCart;
    public ConstraintLayout layout_preprice;

    private void linkView() {
        imvThumbDrink = findViewById(R.id.imvThumbDrink);
        txtTitleDrink = findViewById(R.id.txtTitleDrink);
        txtNameDrink = findViewById(R.id.txtNameDrink);
        txtTypeDrink = findViewById(R.id.txtTypeDrink);
        txtPrepriceDrink = findViewById(R.id.txtPrepriceDrink);
        txtAftpriceDrink = findViewById(R.id.txtAftpriceDrink);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        layout_preprice = findViewById(R.id.layout_preprice);
    }

    public CustomBottomSheetDrink(@NonNull Context context, int theme) {
        super(context, theme);
        setContentView(R.layout.custom_bottomdialog_drink);

        linkView();
    }


}
