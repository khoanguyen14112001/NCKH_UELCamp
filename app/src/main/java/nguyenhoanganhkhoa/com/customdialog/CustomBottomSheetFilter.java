package nguyenhoanganhkhoa.com.customdialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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

import nguyenhoanganhkhoa.com.adapter.FacultyEditAdapter;
import nguyenhoanganhkhoa.com.adapter.FilterAdapter;
import nguyenhoanganhkhoa.com.models.Filter;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.another.CustomSpinner;
import nguyenhoanganhkhoa.com.myapplication.another.DateTimeFormat;
import nguyenhoanganhkhoa.com.myapplication.home.EditInfomationScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

public class CustomBottomSheetFilter extends BottomSheetDialog {
    ImageView imvClose, imvDropdown;
    CustomSpinner spnFilter;


    TextView txtFilterFromDate,txtFilterToDate;

    DatePickerDialog.OnDateSetListener setListenerTo;
    DatePickerDialog.OnDateSetListener setListenerFrom;

    Button btnApply;

    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    ConstraintLayout layout_filter_date, layout_filter_status, layout_filter_amount;
    LinearLayout lnTitleStatus, lnTitleAmount;

    EditText edtValueFrom, edtValueTo;
    RangeSlider sliderAmount;

    TextView txtClearFilters;

    CheckBox chkUnsuccessful, chkSuccessful;


    private void linkView() {
        imvClose = findViewById(R.id.imvClose);
        spnFilter = findViewById(R.id.spnFilter);
        imvDropdown = findViewById(R.id.imvDropdown);
        txtFilterFromDate = findViewById(R.id.txtFilterFromDate);
        txtFilterToDate = findViewById(R.id.txtFilterToDate);
        btnApply = findViewById(R.id.btnApply);

        layout_filter_date = findViewById(R.id.layout_filter_date);
        layout_filter_status = findViewById(R.id.layout_filter_status);
        layout_filter_amount = findViewById(R.id.layout_filter_amount);
        lnTitleStatus = findViewById(R.id.lnTitleStatus);
        lnTitleAmount = findViewById(R.id.lnTitleAmount);

        edtValueFrom = findViewById(R.id.edtValueFrom);
        edtValueTo = findViewById(R.id.edtValueTo);
        sliderAmount = findViewById(R.id.sliderAmount);

        txtClearFilters = findViewById(R.id.txtClearFilters);

        chkUnsuccessful=findViewById(R.id.chkUnsuccessful);
        chkSuccessful=findViewById(R.id.chkSuccessful);


    }



    public CustomBottomSheetFilter(@NonNull Context context, int theme, int layout) {
        super(context,theme);
        setContentView(layout);

        linkView();
        initAdapterSpinner();
        addEvents();


//        getCurrentDate();



    }

    String position;



    private void setValueRangeSlider() {

        if(position.equals("All")|position.equals("Amount"))
        {
            String sFrom = edtValueFrom.getText().toString();
            String sTo = edtValueTo.getText().toString();
            if(!sFrom.isEmpty() && !sTo.isEmpty())
            {
                float amountFrom = Float.parseFloat(sFrom);
                float amountTo = Float.parseFloat(sTo);
                ArrayList<Float> list = new ArrayList<>();
                list.add(amountFrom);
                list.add(amountTo);
                sliderAmount.setValues(list);
                Log.d("Gia trị slider là: ", "setValueRangeSlider: " + sliderAmount.getValues());
            }
        }
        else return;

    }


    private void displayFilter(int selectedFilter) {
        Log.d("Gia tri ham: ", "displayFilter: " + selectedFilter);
        if(selectedFilter == 3){
            lnTitleStatus.setVisibility(View.VISIBLE);
            lnTitleAmount.setVisibility(View.VISIBLE);
        }
        else {
            lnTitleStatus.setVisibility(View.GONE);
            lnTitleAmount.setVisibility(View.GONE);
        }
        switch (selectedFilter){
            case 0:
                // Date
                layout_filter_date.setVisibility(View.VISIBLE);
                layout_filter_amount.setVisibility(View.GONE);
                layout_filter_status.setVisibility(View.GONE);
                position = "Date";
                break;
            case 1:
                //Status
                layout_filter_status.setVisibility(View.VISIBLE);
                layout_filter_amount.setVisibility(View.GONE);
                layout_filter_date.setVisibility(View.GONE);
                position = "Status";
                break;
            case 2:
                //Amount
                layout_filter_amount.setVisibility(View.VISIBLE);
                layout_filter_status.setVisibility(View.GONE);
                layout_filter_date.setVisibility(View.GONE);
                position = "Amount";
                break;
            case 3:
                //All
                layout_filter_status.setVisibility(View.VISIBLE);
                layout_filter_amount.setVisibility(View.VISIBLE);
                layout_filter_date.setVisibility(View.VISIBLE);
                position = "All";
                break;
        }
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

//    private void getCurrentDate() {
//
//        String dateFrom = day+"/" +month + "/" +(year -1);
//        String dateTo = day+"/" +month + "/" +year;
//
//        //Đưa ra cái format chuẩn cho 2 cái chuỗi ở trên
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/m/yyyy");
//
//        Date dFrom = null;
//        Date dTo = null;
//        try {
//            // chuyển 2 chuỗi ở trên về date
//            dFrom = sdf.parse(dateFrom);
//            dTo = sdf.parse(dateTo);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        // chỉnh lại định dạng ngày cho chuỗi đó
//        txtFilterFromDate.setText(AppUtil.dateFormat2.format(dFrom));
//        txtFilterToDate.setText(AppUtil.dateFormat2.format(dTo));
//

//    }

    private void initAdapterSpinner() {
        FilterAdapter adapter = new FilterAdapter(getContext(),R.layout.item_filter_selected,getListFilter());
        spnFilter.setAdapter(adapter);
        spnFilter.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            @Override
            public void onPopupWindowOpened(Spinner spinner) {
                imvDropdown.setImageResource(R.drawable.ic_arrrow_dropdown_up_black);

            }

            @Override
            public void onPopupWindowClosed(Spinner spinner) {
                imvDropdown.setImageResource(R.drawable.ic_arror_down_spinner_black);

            }
        });

    }



    private List<Filter> getListFilter() {
        List<Filter> list = new ArrayList<>();
        list.add(new Filter("Date"));
        list.add(new Filter("Status"));
        list.add(new Filter("Amount"));
        list.add(new Filter("All"));
        return list;
    }
    Date dateFrom = null;
    Date dateTo = null;


    private void addEvents() {
        chkUnsuccessful.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkSuccessful.setChecked(false);
                }
            }
        });

        chkSuccessful.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkUnsuccessful.setChecked(false);
                }
            }
        });

        txtClearFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFilterToDate.setText(null);
                txtFilterFromDate.setText(null);
                chkSuccessful.setChecked(false);
                chkUnsuccessful.setChecked(false);
                edtValueFrom.setText("0");
                edtValueTo.setText("500000");
                ArrayList<Float> list = new ArrayList<>();
                list.add((float) 0);
                list.add(500000F);
                sliderAmount.setValues(list);
            }
        });

        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("gia tri set item", "onItemSelected: " + i);
                displayFilter(i);
                setValueRangeSlider();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateDate()|!validateAmount()){

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

        sliderAmount.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull RangeSlider slider) {



            }

            @Override
            public void onStopTrackingTouch(@NonNull RangeSlider slider) {
                List<Float> values = sliderAmount.getValues();

                float amountFrom = values.get(0);
                float amountTo = values.get(1);

                int a1 = (int) amountFrom;
                int a2 = (int) amountTo;

                edtValueFrom.setText(String.valueOf(a1));
                edtValueTo.setText(String.valueOf(a2));

            }
        });


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
    private boolean validateAmount() {
        int amountFrom, amountTo;
        String sFrom = edtValueFrom.getText().toString();
        String sTo = edtValueTo.getText().toString();
        if(!sFrom.isEmpty() && !sTo.isEmpty())
        {
            try{
                amountFrom = Objects.requireNonNull(convertStringToInt())[0];
                amountTo =  Objects.requireNonNull(convertStringToInt())[1];
                if(amountFrom<=amountTo){
                    setValueRangeSlider();
                    return true;
                }
                else {
                    Toast.makeText(getContext(), "Invalid input amount!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            catch (Exception e) {
                Log.d("TAG", "validateAmount: " + e);
                return false;
            }

        }
        else if(sFrom.isEmpty() && !sTo.isEmpty()){
            edtValueFrom.setText("0");
            setValueRangeSlider();
            return true;

        }

        else if(!sFrom.isEmpty() && sTo.isEmpty()){
            edtValueTo.setText("500000");
            setValueRangeSlider();
            return true;

        }
        else{
            edtValueFrom.setText("0");
            edtValueTo.setText("500000");
            setValueRangeSlider();
            return true;

        }

    }

    private int[] convertStringToInt() {
        try {
            int[] amountFromTo = new int[2];
            int amountFrom = Integer.parseInt(edtValueFrom.getText().toString());
            int amountTo = Integer.parseInt(edtValueTo.getText().toString());
            amountFromTo[0] = amountFrom;
            amountFromTo[1] = amountTo;
            return amountFromTo;

        }
        catch (Exception e){
            Log.d(":Error", "Fail to convertStringToInt in bottom sheet: " + e);
            return null;
        }
    }




}
