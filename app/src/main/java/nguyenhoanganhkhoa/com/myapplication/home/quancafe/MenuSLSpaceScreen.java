package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import nguyenhoanganhkhoa.com.adapter.DrinkAdapter;
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class MenuSLSpaceScreen extends AppCompatActivity {

    RecyclerView rcvMenu;
    ImageView imvBack, imvCart, imvLove;
    DrinkAdapter adapter = new DrinkAdapter(this);

    ReusedConstraint reusedConstraint = new ReusedConstraint(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_slspace_screen);

        linkView();
        initAdapter();
        reusedConstraint.openNav(this);
        addEvents();
    }

    private void initAdapter() {
        adapter.setData(HomeSLSpaceScreen.getListDrink());
        rcvMenu.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvMenu.setAdapter(adapter);
    }

    private void addEvents() {
        imvLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuSLSpaceScreen.this, FavoriteDrinksScreen.class));
            }
        });
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.checkNavStatusComeBack(MenuSLSpaceScreen.this);
            }
        });

        imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuSLSpaceScreen.this, CartSLSpaceScreen.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(MenuSLSpaceScreen.this);
    }

    private void linkView() {
        rcvMenu = findViewById(R.id.rcvMenu);
        imvBack = findViewById(R.id.imvBack);
        imvCart = findViewById(R.id.imvCart);

    }
}