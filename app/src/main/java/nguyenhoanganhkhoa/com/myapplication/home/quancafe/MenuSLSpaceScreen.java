package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    ConstraintLayout layout_choose_address;

    ReusedConstraint reusedConstraint = new ReusedConstraint(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_slspace_screen);

        linkView();
        initAdapter();
        addEvents();
    }

    private void initAdapter() {
        adapter.setData(HomeSLSpaceScreen.getListDrink());
        rcvMenu.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvMenu.setAdapter(adapter);
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        layout_choose_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuSLSpaceScreen.this, ChooseAddressScreen.class));

            }
        });
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
        layout_choose_address = findViewById(R.id.layout_choose_address);
        imvLove = findViewById(R.id.imvLove);

    }
}