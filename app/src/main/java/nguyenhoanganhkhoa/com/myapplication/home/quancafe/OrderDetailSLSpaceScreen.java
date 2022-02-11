package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DrinkIncartAdapter;
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.models.DrinkInCart;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class OrderDetailSLSpaceScreen extends AppCompatActivity {

    private static final double discount = 5000;
    private static final double deliveryFee = 3000;

    RecyclerView rcvItemOrder;
    List<DrinkInCart> orderList;
    DrinkIncartAdapter adapter = new DrinkIncartAdapter(this);
    DrawerLayout drawerLayout;
    TextView txtTotalPayment, txtTotalNoDiscount, txtDeliveryFee, txtDiscount, txtPaymentSummary, txtPaymentMethod;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    private void linkView() {
        rcvItemOrder = findViewById(R.id.rcvItemOrder);
        drawerLayout = findViewById(R.id.drawerLayout);
        txtTotalPayment = findViewById(R.id.txtTotalPayment);
        txtTotalNoDiscount = findViewById(R.id.txtTotalNoDiscount);

        txtDeliveryFee = findViewById(R.id.txtDeliveryFee);
        txtDiscount = findViewById(R.id.txtDiscount);
        txtPaymentSummary = findViewById(R.id.txtPaymentSummary);
        txtPaymentMethod = findViewById(R.id.txtPaymentMethod);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_slspace_screen);

        linkView();
        getData();
        initAdapter();
        setCallBackAdapter();
        setFee();
        getTotalPayment(orderList);
        addEvents();

    }

    private void setFee() {
        txtDeliveryFee.setText( "+" + reusedConstraint.formatCurrency(deliveryFee));
        txtDiscount.setText(reusedConstraint.formatCurrency(discount));
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
                    Toast.makeText(OrderDetailSLSpaceScreen.this, "There is no products in your order", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void getTotalPayment(List<DrinkInCart> list){
        int i;
        double totalPay = 0;
        for(i=0;i<list.size();i++){
            totalPay = totalPay + list.get(i).getTotalPrice();
        }
        txtTotalNoDiscount.setText(reusedConstraint.formatCurrency(totalPay));
        txtTotalPayment.setText(reusedConstraint.formatCurrency(totalPay - discount + deliveryFee));
        txtPaymentSummary.setText(reusedConstraint.formatCurrency(totalPay - discount + deliveryFee));
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        reusedConstraint.setActionComeBack(this);
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
        adapter.setNumScreen(1);
        rcvItemOrder.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvItemOrder.setAdapter(adapter);
    }


}