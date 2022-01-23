package nguyenhoanganhkhoa.com.customdialog;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nguyenhoanganhkhoa.com.myapplication.R;

public class CustomBottomSheetComponent extends BottomSheetDialog {

    ImageView imvClose;
    Button btnApply;



    public CustomBottomSheetComponent(@NonNull Context context, int theme, int layout) {

        super(context,theme);
        setContentView(layout);
        linkView();
        addEvents();

    }

    private void linkView() {
        imvClose = findViewById(R.id.imvClose);
        btnApply = findViewById(R.id.btnApply);

    }

    private void addEvents() {
        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


}
