package nguyenhoanganhkhoa.com.myapplication.home.SLSpace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DrinkIncartAdapter;
import nguyenhoanganhkhoa.com.adapter.FacultyAdapter;
import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogTransferConfirm;
import nguyenhoanganhkhoa.com.custom.spinner.CustomSpinner;
import nguyenhoanganhkhoa.com.models.DrinkInCart;
import nguyenhoanganhkhoa.com.models.Faculty;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.homepage.HomePageScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class OrderSLSpaceScreen extends AppCompatActivity {

    private static final double discount = 5000;
    private static final double deliveryFee = 3000;

    RecyclerView rcvItemOrder;
    List<DrinkInCart> orderList;
    DrinkIncartAdapter adapter = new DrinkIncartAdapter(this);
    DrawerLayout drawerLayout;
    TextView txtTotalPayment, txtTotalNoDiscount, txtDeliveryFee, txtDiscount, txtPaymentSummary;

    CustomSpinner spnPaymentMethod;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    ConstraintLayout layout_voucher, layout_container_show_order, layout_complete_order;
    Button btnOrder, btnBackToHome, btnChangeAddress, btnAddMore, btnGoToPurchase;

    ImageView imvDropdown;

    FacultyAdapter adapterPaymentMethod;

    private void linkView() {
        rcvItemOrder = findViewById(R.id.rcvItemOrder);
        drawerLayout = findViewById(R.id.drawerLayout);
        txtTotalPayment = findViewById(R.id.txtTotalPayment);
        txtTotalNoDiscount = findViewById(R.id.txtTotalNoDiscount);

        txtDeliveryFee = findViewById(R.id.txtDeliveryFee);
        txtDiscount = findViewById(R.id.txtDiscount);
        txtPaymentSummary = findViewById(R.id.txtPaymentSummary);

        spnPaymentMethod = findViewById(R.id.spnPaymentMethod);

        layout_voucher = findViewById(R.id.layout_voucher);
        btnOrder = findViewById(R.id.btnOrder);
        btnBackToHome = findViewById(R.id.btnBackToHome);
        layout_container_show_order = findViewById(R.id.layout_container_show_order);
        layout_complete_order = findViewById(R.id.layout_complete_order);
        btnChangeAddress = findViewById(R.id.btnChangeAddress);
        btnAddMore = findViewById(R.id.btnAddMore);
        btnGoToPurchase = findViewById(R.id.btnGoToPurchase);

        imvDropdown = findViewById(R.id.imvDropdown);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_slspace_screen);

        linkView();
        getData();
        initAdapter();
        setCallBackAdapter();
        setFee();
        getTotalPayment(orderList);
        initAdapterPaymentMethod();
        addEvents();

    }

    private void initAdapterPaymentMethod() {
        adapterPaymentMethod = new FacultyAdapter(this,R.layout.item_payment_selected,getListPaymentMethod());
        adapterPaymentMethod.setScreen(FacultyAdapter.ORDER_SCREEN);
        adapterPaymentMethod.setStatusAdapter(FacultyAdapter.NORMAL_STATUS);

        spnPaymentMethod.setAdapter(adapterPaymentMethod);
    }


    private List<Faculty> getListPaymentMethod() {
        List<Faculty> list = new ArrayList<>();
        list.add(new Faculty("UEL CAMP"));
        list.add(new Faculty("CASH"));
        return list;
    }

    private void setFee() {
        txtDeliveryFee.setText( "+" + reusedConstraint.formatCurrency(deliveryFee));
        txtDiscount.setText("-" + reusedConstraint.formatCurrency(discount));
    }

    private void setCallBackAdapter(){
        adapter.setCallBack(new DrinkIncartAdapter.MyCallBack() {
            @Override
            public void setTextPriceTotal(List<DrinkInCart> list) {
                getTotalPayment(list);
            }

            @Override
            public void showSnackBar(String itemName) {
                Snackbar snackbar = Snackbar.make(drawerLayout,itemName+ " has been removed!", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }

            @Override
            public void getListSizeRemain(int size) {
                if(size == 0){
                    Toast.makeText(OrderSLSpaceScreen.this, "There is no products in your order", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void getTotalPayment(List<DrinkInCart> list){
        int i;
        double totalPay = 0;
        try {
            for(i=0;i<list.size();i++){
                totalPay = totalPay + list.get(i).getTotalPrice();
            }
            txtTotalNoDiscount.setText(reusedConstraint.formatCurrency(totalPay));
            txtTotalPayment.setText(reusedConstraint.formatCurrency(totalPay - discount + deliveryFee));
            txtPaymentSummary.setText(reusedConstraint.formatCurrency(totalPay - discount + deliveryFee));
        }
        catch (Exception e){
            Log.d("TAG", "getTotalPayment: " + e);
        }

    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        reusedConstraint.setActionComeBack(this);

        spnPaymentMethod.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            @Override
            public void onPopupWindowOpened(Spinner spinner) {
                imvDropdown.setImageResource(R.drawable.ic_arrrow_dropdown_up_black);
            }

            @Override
            public void onPopupWindowClosed(Spinner spinner) {
                imvDropdown.setImageResource(R.drawable.ic_arror_down_spinner_black);
            }
        });
        spnPaymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    imvDropdown.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down_spinner));
                adapterPaymentMethod.setCurrentPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGoToPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderSLSpaceScreen.this, PurchaseSLSpaceScreen.class));

            }
        });

        btnAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderSLSpaceScreen.this, MenuSLSpaceScreen.class));

            }
        });
        btnChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderSLSpaceScreen.this,ChooseAddressScreen.class));

            }
        });
        layout_voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderSLSpaceScreen.this,VoucherSLSpaceScreen.class));
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConfirmDialog();
            }
        });

        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderSLSpaceScreen.this, HomePageScreen.class));
            }
        });

    }

    private void openConfirmDialog() {
        CustomDialogTransferConfirm dialog = new CustomDialogTransferConfirm(this,R.layout.custom_dialog_transfer_confirm);
        dialog.setScreen(1);
        dialog.setCallBack(new CustomDialogTransferConfirm.MyCallBack() {
            @Override
            public void hideLayoutOrder() {
                layout_container_show_order.setVisibility(View.GONE);
                layout_complete_order.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void getData(){
        orderList = (List<DrinkInCart>) getIntent().getSerializableExtra(AppUtil.MY_BUNDLE_TRANS);
    }


    private void initAdapter() {
        adapter.setData(orderList);
        adapter.setNumScreen(DrinkIncartAdapter.ORDER_SCREEN);
        rcvItemOrder.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvItemOrder.setAdapter(adapter);
    }



}