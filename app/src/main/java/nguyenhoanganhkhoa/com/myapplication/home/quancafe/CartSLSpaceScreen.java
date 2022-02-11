package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DrinkIncartAdapter;
import nguyenhoanganhkhoa.com.models.DrinkInCart;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class CartSLSpaceScreen extends AppCompatActivity {

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
        initAdapter(getListDrinkInCart());
        setCallBackAdapter();
        getTotalPayment(getListDrinkInCart());
        reusedConstraint.openNav(this);
        addEvents();
    }

    private void configureRecyclerView(){
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
                txtItemQuantity.setText("(" + size +" item)");
                if(size == 0){
                    txtNotifyNoProduct.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private List<DrinkInCart> getListDrinkInCart() {
        List<DrinkInCart> list = new ArrayList<>();
        addItemToList(list,"ICED/HOT COFFEE - size M", R.drawable.img_news1,0,30000,
                70,50,"Size L", 1);
        addItemToList(list,"MATCHA FRAPPUCHINO - size M", R.drawable.img_news2,0.2,25000,
                70,50,"Size L", 2);
        addItemToList(list,"SEA SODA - size M", R.drawable.img_news3,0,30000,
                70,50,"Size L", 3);

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
            Intent intent = new Intent(CartSLSpaceScreen.this, OrderDetailSLSpaceScreen.class);
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
                reusedConstraint.checkNavStatusComeBack(CartSLSpaceScreen.this);
            }
        });
        imvRadSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelectAllActive){
                    imvRadSelectAll.setImageResource(R.drawable.ic_rad_yellow_unchecked);
                    isSelectAllActive = false;
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
    private void addItemToList(List<DrinkInCart> list, String drinkName, int thumbDrink, double drinkDiscount, double drinkPrePrice,
                               int iceLevel, int sugarLevel, String size, int quantity ){
        DrinkInCart drink = new DrinkInCart(drinkName,thumbDrink,drinkDiscount,drinkPrePrice);
        drink.setIceLevel(iceLevel);
        drink.setSugarLevel(sugarLevel);
        drink.setSize(size);
        drink.setQuantityDrink(quantity);

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