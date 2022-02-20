package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

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
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.models.Images;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class HomeSLSpaceScreen extends AppCompatActivity {
    RecyclerView rcvOrderMayLike;
    LinearLayout lnCoffee, lnTea, lnSoda, lnJuice, lnYogurt, lnMachiato, lnFrappuchino, lnDiscount;
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


    private void linkView() {
        rcvOrderMayLike = findViewById(R.id.rcvOrderMayLike);

        lnCoffee = findViewById(R.id.lnCoffee);
        lnTea = findViewById(R.id.lnTea);
        lnSoda = findViewById(R.id.lnSoda);
        lnJuice = findViewById(R.id.lnJuice);
        lnYogurt = findViewById(R.id.lnYogurt);
        lnMachiato = findViewById(R.id.lnMachiato);
        lnFrappuchino = findViewById(R.id.lnFrappuchino);
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
    public static final String ORDER_COFFEE = "Coffee";
    public static final String ORDER_TEA = "Tea";
    public static final String ORDER_SODA = "Soda";
    public static final String ORDER_JUICE = "Juice";
    public static final String ORDER_YOGURT = "Yogurt";
    public static final String ORDER_MACHIATO = "Machiato";
    public static final String ORDER_FRAPPUCHINO = "Frappuchino";
    public static final String ORDER_DISCOUNT = "Discount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_slspace_screen);

        linkView();
        initAdapterDrink();
        setCallBackAdapter();
        addSearchFunction();

        loadFireBaseData();


        addEvents();
    }



    DrinkAdapter adapter2 = new DrinkAdapter(this);
    public void addSearchFunction() {
        adapter2.setData(getListDrink());
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

    private void initAdapterDrink(LinearLayout layout) {

        if(layout.equals(lnCoffee)){
            adapter.setData(getListByType(ORDER_COFFEE));
        }
        else if(layout.equals(lnDiscount)){
            adapter.setData(getListByType(ORDER_DISCOUNT));
        }

        else if(layout.equals(lnFrappuchino)){
            adapter.setData(getListByType(ORDER_FRAPPUCHINO));
        }

        else if(layout.equals(lnJuice)){
            adapter.setData(getListByType(ORDER_JUICE));
        }

        else if(layout.equals(lnMachiato)){
            adapter.setData(getListByType(ORDER_MACHIATO));
        }

        else if(layout.equals(lnTea)){
            adapter.setData(getListByType(ORDER_TEA));
        }

        else if(layout.equals(lnSoda)){
            adapter.setData(getListByType(ORDER_SODA));
        }

        else if(layout.equals(lnYogurt)){
            adapter.setData(getListByType(ORDER_YOGURT));
        }

        rcvOrderMayLike.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvOrderMayLike.setAdapter(adapter);

    }
    private void initAdapterDrink() {

        adapter.setData(getListDrink());
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

    public static List<Drink> getListDrink() {
        List<Drink> drinks = new ArrayList<>();

        drinks.add(new Drink(DrinkAdapter.DRINK_TITLE_BEST_SELLER,"MATCHA FRAPPUCHINO","Frappuchino",R.drawable.img_drink_1,0,35000));
        drinks.add(new Drink("","ORANGE JUICE","Juice",R.drawable.img_drink_2,0,29000, true));
        drinks.add(new Drink("","ICED/HOT COFFEE","Coffee",R.drawable.img_drink_3,0,25000));
        drinks.add(new Drink("","SEA SODA","Soda",R.drawable.img_drink_4,0.1,25000, true));
        drinks.add(new Drink("","LEMON YOGURT","Yogurt",R.drawable.img_drink_5,0.3,25000));
        drinks.add(new Drink(DrinkAdapter.DRINK_TITLE_BEST_SELLER,"PEACH TEA","Soda",R.drawable.img_drink_6,0,25000));
        return drinks;
    }


    private void changeColorButton(LinearLayout layout){
        LinearLayout[] arrLn = {lnCoffee, lnTea, lnSoda, lnJuice, lnYogurt, lnMachiato, lnFrappuchino, lnDiscount};
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
            initAdapterDrink();
            txtShowType.setVisibility(View.GONE);
            txtMaybeLike.setVisibility(View.VISIBLE);
            txtSeeAll.setVisibility(View.VISIBLE);
        }
    }
    private void checkAndSetListAgain(){
        LinearLayout[] arrLn = {lnCoffee, lnTea, lnSoda, lnJuice, lnYogurt, lnMachiato, lnFrappuchino, lnDiscount};
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
        reusedConstraint.checkNavStatusComeBack(HomeSLSpaceScreen.this);
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        imvPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeSLSpaceScreen.this, PurchaseSLSpaceScreen.class));
            }
        });
        layout_choose_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeSLSpaceScreen.this,ChooseAddressScreen.class));
            }
        });
        imvFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeSLSpaceScreen.this,FavoriteDrinksScreen.class));
            }
        });
        imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeSLSpaceScreen.this, CartSLSpaceScreen.class));
            }
        });
        txtSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeSLSpaceScreen.this,MenuSLSpaceScreen.class));
            }
        });
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.checkNavStatusComeBack(HomeSLSpaceScreen.this);
            }
        });
        lnCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(lnCoffee);
                setTextType(ORDER_COFFEE);
                changeColorButton(lnCoffee);
            }
        });
        lnTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(lnTea);
                setTextType(ORDER_TEA);
                changeColorButton(lnTea);
            }
        });

        lnSoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(lnSoda);
                setTextType(ORDER_SODA);
                changeColorButton(lnSoda);
            }
        });

        lnJuice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(lnJuice);
                setTextType(ORDER_JUICE);
                changeColorButton(lnJuice);
            }
        });

        lnYogurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(lnYogurt);
                setTextType(ORDER_YOGURT);
                changeColorButton(lnYogurt);
            }
        });

        lnMachiato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(lnMachiato);
                setTextType(ORDER_MACHIATO);
                changeColorButton(lnMachiato);
            }
        });

        lnFrappuchino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(lnFrappuchino);
                setTextType(ORDER_FRAPPUCHINO);
                changeColorButton(lnFrappuchino);
            }
        });

        lnDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(lnDiscount);
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
           case ORDER_COFFEE:
               filterList(list,ORDER_COFFEE);
               return list;
           case ORDER_TEA:
               filterList(list,ORDER_TEA);
               return list;
           case ORDER_SODA:
               filterList(list,ORDER_SODA);
               return list;
           case ORDER_JUICE:
               filterList(list,ORDER_JUICE);
               return list;

           case ORDER_YOGURT:
               filterList(list,ORDER_YOGURT);
               return list;
           case ORDER_MACHIATO:
               filterList(list,ORDER_MACHIATO);
               return list;
           case ORDER_FRAPPUCHINO:
               filterList(list,ORDER_FRAPPUCHINO);
               return list;
           case ORDER_DISCOUNT:
               int i ;
               for(i=0;i<getListDrink().size();i++){
                   if(getListDrink().get(i).getDrinkDiscount()!=0){
                       list.add(getListDrink().get(i));
                   }
               }
               return list;
       }
       return null;
   }

   private void filterList(List<Drink> list, String type){
        int i ;
        for(i=0;i<getListDrink().size();i++){
            if(getListDrink().get(i).getDrinkType().equals(type)){
                list.add(getListDrink().get(i));
            }
        }
    }

    ImagesAdapter adapterAds = new ImagesAdapter(this);
    int currentPosition = 0;
    private static final String LINK_ADS = "ads_slspace";
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
                Toast.makeText(HomeSLSpaceScreen.this, "Fail to load Data", Toast.LENGTH_SHORT).show();
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