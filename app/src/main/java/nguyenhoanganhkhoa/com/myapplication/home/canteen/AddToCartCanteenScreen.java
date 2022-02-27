package nguyenhoanganhkhoa.com.myapplication.home.canteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DrinkCateAdapter;
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.models.DrinkCateOption;
import nguyenhoanganhkhoa.com.models.DrinkOption;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class AddToCartCanteenScreen extends AppCompatActivity {

    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    ImageView imvBack, imbPlus, imbMinus;
    TextView edtQuantity;

    ImageView imvThumbDrink;
    TextView txtNameDrink, txtPriceDrink;

    Button btnAddToCart;

    TextView txtTotalPayment;

    int quantity = 1;

    private void linkView() {
        imvBack = findViewById(R.id.imvBack);
        imbPlus = findViewById(R.id.imbPlus);
        imbMinus = findViewById(R.id.imbMinus);
        edtQuantity = findViewById(R.id.edtQuantity);

        imvThumbDrink = findViewById(R.id.imvThumbDrink);
        txtNameDrink = findViewById(R.id.txtNameDrink);
        txtPriceDrink = findViewById(R.id.txtPriceDrink);

        txtTotalPayment = findViewById(R.id.txtTotalPayment);

        btnAddToCart = findViewById(R.id.btnAddToCart);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart_canteen_screen);

        linkView();
        setData();
        reusedConstraint.openNav(this);
        addEvents();
    }

    Drink drink;

    private void getData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(AppUtil.MY_BUNDLE_TRANS);
        if(bundle!=null){
            drink = (Drink) bundle.getSerializable(AppUtil.SELECTED_ITEM_TRANS);
        }
    }
    private void setData() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra(AppUtil.MY_BUNDLE_TRANS);
            if(bundle!=null){
                drink = (Drink) bundle.getSerializable(AppUtil.SELECTED_ITEM_TRANS);
                txtNameDrink.setText(drink.getDrinkName());
                txtPriceDrink.setText(reusedConstraint.formatCurrency(drink.getPriceAfterDiscount()));
                txtTotalPayment.setText(reusedConstraint.formatCurrency(drink.getPriceAfterDiscount()));
                txtTotalPayment.setHint(String.valueOf(drink.getPriceAfterDiscount()));
                imvThumbDrink.setImageResource(drink.getThumbDrink());
            }
        }

        catch (Exception e){
            Log.d("Error", "Cannot get data from add to cart adapter " + e);
        }
    }
    private void addEvents() {
        getData();

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddToCartCanteenScreen.this, CartCanteenScreen.class));
            }
        });
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.checkNavStatusComeBack(AddToCartCanteenScreen.this);
            }
        });

        imbPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                edtQuantity.setText(String.valueOf(quantity));
                double totalPay = Double.parseDouble(txtTotalPayment.getHint().toString());
                totalPay = totalPay + drink.getPriceAfterDiscount();
                txtTotalPayment.setText(reusedConstraint.formatCurrency(totalPay));
                txtTotalPayment.setHint(String.valueOf(totalPay));
            }
        });

        imbMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity>1){
                    quantity--;
                    edtQuantity.setText(String.valueOf(quantity));
                    double totalPay = Double.parseDouble(txtTotalPayment.getHint().toString());
                    totalPay = totalPay - drink.getPriceAfterDiscount();
                    txtTotalPayment.setText(reusedConstraint.formatCurrency(totalPay));
                    txtTotalPayment.setHint(String.valueOf(totalPay));

                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(AddToCartCanteenScreen.this);
    }
}