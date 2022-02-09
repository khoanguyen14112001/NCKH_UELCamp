package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DrinkAdapter;
import nguyenhoanganhkhoa.com.adapter.OrderCateAdapter;
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.models.OrderCategories;
import nguyenhoanganhkhoa.com.myapplication.R;

public class HomeSLSpaceScreen extends AppCompatActivity {

    RecyclerView rcvOrderCate, rcvOrderMayLike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_slspace_screen);

        linkView();
        initAdapterType();
        initAdapterDrink();
        addEvents();
    }

    private void initAdapterDrink() {
        DrinkAdapter adapter = new DrinkAdapter(this);
        adapter.setData(getListDrink());
        rcvOrderMayLike.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvOrderMayLike.setAdapter(adapter);
    }

    private List<Drink> getListDrink() {
        List<Drink> drinks = new ArrayList<>();

        drinks.add(new Drink("","RASPBERRY FRAPPUCHINO - size M","Frappuchino",R.drawable.img_news1,0,30000));
        drinks.add(new Drink("","PEACH & LYCHEE FRAPPUCHINO - size M","Frappuchino",R.drawable.img_news2,0.1,30000, true));
        return drinks;
    }

    private void initAdapterType() {
        OrderCateAdapter adapter = new OrderCateAdapter(this);
        adapter.setData(getListOrderType());
        rcvOrderCate.setLayoutManager(new GridLayoutManager(this, 4));
        rcvOrderCate.setAdapter(adapter);
    }

    private List<OrderCategories> getListOrderType() {
        List<OrderCategories> list = new ArrayList<>();
        list.add(new OrderCategories(OrderCateAdapter.ORDER_COFFEE,R.drawable.ic_order_coffee));
        list.add(new OrderCategories(OrderCateAdapter.ORDER_TEA,R.drawable.ic_order_tea));
        list.add(new OrderCategories(OrderCateAdapter.ORDER_SODA,R.drawable.ic_order_soda));
        list.add(new OrderCategories(OrderCateAdapter.ORDER_JUICE,R.drawable.ic_order_juice));
        list.add(new OrderCategories(OrderCateAdapter.ORDER_YOGURT,R.drawable.ic_order_yogurt));
        list.add(new OrderCategories(OrderCateAdapter.ORDER_MACHIATO,R.drawable.ic_order_machiato));
        list.add(new OrderCategories(OrderCateAdapter.ORDER_FRAPPUCHINO,R.drawable.ic_order_frappuchino));
        list.add(new OrderCategories(OrderCateAdapter.ORDER_DISCOUNT,R.drawable.ic_order_discount));
         return list;
    }

    private void addEvents() {
    }

    private void linkView() {
        rcvOrderCate = findViewById(R.id.rcvOrderCate);
        rcvOrderMayLike = findViewById(R.id.rcvOrderMayLike);
    }
}