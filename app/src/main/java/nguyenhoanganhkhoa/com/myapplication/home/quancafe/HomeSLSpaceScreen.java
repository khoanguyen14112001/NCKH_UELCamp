package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DialogNotificationAdapter;
import nguyenhoanganhkhoa.com.adapter.DrinkAdapter;
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class HomeSLSpaceScreen extends AppCompatActivity {
    RecyclerView rcvOrderMayLike;
    LinearLayout lnCoffee, lnTea, lnSoda, lnJuice, lnYogurt, lnMachiato, lnFrappuchino, lnDiscount;
    TextView txtShowType, txtMaybeLike,txtSeeAll;

    ImageView imvBack;

    SearchView svOrder;

    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    DrinkAdapter adapter = new DrinkAdapter(this);

    ConstraintLayout layout_hide_filter;

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

        layout_hide_filter = findViewById(R.id.layout_hide_filter);
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
        reusedConstraint.openNav(this);
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

    private List<Drink> getListDrink() {
        List<Drink> drinks = new ArrayList<>();

        drinks.add(new Drink("","RASPBERRY FRAPPUCHINO - size M","Frappuchino",R.drawable.img_news1,0,30000));
        drinks.add(new Drink("","PEACH & LYCHEE FRAPPUCHINO - size M","Coffee",R.drawable.img_news2,0.1,30000, true));
        drinks.add(new Drink("","MILK TEA - size M","Tea",R.drawable.img_news3,0.1,25000));
        drinks.add(new Drink("","FRESH MILK - size M","Yogurt",R.drawable.img_news4,0,22000, true));
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
}