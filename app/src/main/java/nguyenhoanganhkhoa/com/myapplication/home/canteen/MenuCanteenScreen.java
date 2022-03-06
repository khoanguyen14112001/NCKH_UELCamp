package nguyenhoanganhkhoa.com.myapplication.home.canteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nguyenhoanganhkhoa.com.adapter.DrinkAdapter;
import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogThreeButton;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.canteen.HomeCanteenScreen;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class MenuCanteenScreen extends AppCompatActivity {

    RecyclerView rcvMenu;
    ImageView imvBack, imvCart, imvLove, imvPending;
    DrinkAdapter adapter = new DrinkAdapter(this);
    ConstraintLayout layout_choose_address;

    TextView txtChooseYourAddress, txtTitleDelivery;

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
        adapter.setData(HomeCanteenScreen.getListDishes());
        adapter.setScreen(DrinkAdapter.HOME_MENU_CANTEEN_SCREEN);
        rcvMenu.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvMenu.setAdapter(adapter);
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        layout_choose_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogThreeButton dialog = new CustomDialogThreeButton(MenuCanteenScreen.this, R.layout.custom_dialog_chooss_image);
                dialog.txtHeaderDialog.setText("Choose other location");
                dialog.btnTakePhotos.setText("Canteen 1");
                dialog.btnChooseFromGallery.setText("Canteen 2");
                dialog.btnTakePhotos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtChooseYourAddress.setText("Canteen 1");
                        dialog.dismiss();
                    }
                });
                dialog.btnChooseFromGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtChooseYourAddress.setText("Canteen 2");
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

        imvPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuCanteenScreen.this, PurchaseCanteenScreen.class));

            }
        });

        imvLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuCanteenScreen.this, FavoriteDishesScreen.class));
            }
        });
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.checkNavStatusComeBack(MenuCanteenScreen.this);
            }
        });

        imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuCanteenScreen.this, CartCanteenScreen.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(MenuCanteenScreen.this);
    }

    private void linkView() {
        rcvMenu = findViewById(R.id.rcvMenu);
        imvBack = findViewById(R.id.imvBack);
        imvCart = findViewById(R.id.imvCart);
        layout_choose_address = findViewById(R.id.layout_choose_address);
        imvLove = findViewById(R.id.imvLove);
        imvPending = findViewById(R.id.imvPending);

        txtChooseYourAddress = findViewById(R.id.txtChooseYourAddress);
        txtTitleDelivery = findViewById(R.id.txtTitleDelivery);

    }
}