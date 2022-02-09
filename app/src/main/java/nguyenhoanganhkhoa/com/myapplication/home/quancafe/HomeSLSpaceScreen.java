package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DrinkAdapter;
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

public class HomeSLSpaceScreen extends AppCompatActivity {
    RecyclerView rcvOrderMayLike;
    LinearLayout lnCoffee, lnTea, lnSoda, lnJuice, lnYogurt, lnMachiato, lnFrappuchino, lnDiscount;

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
        initAdapterDrink(getListDrink());
        addEvents();
    }



    private void initAdapterDrink(List<Drink> list) {
        DrinkAdapter adapter = new DrinkAdapter(this);
        adapter.setData(list);
        rcvOrderMayLike.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvOrderMayLike.setAdapter(adapter);
    }

    private List<Drink> getListDrink() {
        List<Drink> drinks = new ArrayList<>();

        drinks.add(new Drink("","RASPBERRY FRAPPUCHINO - size M","Frappuchino",R.drawable.img_news1,0,30000));
        drinks.add(new Drink("","PEACH & LYCHEE FRAPPUCHINO - size M","Coffee",R.drawable.img_news2,0.1,30000, true));
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
            initAdapterDrink(getListDrink());
        }
    }

    private void addEvents() {
        lnCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(getListByType(ORDER_COFFEE));
                changeColorButton(lnCoffee);
            }
        });
        lnTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(getListByType(ORDER_TEA));
                changeColorButton(lnTea);
            }
        });

        lnSoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(getListByType(ORDER_SODA));
                changeColorButton(lnSoda);
            }
        });

        lnJuice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(getListByType(ORDER_JUICE));
                changeColorButton(lnJuice);
            }
        });

        lnYogurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(getListByType(ORDER_YOGURT));
                changeColorButton(lnYogurt);
            }
        });

        lnMachiato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(getListByType(ORDER_MACHIATO));
                changeColorButton(lnMachiato);
            }
        });

        lnFrappuchino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(getListByType(ORDER_FRAPPUCHINO));
                changeColorButton(lnFrappuchino);
            }
        });

        lnDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapterDrink(getListByType(ORDER_DISCOUNT));
                changeColorButton(lnDiscount);
            }
        });
    }

    private void changeTypeDrink(String type){

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