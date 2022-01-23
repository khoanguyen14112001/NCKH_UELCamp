package nguyenhoanganhkhoa.com.customdialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.slider.RangeSlider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import nguyenhoanganhkhoa.com.adapter.FilterAdapter;
import nguyenhoanganhkhoa.com.models.Filter;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.another.CustomSpinner;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

public class CustomBottomSheetFilterHistory extends BottomSheetDialog {
    ImageView imvClose, imvDropdown;
    CustomSpinner spnFilter;


    TextView txtFilterFromDate,txtFilterToDate;

    DatePickerDialog.OnDateSetListener setListenerTo;
    DatePickerDialog.OnDateSetListener setListenerFrom;

    ConstraintLayout  layout_filter_status, layout_filter_amount;

    LinearLayout lnTitleStatus, lnTitleAmount;

    Button btnApply;

    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);



    TextView txtClearFilters;



    private void linkView() {
        imvClose = findViewById(R.id.imvClose);
        spnFilter = findViewById(R.id.spnFilter);
        imvDropdown = findViewById(R.id.imvDropdown);
        txtFilterFromDate = findViewById(R.id.txtFilterFromDate);
        txtFilterToDate = findViewById(R.id.txtFilterToDate);
        btnApply = findViewById(R.id.btnApply);

        layout_filter_status = findViewById(R.id.layout_filter_status);
        layout_filter_amount = findViewById(R.id.layout_filter_amount);
        lnTitleStatus = findViewById(R.id.lnTitleStatus);
        lnTitleAmount = findViewById(R.id.lnTitleAmount);


        txtClearFilters = findViewById(R.id.txtClearFilters);


    }

    public CustomBottomSheetFilterHistory(@NonNull Context context, int theme, int layout) {
        super(context,theme);
        setContentView(layout);

        linkView();
        setVisibleLayout();
        initAdapterSpinner();
        addEvents();


    }

    private void setVisibleLayout() {
        layout_filter_status.setVisibility(View.GONE);
        layout_filter_amount.setVisibility(View.GONE);

        lnTitleStatus.setVisibility(View.GONE);
        lnTitleAmount.setVisibility(View.GONE);

    }


    private void initAdapterSpinner() {
        FilterAdapter adapter = new FilterAdapter(getContext(),R.layout.item_filter_selected,getListFilter());
        spnFilter.setAdapter(adapter);
    }


    private Date convertStringToDate(String sDate, SimpleDateFormat sdf) {

        Date date = null;
        try {
            date = sdf.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }






    private List<Filter> getListFilter() {
        List<Filter> list = new ArrayList<>();
        list.add(new Filter("Date"));
        return list;
    }
    Date dateFrom = null;
    Date dateTo = null;


    private void addEvents() {

        txtClearFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFilterToDate.setText(null);
                txtFilterFromDate.setText(null);
            }
        });

        spnFilter.setEnabled(false);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateDate()){

                }
                else {
                    if(dateFrom!=null&&dateTo!=null){
                        //getData and search
                    }
                    dismiss();
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                }

            }
        });


        setListenerFrom = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                txtFilterFromDate.setText(AppUtil.dateFormat2.format(calendar.getTime()));

                // Chuyển lại định dạng date cho chuỗi
                dateFrom = convertStringToDate(txtFilterFromDate.getText().toString(),AppUtil.dateFormat2);
            }
        };



        setListenerTo = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                txtFilterToDate.setText(AppUtil.dateFormat2.format(calendar.getTime()));

                dateTo = convertStringToDate(txtFilterToDate.getText().toString(),AppUtil.dateFormat2);

            }
        };

        txtFilterFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListenerFrom,
                        year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        txtFilterToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListenerTo,
                        year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });



        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    private boolean validateDate() {


        if(dateFrom !=null && dateTo!=null)
        {
            if(dateFrom.compareTo(dateTo) >0){
                Toast.makeText(getContext(), "Invalid date input. Please try again", Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                return true;
            }
        }
        else{
            return true;
        }
    }



}
