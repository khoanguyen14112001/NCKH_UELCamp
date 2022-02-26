package nguyenhoanganhkhoa.com.myapplication.home.canteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DrinkIncartAdapter;
import nguyenhoanganhkhoa.com.models.DrinkInCart;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class CartCanteenScreen extends AppCompatActivity {

    RecyclerView rcvCart;
    DrinkIncartAdapter adapter = new DrinkIncartAdapter(this); ;
    TextView txtTotalPayment;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    DrawerLayout drawerLayout;
    TextView txtNotifyNoProduct, txtItemQuantity;
    Button btnGoToPayment;

    ImageView imvRadSelectAll, imvBack;

    boolean isSelectAllActive = false;



    private void linkView() {
        rcvCart = findViewById(R.id.rcvCart);
        txtTotalPayment = findViewById(R.id.txtTotalPayment);
        drawerLayout = findViewById(R.id.drawerLayout);
        txtNotifyNoProduct = findViewById(R.id.txtNotifyNoProduct);
        txtItemQuantity = findViewById(R.id.txtItemQuantity);
        imvRadSelectAll = findViewById(R.id.imvRadSelectAll);
        imvBack = findViewById(R.id.imvBack);
        btnGoToPayment = findViewById(R.id.btnGoToPayment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_slspace_screen);

        linkView();
        configureRecyclerView();
        initAdapter(getListDishesInCart());
        setCallBackAdapter();
        getTotalPayment(getListDishesInCart());
        getItemQuantity(getListDishesInCart().size());
        reusedConstraint.openNav(this);
        addEvents();
    }

    private void configureRecyclerView(){
        adapter.setNumScreen(DrinkIncartAdapter.IN_CART_CANTEEN_SCREEN);
        rcvCart.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void initAdapter(List<DrinkInCart> list) {
        adapter.setData(list);
        rcvCart.setAdapter(adapter);
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
                getItemQuantity(size);
            }
        });
    }

    private void getItemQuantity(int size){
        txtItemQuantity.setText("(" + size +" item)");
        if(size == 0){
            txtNotifyNoProduct.setVisibility(View.VISIBLE);
        }
    }

    public static List<DrinkInCart> getListDishesInCart() {
        List<DrinkInCart> list = new ArrayList<>();
        addItemToList(list,"Beef rice noodles", R.drawable.img_drink_1,0,30000,
                1);
        addItemToList(list,"Chicken promo noodles", R.drawable.img_drink_1,0,15000,
                2);
        addItemToList(list,"Stir-fried spaghetti with beef", R.drawable.img_drink_1,0,27000,
                3);
        addItemToList(list,"Rib rice", R.drawable.img_drink_1,0,25000,
                4);
        return list;
    }
    private void pushData(List<DrinkInCart> listDrink) {
        List<DrinkInCart> list = new ArrayList<>();
        for(int i = 0; i<listDrink.size();i++){
            if(listDrink.get(i).isSelected()){
                list.add(listDrink.get(i));
            }
        }

        if(!list.isEmpty()){
            Intent intent = new Intent(CartCanteenScreen.this, OrderCanteenScreen.class);
            intent.putExtra(AppUtil.MY_BUNDLE_TRANS, (Serializable) list);
            startActivity(intent);
        }
        else{
            Snackbar snackbar = Snackbar.make(drawerLayout, "Please choose items to purchase!", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }


    private void addEvents() {
        btnGoToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushData(adapter.getmDrinks());
            }
        });
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.checkNavStatusComeBack(CartCanteenScreen.this);
            }
        });
        imvRadSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelectAllActive){
                    imvRadSelectAll.setImageResource(R.drawable.ic_rad_yellow_unchecked);
                    isSelectAllActive = false;
                    setListAllUnchecked();
                }
                else{
                    imvRadSelectAll.setImageResource(R.drawable.ic_rad_yellow_checked);
                    isSelectAllActive = true;
                    setListAllChecked();
                }

            }
        });
    }

    private void setListAllChecked(){
        List<DrinkInCart> listDrinkNew =  adapter.getmDrinks();
        for(int i = 0; i< listDrinkNew.size();i++){
            listDrinkNew.get(i).setSelected(true);
        }
        initAdapter(listDrinkNew);
    }

    private void setListAllUnchecked(){
        List<DrinkInCart> listDrinkNew =  adapter.getmDrinks();
        for(int i = 0; i< listDrinkNew.size();i++){
            listDrinkNew.get(i).setSelected(false);
        }
        initAdapter(listDrinkNew);
    }
    private static void addItemToList(List<DrinkInCart> list, String drinkName, int thumbDrink, double drinkDiscount, double drinkPrePrice,
                                      int quantity){
        DrinkInCart drink = new DrinkInCart(drinkName,thumbDrink,drinkDiscount,drinkPrePrice);
        drink.setQuantityDrink(quantity);
        drink.setNote("no chili, no onions...");
        list.add(drink);
    }

    private void getTotalPayment(List<DrinkInCart> list){
        int i;
        double totalPay = 0;
        for(i=0;i<list.size();i++){
            totalPay = totalPay + list.get(i).getTotalPrice();
        }
        txtTotalPayment.setText(reusedConstraint.formatCurrency(totalPay));
    }
}