package nguyenhoanganhkhoa.com.myapplication.home.canteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DrinkAdapter;
import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogTwoButtonNew;
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class FavoriteDishesScreen extends AppCompatActivity {

    ConstraintLayout layout_delete_favourite;
    RecyclerView rcvFavoriteDrink;
    TextView txtNotifyNoProduct;
    ImageButton imbDeleteAll;

    ReusedConstraint reusedConstraint = new ReusedConstraint();
    DrinkAdapter adapter = new DrinkAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_drinks_screen);

        linkView();
        initAdapter(getListDrinkFavorite());
        setCallBackAdapter();
        addEvents();
    }

    private List<Drink> getListDrinkFavorite(){
        List<Drink> listAll = HomeCanteenScreen.getListDishes();
        List<Drink> list = new ArrayList<>();
        int i;
        for(i = 0; i<listAll.size();i++){
            if(listAll.get(i).isFavoriteDrink()){
                list.add(listAll.get(i));
            }
        }
        return list;
    }

    private void initAdapter(List<Drink> list) {
        adapter.setData(list);
        adapter.setScreen(DrinkAdapter.FAVORITE_DISH_SCREEN);
        rcvFavoriteDrink.setAdapter(adapter);
        rcvFavoriteDrink.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    private void setActionDelete(){
        if(adapter.getItemCount() !=0){
            CustomDialogTwoButtonNew dialog = new CustomDialogTwoButtonNew(FavoriteDishesScreen.this);
            dialog.txtHeaderDialog.setText(getResources().getString(R.string.delete_all));
            dialog.txtContentDialog.setText(R.string.delete_all_content);
            dialog.btnOK.setText(getResources().getString(R.string.yes));
            dialog.btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initAdapter(null);
                    imbDeleteAll.setImageResource(R.drawable.ic_love_yellow);
                    setDisplay();
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
        else {
            Toast.makeText(FavoriteDishesScreen.this, "There is no product to delete!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        reusedConstraint.setActionComeBack(this);
        layout_delete_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActionDelete();
            }
        });
        imbDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActionDelete();
            }
        });
    }

    private void setDisplay(){
        rcvFavoriteDrink.setVisibility(View.GONE);
        txtNotifyNoProduct.setVisibility(View.VISIBLE);
    }



    private void linkView() {
        rcvFavoriteDrink = findViewById(R.id.rcvFavoriteDrink);
        layout_delete_favourite = findViewById(R.id.layout_delete_favourite);
        txtNotifyNoProduct = findViewById(R.id.txtNotifyNoProduct);
        imbDeleteAll = findViewById(R.id.imbDeleteAll);

    }

    private void setCallBackAdapter(){
        adapter.setCallBackFavorite(new DrinkAdapter.MyCallBackForFavorite() {
            @Override
            public void showNotifyNoItem() {
                setDisplay();
            }

            @Override
            public void changeLoveIconStatus() {
                if(adapter.getItemCount()==0){
                    imbDeleteAll.setImageResource(R.drawable.ic_love_yellow);
                }
                else{
                    imbDeleteAll.setImageResource(R.drawable.ic_love_active_yellow);
                }
            }
        });
    }
}