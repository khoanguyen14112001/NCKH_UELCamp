package nguyenhoanganhkhoa.com.myapplication.home.SLSpace;

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
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class PurchaseSLSpaceScreen extends AppCompatActivity {

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
        setContentView(R.layout.activity_purchase_slspace_screen);

        linkView();
        configureRcv();
        initAdapterStatusPurchase();

        setCallBackAdapterStatus();
        initAdapterPurchaseBegin();

        addEvents();
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        reusedConstraint.setActionComeBack(this);
        imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PurchaseSLSpaceScreen.this,CartSLSpaceScreen.class));
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

        addListPurchaseStatus(list,PurchaseAdapter.TYPE_PENDING,getListPurchasePending());
        addListPurchaseStatus(list,PurchaseAdapter.TYPE_IN_PROGRESS,getListPurchaseInProgress());
        addListPurchaseStatus(list,PurchaseAdapter.TYPE_DELIVERING,getListPurchaseDelivery());
        addListPurchaseStatus(list,PurchaseAdapter.TYPE_COMPLETED,getListPurchaseCompleted());
        addListPurchaseStatus(list,PurchaseAdapter.TYPE_CANCELLED,getListPurchaseCancelled());

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
        adapterPurchase.setData(getListPurchasePending());
        rcvListPurchase.setAdapter(adapterPurchase);
    }

    private void setCallBackAdapterStatus(){
        adapterStatus.setCallBack(new PurchaseStatusAdapter.MyCallBack() {
            @Override
            public void changeDataInAdapter(String name) {
                switch (name){
                    case PurchaseAdapter.TYPE_PENDING:
                        initAdapterPurchase(getListPurchasePending());
                        break;
                    case PurchaseAdapter.TYPE_IN_PROGRESS:
                        initAdapterPurchase(getListPurchaseInProgress());
                        break;
                    case PurchaseAdapter.TYPE_CANCELLED:
                        initAdapterPurchase(getListPurchaseCancelled());
                        break;
                    case PurchaseAdapter.TYPE_COMPLETED:
                        initAdapterPurchase(getListPurchaseCompleted());
                        break;
                    case PurchaseAdapter.TYPE_DELIVERING:
                        initAdapterPurchase(getListPurchaseDelivery());
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


    private List<PurchaseItem> getListPurchasePending() {
        List<PurchaseItem> listPurchase = new ArrayList<>();
        List<DrinkInCart> list1 = new ArrayList<>();
        List<DrinkInCart> list2 = new ArrayList<>();

        addItemToList(list1,"ICED/HOT COFFEE - size M", R.drawable.img_drink_3,0,30000,
                70,50,"Size M", 1);


        addItemToList(list2,"ICE/ HOT COFFEE - size M", R.drawable.img_drink_3,0.2,25000,
                50,30,"Size M", 2);

        addItemToList(list2,"MATCHA FRAPPUCHINO - size M", R.drawable.img_drink_5,0,35000,
                70,70,"Size M", 2);

        addItemToList(list2,"SEA SODA - size M", R.drawable.img_drink_2,0.2,25000,
                70,100,"Size M", 3);

        listPurchase.add(new PurchaseItem(PurchaseAdapter.TYPE_PENDING,"220107FX9R2",quantity(list1),totalPayment(list1),list1));
        listPurchase.add(new PurchaseItem(PurchaseAdapter.TYPE_PENDING,"220107FXDFJ",quantity(list2),totalPayment(list2),list2));
        return listPurchase;
    }
    private List<PurchaseItem> getListPurchaseInProgress() {
        List<PurchaseItem> listPurchase = new ArrayList<>();
        List<DrinkInCart> list1 = new ArrayList<>();
        List<DrinkInCart> list2 = new ArrayList<>();


        addItemToList(list1,"MATCHA FRAPPUCHINO - size M", R.drawable.img_drink_5,0,35000,
                70,70,"Size M", 3);

        addItemToList(list2,"ICED/HOT COFFEE - size M", R.drawable.img_drink_3,0,30000,
                70,50,"Size L", 1);

        listPurchase.add(new PurchaseItem(PurchaseAdapter.TYPE_IN_PROGRESS,"22010DSFHU",quantity(list1),totalPayment(list1),list1));
        listPurchase.add(new PurchaseItem(PurchaseAdapter.TYPE_IN_PROGRESS,"2381FDDHOA",quantity(list2),totalPayment(list2),list2));
        return listPurchase;
    }
    private List<PurchaseItem> getListPurchaseDelivery() {
        List<PurchaseItem> listPurchase = new ArrayList<>();
        List<DrinkInCart> list1 = new ArrayList<>();


        addItemToList(list1,"MATCHA FRAPPUCHINO - size M", R.drawable.img_drink_5,0,35000,
                70,70,"Size M", 2);

        listPurchase.add(new PurchaseItem(PurchaseAdapter.TYPE_DELIVERING,"2381FDDASF",quantity(list1),totalPayment(list1),list1));
        return listPurchase;
    }
    private List<PurchaseItem> getListPurchaseCompleted() {
        List<PurchaseItem> listPurchase = new ArrayList<>();
        List<DrinkInCart> list1 = new ArrayList<>();
        List<DrinkInCart> list2 = new ArrayList<>();

        addItemToList(list1,"ICED/HOT COFFEE - size M", R.drawable.img_drink_3,0,30000,
                70,50,"Size M", 1);


        addItemToList(list2,"ICE/ HOT COFFEE - size M", R.drawable.img_drink_3,0.2,25000,
                50,30,"Size M", 2);

        addItemToList(list2,"MATCHA FRAPPUCHINO - size M", R.drawable.img_drink_5,0,35000,
                70,70,"Size M", 2);

        addItemToList(list2,"SEA SODA - size M", R.drawable.img_drink_2,0.2,25000,
                70,100,"Size M", 3);

        listPurchase.add(new PurchaseItem(PurchaseAdapter.TYPE_COMPLETED,"220107FX232",quantity(list1),totalPayment(list1),list1));
        listPurchase.add(new PurchaseItem(PurchaseAdapter.TYPE_COMPLETED,"241147FXDFJ",quantity(list2),totalPayment(list2),list2));
        return listPurchase;
    }
    private List<PurchaseItem> getListPurchaseCancelled() {
        return null;
    }



    private void addItemToList(List<DrinkInCart> list, String drinkName, int thumbDrink, double drinkDiscount, double drinkPrePrice,
                               int iceLevel, int sugarLevel, String size, int quantity){
        DrinkInCart drink = new DrinkInCart(drinkName,thumbDrink,drinkDiscount,drinkPrePrice);
        drink.setIceLevel(iceLevel);
        drink.setSugarLevel(sugarLevel);
        drink.setSize(size);
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