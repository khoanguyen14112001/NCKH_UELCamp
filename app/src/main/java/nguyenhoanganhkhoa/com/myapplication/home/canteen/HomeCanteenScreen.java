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

public class HomeCanteenScreen extends AppCompatActivity {
    RecyclerView rcvOrderMayLike;
    LinearLayout lnRiceNoodle, lnRice, lnStirFriedNoodle, lnPho, lnPromoNoodle, lnTopping, lnDrink, lnDiscount;
    TextView txtShowType, txtMaybeLike,txtSeeAll;

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
        setContentView(R.layout.activity_home_canteen_screen);

        linkView();
        initAdapterDishes();
        setCallBackAdapter();
        addSearchFunction();

        loadFireBaseData();

        addEvents();
    }


    DrinkAdapter adapter2 = new DrinkAdapter(this);

    public static final String ORDER_RICE_NOODLES = "Rice noodles";
    public static final String ORDER_RICE = "Rice";
    public static final String ORDER_STIR_FRIED_NOODLES = "Stir-fried noodles";
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


        txtMaybeLike = findViewById(R.id.txtMaybeLike);
        txtShowType = findViewById(R.id.txtShowType);
        txtSeeAll = findViewById(R.id.txtSeeAll);

        imvBack = findViewById(R.id.imvBack);
        svOrder = findViewById(R.id.svOrder);
        imvCart = findViewById(R.id.imvCart);

        imvFavorite = findViewById(R.id.imvFavorite);

        layout_hide_filter = findViewById(R.id.layout_hide_filter);
        layout_choose_address = findViewById(R.id.layout_choose_address);

        imvPending = findViewById(R.id.imvPending);

        viewPagerAds = findViewById(R.id.viewPagerAds);
        layout_dots_news = findViewById(R.id.layout_dots_news);
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

    public static String content_dish = "Hue people's characteristic is that they " +
            "like to eat spicy food, so each bowl of vermicelli comes with a l" +
            "ittle bit of satay chili, which adds color to the dish and stimulates the " +
            "taste buds. Diners can put some raw vegetables in the bowl and then use chopsticks " +
            "to mix the vermicelli bowl. Each strand of vermicelli blends in the sweetness.";

    public static String comment_demo = "Delicious drink, not too sweet nor too bitter, fast delivery, cute shipper.";
    public static List<Drink> getListDishes() {
        List<Drink> drinks = new ArrayList<>();
        Drink drink1 = new Drink(DrinkAdapter.DRINK_TITLE_BEST_SELLER,"BEEF RICE NOODLES",ORDER_RICE_NOODLES,R.drawable.img_dish_1,0,35000);
        Drink drink2 = new Drink("","CHICKEN PROMO NOODLES",ORDER_PROMO_NOODLES,R.drawable.img_dish_2,0,29000, true);
        Drink drink3 = new Drink("","STIR-FRIED SPAGHETTI WITH BEEF",ORDER_STIR_FRIED_NOODLES,R.drawable.img_dish_3,0,25000);
        Drink drink4 = new Drink("","RIB RICE",ORDER_RICE,R.drawable.img_dish_4,0.1,25000, true);
        Drink drink5 = new Drink("","FLUSHING FAT CHICKEN RICE",ORDER_RICE,R.drawable.img_dish_5,0.3,25000);

        drink1.setDrinkDes(content_dish);
        drink2.setDrinkDes(content_dish);
        drink3.setDrinkDes(content_dish);
        drink4.setDrinkDes(content_dish);
        drink5.setDrinkDes(content_dish);

        drink1.setCommentsList(getListComment1());
        drink2.setCommentsList(getListComment2());


        drinks.add(drink1);
        drinks.add(drink2);
        drinks.add(drink3);
        drinks.add(drink4);
        drinks.add(drink5);

        return drinks;
    }

    public static List<Comments> getListComment1(){
        List<Comments> list = new ArrayList<>();
        Comments comment1 = new Comments(R.drawable.img_heo,"Ý Heo",
                "21 Oct 2022, 10:07",4,
                comment_demo,getListImagesComment1());
        comment1.setGoodValue(true);
        comment1.setGoodProduct(true);

        Comments comment2 = new Comments(R.drawable.img_avatar_male,"AKhoa provjp",
                "21 Oct 2021, 10:07",3,
                comment_demo,getListImagesComment2());
        comment2.setGoodProduct(true);
        comment2.setGoodDelivery(true);
        comment2.setGoodPackaging(true);
        comment2.setGoodSeller(true);
        comment2.setGoodValue(true);


        Comments comment3 =new Comments(R.drawable.img_avatar_female,"Noob",
                "21 Oct 1999, 10:07",1,
                comment_demo,null);
        comment3.setGoodSeller(true);

        Comments comment4 =new Comments(R.drawable.img_avatar_male,"Gà",
                "21 Oct 2133, 10:07",5,
                comment_demo,getListImagesComment3());
        comment4.setGoodSeller(true);
        comment4.setGoodPackaging(true);

        list.add(comment1);
        list.add(comment2);
        list.add(comment3);
        list.add(comment4);

        return list;
    }

    private static List<Comments> getListComment2() {
        List<Comments> list = new ArrayList<>();
        Comments comment1 = new Comments(R.drawable.img_heo,"Ý Heo",
                "21 Oct 2022, 10:07",4,
                comment_demo,null);



        Comments comment3 =new Comments(R.drawable.img_avatar_female,"Noob",
                "21 Oct 1999, 10:07",4,
                comment_demo,null);


        list.add(comment1);
        list.add(comment3);
        return list;
    }

    private static List<ImagesVideoEvaluate> getListImagesComment3() {
        List<ImagesVideoEvaluate> list = new ArrayList<>();
        list.add(new ImagesVideoEvaluate(R.drawable.img_dish_1));
        list.add(new ImagesVideoEvaluate(R.drawable.img_dish_2));
        list.add(new ImagesVideoEvaluate(R.drawable.img_dish_1));
        list.add(new ImagesVideoEvaluate(R.drawable.img_dish_3));
        list.add(new ImagesVideoEvaluate(R.drawable.img_dish_5));
        list.add(new ImagesVideoEvaluate(R.drawable.img_dish_4));
        return list;
    }

    private static List<ImagesVideoEvaluate> getListImagesComment1() {
        List<ImagesVideoEvaluate> list = new ArrayList<>();
        list.add(new ImagesVideoEvaluate(R.drawable.img_dish_1));
        list.add(new ImagesVideoEvaluate(R.drawable.img_dish_2));
        list.add(new ImagesVideoEvaluate(R.drawable.img_dish_1));
        list.add(new ImagesVideoEvaluate(R.drawable.img_dish_4));
        return list;
    }

    private static List<ImagesVideoEvaluate> getListImagesComment2() {
        List<ImagesVideoEvaluate> list = new ArrayList<>();
        list.add(new ImagesVideoEvaluate(R.drawable.img_dish_5));
        return list;
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
            txtMaybeLike.setVisibility(View.VISIBLE);
            txtSeeAll.setVisibility(View.VISIBLE);
        }
    }

    private void checkAndSetListAgain(){
        LinearLayout[] arrLn = {lnRice, lnRiceNoodle, lnPromoNoodle, lnTopping, lnDrink, lnPho, lnStirFriedNoodle, lnDiscount};
        int i;
        for(i=0;i<arrLn.length;i++){
            arrLn[i].setBackground(getDrawable(R.drawable.view_custom_corner_small_blackstroke_thin));
            arrLn[i].setTag("off");
            txtShowType.setVisibility(View.GONE);
            txtMaybeLike.setVisibility(View.VISIBLE);
            txtSeeAll.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(HomeCanteenScreen.this);
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        imvPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeCanteenScreen.this, PurchaseCanteenScreen.class));
            }
        });

        imvFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeCanteenScreen.this, FavoriteDishesScreen.class));
            }
        });
        imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeCanteenScreen.this, CartCanteenScreen.class));
            }
        });
        txtSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeCanteenScreen.this, MenuCanteenScreen.class));
            }
        });
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.checkNavStatusComeBack(HomeCanteenScreen.this);
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
    }

    private void setTextType(String type){
        txtShowType.setVisibility(View.VISIBLE);
        txtShowType.setText(type);
        txtMaybeLike.setVisibility(View.GONE);
        txtSeeAll.setVisibility(View.GONE);
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
    ImagesAdapter adapterAds = new ImagesAdapter(this);
    int currentPosition = 0;
    private static final String LINK_ADS = "ads_canteen";
    Timer timer;


    DatabaseReference databaseReferenceAds =  FirebaseDatabase.getInstance().getReference();

    private void loadFireBaseData() {
        List<Images> list = new ArrayList<>();
        databaseReferenceAds.child(LINK_ADS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()){
                    try {
                        Images images;
                        images = new Images(data.getValue().toString());
                        list.add(images);
                    }
                    catch (Exception e){
                        Log.d("Error", "Cannot load news image from firebase: " + e);
                    }
                }
                initAdapterAds(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeCanteenScreen.this, "Fail to load Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initAdapterAds(List<Images> list) {
        adapterAds.setListImages(list);
        adapterAds.setLayout(R.layout.item_ads_slspace);
        viewPagerAds.setAdapter(adapterAds);
        setAutoViewpager(list.size());
    }

    private void setAutoViewpager(int size){
        viewPagerAds.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                reusedConstraint.seletedIndicator(dots, position);
                currentPosition = position;
                super.onPageSelected(position);
            }
        });
        createSlideShow(size);
        addDots(size);

    }

    private void addDots(int size) {
        dots = new TextView[size];
        reusedConstraint.prepareDots(this,size,layout_dots_news,dots,12);
    }

    private void createSlideShow(int size) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(currentPosition == size){
                    currentPosition = 0;
                }
                viewPagerAds.setCurrentItem(currentPosition++,true);

            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 150,2500);

    }

}