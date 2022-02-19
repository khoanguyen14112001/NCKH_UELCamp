package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DrinkIncartAdapter;
import nguyenhoanganhkhoa.com.adapter.PurchaseAdapter;
import nguyenhoanganhkhoa.com.adapter.PurchaseStatusAdapter;
import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogOneButtonNew;
import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogTwoButtonNew;
import nguyenhoanganhkhoa.com.models.DrinkInCart;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class OrderDetailScreen extends AppCompatActivity {
    RecyclerView rcvOrderDetail;
    DrinkIncartAdapter adapter;

    TextView txtPrice, txtDeliveryFee, txtDiscount, txtTotalPayment, txtPaymentMethod, txtStatus;
    Button btnCancelOrder;

    ReusedConstraint reusedConstraint = new ReusedConstraint();

    private static final  int deliveryFee = 7000;
    private static final  int discount = 7000;

    private void linkView() {
        rcvOrderDetail = findViewById(R.id.rcvOrderDetail);

        txtPrice = findViewById(R.id.txtPrice);
        txtDeliveryFee = findViewById(R.id.txtDeliveryFee);
        txtDiscount = findViewById(R.id.txtDiscount);
        txtTotalPayment = findViewById(R.id.txtTotalPayment);
        txtPaymentMethod = findViewById(R.id.txtPaymentMethod);
        txtStatus = findViewById(R.id.txtStatus);

        btnCancelOrder = findViewById(R.id.btnCancelOrder);
    }

    private final int GREY = R.color.xamBlcok;
    private final int BLACK = R.color.black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_screen);

        linkView();
        setValue();
        initAdapter();
        addEvents();
    }

    private List<DrinkInCart> getListDrinkPurchaseDetail(){
        return (List<DrinkInCart>) getIntent().getSerializableExtra(AppUtil.MY_BUNDLE_TRANS);
    }

    private void initAdapter() {
        adapter = new DrinkIncartAdapter(this);
        adapter.setNumScreen(DrinkIncartAdapter.ORDER_DETAIL_SCREEN);
        adapter.setData(getListDrinkPurchaseDetail());
        rcvOrderDetail.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvOrderDetail.setAdapter(adapter);
    }

    private double getPrice(){
        DrinkInCart drink;
        double totalPrice = 0;
        for (int i = 0; i < getListDrinkPurchaseDetail().size(); i++){
            drink = getListDrinkPurchaseDetail().get(i);
            totalPrice = totalPrice + drink.getTotalPrice();
        }
        return totalPrice;
    }

    private void setValue(){
        txtPrice.setText(reusedConstraint.formatCurrency(getPrice()));
        txtDiscount.setText("-" + reusedConstraint.formatCurrency(discount));
        txtDeliveryFee.setText(reusedConstraint.formatCurrency(deliveryFee));
        txtTotalPayment.setText(reusedConstraint.formatCurrency(getPrice() + deliveryFee - discount));
        if(AppUtil.statusOrder!=null){
            txtStatus.setText(AppUtil.statusOrder);
        }

        if(txtStatus.getText().toString().equals(PurchaseAdapter.TYPE_PENDING)){
            changeButtonStatus(BLACK,true);
        }
        else{
            changeButtonStatus(GREY,false);
        }
    }

    private void changeButtonStatus(int color, boolean isEnable){
        btnCancelOrder.setBackgroundTintList(getColorStateList(color));
        btnCancelOrder.setEnabled(isEnable);
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        reusedConstraint.setActionComeBack(this);
        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogTwoButtonNew dialog = new CustomDialogTwoButtonNew(OrderDetailScreen.this);
                dialog.txtHeaderDialog.setText("Cancel order");
                dialog.txtContentDialog.setText("You are cancelling your order.\nAre you sure?");
                dialog.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.btnOK.setText(R.string.yes);
                dialog.btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(OrderDetailScreen.this, "Your order has been cancelled!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        finish();
                    }
                });
                dialog.show();
            }
        });
    }
}