package nguyenhoanganhkhoa.com.myapplication.home.canteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DrinkCateAdapter;
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.models.DrinkCateOption;
import nguyenhoanganhkhoa.com.models.DrinkInCart;
import nguyenhoanganhkhoa.com.models.DrinkOption;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.BuyNowSLSpaceScreen;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.OrderSLSpaceScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class BuyNowCanteenScreen extends AppCompatActivity {
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    RecyclerView rcvOptionDrink;
    DrinkCateAdapter adapter;
    ImageView imvBack, imbPlus, imbMinus;
    TextView edtQuantity;

    ImageView imvThumbDrink;
    TextView txtNameDrink, txtPriceDrink;

    Button btnGoToOrder;

    TextView txtTotalPayment;
    EditText edtNotes;

    int quantity = 1;

    private void linkView() {
        rcvOptionDrink = findViewById(R.id.rcvOptionDrink);
        imvBack = findViewById(R.id.imvBack);
        imbPlus = findViewById(R.id.imbPlus);
        imbMinus = findViewById(R.id.imbMinus);
        edtQuantity = findViewById(R.id.edtQuantity);

        imvThumbDrink = findViewById(R.id.imvThumbDrink);
        txtNameDrink = findViewById(R.id.txtNameDrink);
        txtPriceDrink = findViewById(R.id.txtPriceDrink);

        txtTotalPayment = findViewById(R.id.txtTotalPayment);

        btnGoToOrder = findViewById(R.id.btnGoToOrder);
        edtNotes = findViewById(R.id.edtNotes);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now_canteen_screen);

        linkView();
        setData();
        addEvents();
    }








    Drink drink;
    DrinkInCart drinkInCart = new DrinkInCart();

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
        reusedConstraint.openNav(this);
        btnGoToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<DrinkInCart> listDrink = new ArrayList<>();
                Intent intent = new Intent(BuyNowCanteenScreen.this, OrderCanteenScreen.class);
                setValueToPush();
                Log.d("TAG", "onClick: " + drinkInCart.toString());
                listDrink.add(drinkInCart);
                intent.putExtra(AppUtil.MY_BUNDLE_TRANS, (Serializable) listDrink);
                startActivity(intent);
            }
        });
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.checkNavStatusComeBack(BuyNowCanteenScreen.this);
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

    private void setValueToPush(){
        drinkInCart.setThumbDrink(drink.getThumbDrink());
        drinkInCart.setDrinkName(drink.getDrinkName());
        drinkInCart.setQuantityDrink(Integer.parseInt(edtQuantity.getText().toString()));
        drinkInCart.setNote(edtNotes.getText().toString());
        if(drinkInCart.getDrinkPrePrice()==0){
            drinkInCart.setDrinkPrePrice(drink.getDrinkPrePrice());
        }
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(BuyNowCanteenScreen.this);
    }
}