package nguyenhoanganhkhoa.com.myapplication.home.SLSpace;

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

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DrinkCateAdapter;
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.models.DrinkCateOption;
import nguyenhoanganhkhoa.com.models.DrinkInCart;
import nguyenhoanganhkhoa.com.models.DrinkOption;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class BuyNowSLSpaceScreen extends AppCompatActivity {

    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    RecyclerView rcvOptionDrink;
    DrinkCateAdapter adapter;
    ImageView imvBack, imbPlus, imbMinus;
    TextView edtQuantity;

    ImageView imvThumbDrink;
    TextView txtNameDrink, txtPriceDrink;

    Button btnGoToOrder;

    TextView txtTotalPayment;

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now_slpace_screen);

        linkView();
        initAdapter();
        setData();
        passData();
        reusedConstraint.openNav(this);
        addEvents();
    }




    private void initAdapter() {
        adapter = new DrinkCateAdapter(this);
        adapter.setData(getListCateOption());
        rcvOptionDrink.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvOptionDrink.setAdapter(adapter);
    }

    private void passData(){
        adapter.setCallBack(new DrinkCateAdapter.MyCallBack() {
            @Override
            public void passDataToCart(List<DrinkCateOption> mCates) {
                double totalPay;
                for(int i =0; i<mCates.size();i++){
                    List<DrinkOption> drinkOption1 = mCates.get(i).getListOption();
                    for(int k = 0; k<drinkOption1.size();k++){
                        if(drinkOption1.get(k).isChosen()){
                            totalPay = drink.getPriceAfterDiscount() * quantity + drinkOption1.get(k).getOptionAdditionPrice();
                            txtTotalPayment.setText(reusedConstraint.formatCurrency(totalPay));
                            txtTotalPayment.setHint(String.valueOf(totalPay));

                            drinkInCart.setDrinkPrePrice(totalPay);
                            if(drinkOption1.get(k).getOptionCate().toLowerCase().contains("ice")){
                                drinkInCart.setIceLevel(drinkOption1.get(k).getValueLevel());
                            }
                            if(drinkOption1.get(k).getOptionCate().toLowerCase().contains("sugar")){
                                drinkInCart.setSugarLevel(drinkOption1.get(k).getValueLevel());
                            }
                            if(drinkOption1.get(k).getOptionCate().toLowerCase().contains("size")){
                                drinkInCart.setSize(drinkOption1.get(k).getOptionCate());
                            }

                        }
                    }
                }
            }
        });
    }



    private List<DrinkCateOption> getListCateOption() {
        List<DrinkCateOption> mCate = new ArrayList<>();

        List<DrinkOption> mOptionIce = new ArrayList<>();
        List<DrinkOption> mOptionSugar = new ArrayList<>();
        List<DrinkOption> mOptionSize = new ArrayList<>();

        mOptionIce.add(new DrinkOption("ice", 0));
        mOptionIce.add(new DrinkOption("ice", 30));
        mOptionIce.add(new DrinkOption("ice", 50));
        mOptionIce.add(new DrinkOption("ice", 70));
        mOptionIce.add(new DrinkOption("ice", 100));

        mOptionSugar.add(new DrinkOption("sugar", 0));
        mOptionSugar.add(new DrinkOption("sugar", 30));
        mOptionSugar.add(new DrinkOption("sugar", 50));
        mOptionSugar.add(new DrinkOption("sugar", 70));
        mOptionSugar.add(new DrinkOption("sugar", 100));

        mOptionSize.add(new DrinkOption("Size M"));
        mOptionSize.add(new DrinkOption("Size L", false,5000));

        mCate.add(new DrinkCateOption("Ice level", mOptionIce));
        mCate.add(new DrinkCateOption("Sugar level", mOptionSugar));
        mCate.add(new DrinkCateOption("Choose size", mOptionSize));

        return mCate;
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

        btnGoToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<DrinkInCart> listDrink = new ArrayList<>();
                Intent intent = new Intent(BuyNowSLSpaceScreen.this, OrderSLSpaceScreen.class);
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
                reusedConstraint.checkNavStatusComeBack(BuyNowSLSpaceScreen.this);
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
        if(drinkInCart.getDrinkPrePrice()==0){
            drinkInCart.setDrinkPrePrice(drink.getDrinkPrePrice());
        }

        if(drinkInCart.getSize()==null){
            drinkInCart.setSize("Size M");
        }
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(BuyNowSLSpaceScreen.this);
    }


}