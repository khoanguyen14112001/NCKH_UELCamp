package nguyenhoanganhkhoa.com.myapplication.home.canteen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import nguyenhoanganhkhoa.com.adapter.DrinkAdapter;
import nguyenhoanganhkhoa.com.adapter.ImagesAdapter;
import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogFourButton;
import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogThreeButton;
import nguyenhoanganhkhoa.com.models.Comments;
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.models.Images;
import nguyenhoanganhkhoa.com.models.ImagesVideoEvaluate;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.CartSLSpaceScreen;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.FavoriteDrinksScreen;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.MenuSLSpaceScreen;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.PurchaseSLSpaceScreen;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class MenuCanteenScreen extends AppCompatActivity {
    RecyclerView rcvOrderMayLike;
    LinearLayout lnRiceNoodle, lnRice, lnStirFriedNoodle, lnPho, lnPromoNoodle, lnTopping, lnDrink, lnDiscount;
    TextView txtShowType, txtLocation;

    ConstraintLayout layout_choose_address;

    ImageView imvBack, imvCart, imvFavorite, imvPending;

    SearchView svOrder;

    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    DrinkAdapter adapter = new DrinkAdapter(this);

    ConstraintLayout layout_hide_filter;

    ViewPager2 viewPagerAds;
    TextView dots[];
    LinearLayout layout_dots_news;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_canteen_screen);

        linkView();
        initAdapterDishes();
        setCallBackAdapter();
        addSearchFunction();

        addEvents();
    }


    DrinkAdapter adapter2 = new DrinkAdapter(this);

    public static final String ORDER_RICE_NOODLES = "Rice noodles";
    public static final String ORDER_RICE = "Rice";
    public static final String ORDER_STIR_FRIED_NOODLES = "Fried noodles";
    public static final String ORDER_PHO = "Pho";
    public static final String ORDER_PROMO_NOODLES = "Promo noodles";
    public static final String ORDER_TOPPING = "Topping";
    public static final String ORDER_DRINK = "Drink";

    public static final String ORDER_DISCOUNT = "Discount";


    private void linkView() {
        rcvOrderMayLike = findViewById(R.id.rcvOrderMayLike);

        lnRiceNoodle = findViewById(R.id.lnCoffee);
        lnRice = findViewById(R.id.lnTea);
        lnStirFriedNoodle = findViewById(R.id.lnSoda);
        lnPho = findViewById(R.id.lnJuice);
        lnPromoNoodle = findViewById(R.id.lnYogurt);
        lnTopping = findViewById(R.id.lnMachiato);
        lnDrink = findViewById(R.id.lnFrappuchino);
        lnDiscount = findViewById(R.id.lnDiscount);


        txtShowType = findViewById(R.id.txtShowType);

        imvBack = findViewById(R.id.imvBack);
        svOrder = findViewById(R.id.svOrder);
        imvCart = findViewById(R.id.imvCart);

        imvFavorite = findViewById(R.id.imvFavorite);

        layout_hide_filter = findViewById(R.id.layout_hide_filter);
        layout_choose_address = findViewById(R.id.layout_choose_address);

        imvPending = findViewById(R.id.imvPending);

        viewPagerAds = findViewById(R.id.viewPagerAds);
        layout_dots_news = findViewById(R.id.layout_dots_news);

        txtLocation = findViewById(R.id.txtLocation);
    }


    private void addSearchFunction() {
        adapter2.setData(getListDishes());
        svOrder.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    adapter2.getFilter().filter(query);
                    rcvOrderMayLike.setAdapter(adapter2);
                }
                catch (Exception e){
                    Log.d("Error", "onQueryTextSubmit: " + e);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    adapter2.getFilter().filter(newText);
                    rcvOrderMayLike.setAdapter(adapter2);

                }
                catch (Exception e){
                    Log.d("Error", "onQueryTextSubmit: " + e);
                }
                return false;
            }
        });
    }

    private void initAdapterDishes(LinearLayout layout) {
        if(layout.equals(lnRiceNoodle)){
            adapter.setData(getListByType(ORDER_RICE_NOODLES));
        }
        else if(layout.equals(lnDiscount)){
            adapter.setData(getListByType(ORDER_DISCOUNT));
        }

        else if(layout.equals(lnStirFriedNoodle)){
            adapter.setData(getListByType(ORDER_STIR_FRIED_NOODLES));
        }

        else if(layout.equals(lnPho)){
            adapter.setData(getListByType(ORDER_PHO));
        }

        else if(layout.equals(lnPromoNoodle)){
            adapter.setData(getListByType(ORDER_PROMO_NOODLES));
        }

        else if(layout.equals(lnTopping)){
            adapter.setData(getListByType(ORDER_TOPPING));
        }

        else if(layout.equals(lnDrink)){
            adapter.setData(getListByType(ORDER_DRINK));
        }

        else if(layout.equals(lnRice)){
            adapter.setData(getListByType(ORDER_RICE));
        }

        rcvOrderMayLike.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvOrderMayLike.setAdapter(adapter);
    }

    private void initAdapterDishes() {
        adapter.setData(getListDishes());
        adapter.setScreen(DrinkAdapter.HOME_MENU_CANTEEN_SCREEN);
        rcvOrderMayLike.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvOrderMayLike.setAdapter(adapter);

    }

    private List<Drink> getListDishes() {
        return HomeCanteenScreen.getListDishes();
    }

    private void setCallBackAdapter(){

        adapter2.setCallBack(new DrinkAdapter.MyCallBack() {
            @Override
            public void hideFilter() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        layout_hide_filter.setVisibility(View.GONE);
                    }
                });

            }

            @Override
            public void showFilter() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        layout_hide_filter.setVisibility(View.VISIBLE);
                    }
                });
            }
            @Override
            public void showListAgain() {
                checkAndSetListAgain();
            }

        });
    }


    private void changeColorButton(LinearLayout layout){
        LinearLayout[] arrLn = {lnRice, lnRiceNoodle, lnPromoNoodle, lnTopping, lnDrink, lnPho, lnStirFriedNoodle, lnDiscount};
        if (layout.getTag()==null| layout.getTag() == "off")
        {
            layout.setBackground(getDrawable(R.drawable.view_custom_corner_small_blackstroke_thin_yellow));
            layout.setTag("on");

        }
        else
        {
            layout.setBackground(getDrawable(R.drawable.view_custom_corner_small_blackstroke_thin));
            layout.setTag("off");
        }

        Log.d("TAG", "changeColorButton: " + layout.getTag());

        int i;
        for(i=0;i<arrLn.length;i++){
            if(!arrLn[i].equals(layout)){
                arrLn[i].setBackground(getDrawable(R.drawable.view_custom_corner_small_blackstroke_thin));
                arrLn[i].setTag("off");
            }
        }

        if(layout.getTag() == "off"){
            initAdapterDishes();
            txtShowType.setVisibility(View.GONE);
        }
    }

    private void checkAndSetListAgain(){
        LinearLayout[] arrLn = {lnRice, lnRiceNoodle, lnPromoNoodle, lnTopping, lnDrink, lnPho, lnStirFriedNoodle, lnDiscount};
        int i;
        for(i=0;i<arrLn.length;i++){
            arrLn[i].setBackground(getDrawable(R.drawable.view_custom_corner_small_blackstroke_thin));
            arrLn[i].setTag("off");
            txtShowType.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(MenuCanteenScreen.this);
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        imvPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuCanteenScreen.this, PurchaseCanteenScreen.class));
            }
        });

        imvFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuCanteenScreen.this, FavoriteDishesScreen.class));
            }
        });
        imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuCanteenScreen.this, CartCanteenScreen.class));
            }
        });

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.checkNavStatusComeBack(MenuCanteenScreen.this);
            }
        });
        lnStirFriedNoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDishes(lnStirFriedNoodle);
                setTextType(ORDER_STIR_FRIED_NOODLES);
                changeColorButton(lnStirFriedNoodle);
            }
        });
        lnPho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDishes(lnPho);
                setTextType(ORDER_PHO);
                changeColorButton(lnPho);
            }
        });

        lnDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDishes(lnDrink);
                setTextType(ORDER_DRINK);
                changeColorButton(lnDrink);
            }
        });

        lnTopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDishes(lnTopping);
                setTextType(ORDER_TOPPING);
                changeColorButton(lnTopping);
            }
        });

        lnPromoNoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDishes(lnPromoNoodle);
                setTextType(ORDER_PROMO_NOODLES);
                changeColorButton(lnPromoNoodle);
            }
        });

        lnRiceNoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDishes(lnRiceNoodle);
                setTextType(ORDER_RICE_NOODLES);
                changeColorButton(lnRiceNoodle);
            }
        });

        lnRice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDishes(lnRice);
                setTextType(ORDER_RICE);
                changeColorButton(lnRice);
            }
        });

        lnDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDishes(lnDiscount);
                setTextType(ORDER_DISCOUNT);
                changeColorButton(lnDiscount);
            }
        });
        layout_choose_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogFourButton dialog = new CustomDialogFourButton(MenuCanteenScreen.this, R.layout.custom_dialog_four_button);
                dialog.txtHeaderDialog.setText("Choose other location");
                dialog.btnTakePhotos.setText("Canteen 1");
                dialog.btnChooseFromGallery.setText("Canteen 2");
                dialog.btnMainCanteen.setText("Main canteen");

                dialog.btnMainCanteen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtLocation.setText("Main Canteen");
                        dialog.dismiss();
                    }
                });
                dialog.btnTakePhotos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtLocation.setText("Canteen 1");
                        dialog.dismiss();
                    }
                });
                dialog.btnChooseFromGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtLocation.setText("Canteen 2");
                        dialog.dismiss();
                    }
                });

                dialog.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

        });
    }

    private void setTextType(String type){
        txtShowType.setVisibility(View.VISIBLE);
        txtShowType.setText(type);
    }

    private List<Drink> getListByType(String type){
        List<Drink> list = new ArrayList<>();
        switch (type){
            case ORDER_DRINK:
                filterList(list,ORDER_DRINK);
                return list;
            case ORDER_PHO:
                filterList(list,ORDER_PHO);
                return list;
            case ORDER_RICE:
                filterList(list,ORDER_RICE);
                return list;
            case ORDER_PROMO_NOODLES:
                filterList(list,ORDER_PROMO_NOODLES);
                return list;

            case ORDER_RICE_NOODLES:
                filterList(list,ORDER_RICE_NOODLES);
                return list;
            case ORDER_STIR_FRIED_NOODLES:
                filterList(list,ORDER_STIR_FRIED_NOODLES);
                return list;
            case ORDER_TOPPING:
                filterList(list,ORDER_TOPPING);
                return list;
            case ORDER_DISCOUNT:
                int i ;
                for(i=0;i<getListDishes().size();i++){
                    if(getListDishes().get(i).getDrinkDiscount()!=0){
                        list.add(getListDishes().get(i));
                    }
                }
                return list;
        }
        return null;
    }

    private void filterList(List<Drink> list, String type){
        int i ;
        for(i=0;i<getListDishes().size();i++){
            if(getListDishes().get(i).getDrinkType().equals(type)){
                list.add(getListDishes().get(i));
            }
        }
    }

}