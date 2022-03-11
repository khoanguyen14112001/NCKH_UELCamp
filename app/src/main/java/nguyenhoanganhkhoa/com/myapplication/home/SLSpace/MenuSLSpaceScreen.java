package nguyenhoanganhkhoa.com.myapplication.home.SLSpace;

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
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class MenuSLSpaceScreen extends AppCompatActivity {
    RecyclerView rcvOrderMayLike;
    LinearLayout lnCoffee, lnTea, lnSoda, lnJuice, lnYogurt, lnMachiato, lnFrappuchino, lnDiscount;
    TextView txtShowType;

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
        setContentView(R.layout.activity_menu_slspace_screen);

        linkView();
        initAdapterDrink();
        setCallBackAdapter();
        addSearchFunction();


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
        List<Drink> drinks = HomeSLSpaceScreen.getListDrink();

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
        }
    }
    private void checkAndSetListAgain(){
        LinearLayout[] arrLn = {lnCoffee, lnTea, lnSoda, lnJuice, lnYogurt, lnMachiato, lnFrappuchino, lnDiscount};
        int i;
        for(i=0;i<arrLn.length;i++){
            arrLn[i].setBackground(getDrawable(R.drawable.view_custom_corner_small_blackstroke_thin));
            arrLn[i].setTag("off");
            txtShowType.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(MenuSLSpaceScreen.this);
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        imvPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuSLSpaceScreen.this, PurchaseSLSpaceScreen.class));
            }
        });
        layout_choose_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuSLSpaceScreen.this,ChooseAddressScreen.class));
            }
        });
        imvFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuSLSpaceScreen.this,FavoriteDrinksScreen.class));
            }
        });
        imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuSLSpaceScreen.this, CartSLSpaceScreen.class));
            }
        });
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.checkNavStatusComeBack(MenuSLSpaceScreen.this);
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




}