package nguyenhoanganhkhoa.com.myapplication.home.SLSpace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import nguyenhoanganhkhoa.com.adapter.CommentsAdapter;
import nguyenhoanganhkhoa.com.adapter.DrinkAdapter;
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class ProductDetailScreen extends AppCompatActivity {

    ImageView imvThumbDrink;
    TextView txtTitleDrink, txtNameDrink, txtTypeDrink, txtPrepriceDrink, txtAftpriceDrink,txtInfoProduct, txtRating, txtQuantityReviews, txtRating2;
    Button btnAddToCart;
    ConstraintLayout layout_preprice;
    LinearLayout layout_drink_detail;
    TextView txtSeeAll;
    ImageView imvLove;
    RecyclerView rcvComment;
    RatingBar ratingBar, ratingBar2;

    ReusedConstraint reusedConstraint = new ReusedConstraint();
    CommentsAdapter adapter = new CommentsAdapter(this);


    boolean isExpanded = false;

    Drink drink;
    String title;

    private void linkView() {
        imvThumbDrink = findViewById(R.id.imvThumbDrink);
        txtTitleDrink = findViewById(R.id.txtTitleDrink);
        txtNameDrink = findViewById(R.id.txtNameDrink);
        txtTypeDrink = findViewById(R.id.txtTypeDrink);
        txtPrepriceDrink = findViewById(R.id.txtPrepriceDrink);
        txtAftpriceDrink = findViewById(R.id.txtAftpriceDrink);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        layout_preprice = findViewById(R.id.layout_preprice);
        layout_drink_detail = findViewById(R.id.layout_drink_detail);
        txtInfoProduct = findViewById(R.id.txtInfoProduct);
        txtSeeAll = findViewById(R.id.txtSeeAll);
        imvLove = findViewById(R.id.imvLove);
        rcvComment = findViewById(R.id.rcvComment);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar2 = findViewById(R.id.ratingBar2);
        txtRating = findViewById(R.id.txtRating);
        txtRating2 = findViewById(R.id.txtRating2);
        txtQuantityReviews = findViewById(R.id.txtQuantityReviews);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_screen);

        linkView();
        getData();
        setValue();
        initAdapter();
        addEvents();



    }

    private void initAdapter() {
        adapter.setData(drink.getCommentsList());
        rcvComment.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvComment.setAdapter(adapter);
    }

    private void setFavorite(){
        if(drink.isFavoriteDrink()){
            imvLove.setImageResource(R.drawable.ic_love_active_yellow);
        }
        else{
            imvLove.setImageResource(R.drawable.ic_love_yellow);
        }
    }

    private void setExpanded(){
        if(isExpanded){
            txtInfoProduct.setMaxLines(Integer.MAX_VALUE);
        }
        else{
            txtInfoProduct.setMaxLines(4);
        }
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        reusedConstraint.setActionComeBack(this);
        imvLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drink.setFavoriteDrink(!drink.isFavoriteDrink());
                setFavorite();
            }
        });
        txtSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isExpanded){
                    isExpanded = false;
                    txtSeeAll.setText(R.string.see_all);
                }
                else{
                    isExpanded = true;
                    txtSeeAll.setText(R.string.see_less);
                }
                setExpanded();

            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailScreen.this, AddToCartScreen.class);
                pushData(intent,drink);
                startActivity(intent);
            }
        });
    }
    private void pushData(Intent intent, Drink drink) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppUtil.SELECTED_ITEM_TRANS,drink);
        intent.putExtra(AppUtil.MY_BUNDLE_TRANS, bundle);
    }



    private void setValue() {
        txtQuantityReviews.setText(String.valueOf(drink.commentQuantity()));

        ratingBar.setRating(drink.getAverageRate());
        ratingBar2.setRating(drink.getAverageRate());

        txtRating.setText(drink.getAverageRate_toString() + "  |");
        txtRating2.setText(drink.getAverageRate_toString()+"/5");

        setFavorite();
        txtInfoProduct.setText(drink.getDrinkDes());
        imvThumbDrink.setImageResource(drink.getThumbDrink());
        double discount = drink.getDrinkDiscount();
        double prePrice = drink.getDrinkPrePrice();
        double aftPrice = prePrice - prePrice*discount;
        if(discount == 0){
            layout_preprice.setVisibility(View.GONE);
            txtAftpriceDrink.setText(reusedConstraint.formatCurrency(aftPrice));
        }
        else{
            layout_preprice.setVisibility(View.VISIBLE);
            txtAftpriceDrink.setText(reusedConstraint.formatCurrency(aftPrice));
            txtPrepriceDrink.setText(reusedConstraint.formatCurrency(prePrice));
        }

        txtNameDrink.setText(drink.getDrinkName());
        txtTypeDrink.setText(drink.getDrinkType());

        txtTitleDrink.setText(title);
        txtTitleDrink.setVisibility(View.VISIBLE);

        if(title.contains("off")){
            txtTitleDrink.setTextColor(getColor(R.color.green));
        }
        if(title.equals(DrinkAdapter.DRINK_TITLE_BEST_SELLER)){
            txtTitleDrink.setTextColor(getColor(R.color.red));
        }
        if(title.isEmpty()){
            txtTitleDrink.setVisibility(View.GONE);
        }
    }

    private void getData() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra(AppUtil.MY_BUNDLE_TRANS);
            if(bundle!=null){
                drink = (Drink) bundle.getSerializable(AppUtil.SELECTED_ITEM_TRANS);
                title = bundle.getString("TitleDrink");
            }
        }
        catch (Exception e){
            Log.d("Error", "getData: " + e);
        }
    }
}