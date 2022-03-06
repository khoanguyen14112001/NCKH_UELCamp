package nguyenhoanganhkhoa.com.myapplication.home.canteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.PurchaseAdapter;
import nguyenhoanganhkhoa.com.adapter.PurchaseStatusAdapter;
import nguyenhoanganhkhoa.com.models.DrinkInCart;
import nguyenhoanganhkhoa.com.models.PurchaseItem;
import nguyenhoanganhkhoa.com.models.PurchaseStatus;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class PurchaseCanteenScreen extends AppCompatActivity {

    RecyclerView rcvStatusPurchase,rcvListPurchase;
    ImageView imvCart;
    PurchaseStatusAdapter adapterStatus = new PurchaseStatusAdapter(this);
    PurchaseAdapter adapterPurchase = new PurchaseAdapter(this);
    LinearLayout layout_no_order_yet;
    ReusedConstraint reusedConstraint = new ReusedConstraint();


    private void linkView() {
        rcvStatusPurchase = findViewById(R.id.rcvStatusPurchase);
        rcvListPurchase = findViewById(R.id.rcvListPurchase);
        layout_no_order_yet = findViewById(R.id.layout_no_order_yet);
        imvCart = findViewById(R.id.imvCart);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_canteen_screen);

        linkView();
        configureRcv();
        initAdapterStatusPurchase();

        setCallBackAdapterStatus();
        initAdapterPurchaseBegin();
        AppUtil.statusOrder = PurchaseAdapter.TYPE_ORDER;

        addEvents();
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        reusedConstraint.setActionComeBack(this);
        imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PurchaseCanteenScreen.this,CartCanteenScreen.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void configureRcv() {
        rcvStatusPurchase.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rcvListPurchase.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    private void initAdapterStatusPurchase() {
        adapterStatus.setData(getListPurchaseStatus());
        rcvStatusPurchase.setAdapter(adapterStatus);
    }

    private List<PurchaseStatus> getListPurchaseStatus() {
        List<PurchaseStatus> list = new ArrayList<>();

        addListPurchaseStatus(list,PurchaseAdapter.TYPE_ORDER,getListPurchaseOrder());
        addListPurchaseStatus(list,PurchaseAdapter.TYPE_COMPLETED,getListPurchaseCompleted());
        addListPurchaseStatus(list,PurchaseAdapter.TYPE_NOT_COMPLETED,getListPurchaseNotCompleted());

        return list;

    }

    private void addListPurchaseStatus(List<PurchaseStatus> list, String text, List<PurchaseItem> listPurchase){
        if(listPurchase!=null){
            list.add(new PurchaseStatus(text,listPurchase.size()));
        }
        else{
            list.add(new PurchaseStatus(text,0));
        }
    }


    private void initAdapterPurchaseBegin() {
        adapterPurchase.setScreen(PurchaseAdapter.PURCHASE_CANTEEN_SCREEN);
        adapterPurchase.setData(getListPurchaseOrder());
        rcvListPurchase.setAdapter(adapterPurchase);
    }

    private void setCallBackAdapterStatus(){
        adapterStatus.setCallBack(new PurchaseStatusAdapter.MyCallBack() {
            @Override
            public void changeDataInAdapter(String name) {
                switch (name){
                    case PurchaseAdapter.TYPE_ORDER:
                        initAdapterPurchase(getListPurchaseOrder());
                        break;
                    case PurchaseAdapter.TYPE_COMPLETED:
                        initAdapterPurchase(getListPurchaseCompleted());
                        break;
                    case PurchaseAdapter.TYPE_NOT_COMPLETED:
                        initAdapterPurchase(getListPurchaseNotCompleted());
                        break;

                }
            }
        });
    }

    private void initAdapterPurchase(List<PurchaseItem> list){
        if(list == null){
            rcvListPurchase.setVisibility(View.GONE);
            layout_no_order_yet.setVisibility(View.VISIBLE);
        }
        else{
            rcvListPurchase.setVisibility(View.VISIBLE);
            layout_no_order_yet.setVisibility(View.GONE);

            adapterPurchase.setData(list);
            rcvListPurchase.setAdapter(adapterPurchase);
        }


    }


    private List<PurchaseItem> getListPurchaseOrder() {
        List<PurchaseItem> listPurchase = new ArrayList<>();
        List<DrinkInCart> list1 = new ArrayList<>();
        List<DrinkInCart> list2 = new ArrayList<>();

        addItemToList(list1,"BEEF RICE NOODLES", R.drawable.img_dish_1,0,30000,
                1);
        addItemToList(list1,"CHICKEN PROMO NOODLES", R.drawable.img_dish_2,0,15000,
                2);
        addItemToList(list2,"STIR-FRIED SPAGHETTI WITH BEEF", R.drawable.img_dish_3,0,27000,
                3);
        addItemToList(list2,"RIB RICE", R.drawable.img_dish_4,0,25000,
                4);

        listPurchase.add(new PurchaseItem(PurchaseAdapter.TYPE_ORDER,"220107FX9R2",quantity(list1),totalPayment(list1),list1));
        listPurchase.add(new PurchaseItem(PurchaseAdapter.TYPE_ORDER,"220107FXDFJ",quantity(list2),totalPayment(list2),list2));
        return listPurchase;
    }
    private List<PurchaseItem> getListPurchaseNotCompleted() {
        List<PurchaseItem> listPurchase = new ArrayList<>();
        List<DrinkInCart> list1 = new ArrayList<>();


        addItemToList(list1,"BEEF RICE NOODLES", R.drawable.img_dish_1,0,30000,
                1);


        listPurchase.add(new PurchaseItem(PurchaseAdapter.TYPE_NOT_COMPLETED,"2381FDDASF",quantity(list1),totalPayment(list1),list1));
        return listPurchase;
    }
    private List<PurchaseItem> getListPurchaseCompleted() {
        List<PurchaseItem> listPurchase = new ArrayList<>();
        List<DrinkInCart> list1 = new ArrayList<>();
        List<DrinkInCart> list2 = new ArrayList<>();

        addItemToList(list1,"BEEF RICE NOODLES", R.drawable.img_dish_1,0,30000,
                1);
        addItemToList(list1,"CHICKEN PROMO NOODLES", R.drawable.img_dish_2,0,15000,
                2);
        addItemToList(list2,"STIR-FRIED SPAGHETTI WITH BEEF", R.drawable.img_dish_3,0,27000,
                3);
        addItemToList(list2,"RIB RICE", R.drawable.img_dish_4,0,25000,
                4);


        listPurchase.add(new PurchaseItem(PurchaseAdapter.TYPE_COMPLETED,"220107FX232",quantity(list1),totalPayment(list1),list1));
        listPurchase.add(new PurchaseItem(PurchaseAdapter.TYPE_COMPLETED,"241147FXDFJ",quantity(list2),totalPayment(list2),list2));
        return listPurchase;
    }



    private void addItemToList(List<DrinkInCart> list, String drinkName, int thumbDrink, double drinkDiscount, double drinkPrePrice,
                               int quantity){
        DrinkInCart drink = new DrinkInCart(drinkName,thumbDrink,drinkDiscount,drinkPrePrice);
        drink.setNote("no chili, no onions...");
        drink.setQuantityDrink(quantity);

        list.add(drink);
    }

    private int quantity(List<DrinkInCart> list){
        if(!list.isEmpty()){
            int totalMoney = 0;
            for (int i = 0; i<list.size();i++){
                totalMoney += list.get(i).getQuantityDrink();
            }
            return totalMoney;
        }
        else{
            return 0;
        }
    }

    private double totalPayment(List<DrinkInCart> list){
        if(!list.isEmpty()){
            double totalMoney = 0;
            for (int i = 0; i<list.size();i++){
                totalMoney += list.get(i).getTotalPrice();
            }
            return totalMoney;
        }
        else{
            return 0;
        }
    }


}