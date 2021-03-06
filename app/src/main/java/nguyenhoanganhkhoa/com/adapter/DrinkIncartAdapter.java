package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;
import java.util.Objects;

import nguyenhoanganhkhoa.com.models.DrinkInCart;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.AddToCartScreen;
import nguyenhoanganhkhoa.com.myapplication.home.canteen.AddToCartCanteenScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class DrinkIncartAdapter extends RecyclerView.Adapter<DrinkIncartAdapter.ViewHolder> {

    private Context context;
    private List<DrinkInCart> mDrinks;
    private int numScreen = IN_CART_SCREEN;

    public static final int IN_CART_SCREEN = 0;
    public static final int ORDER_SCREEN = 1;
    public static final int PURCHASE_SCREEN = 2;
    public static final int ORDER_DETAIL_SCREEN = 3;

    public static final int IN_CART_CANTEEN_SCREEN = 4;
    public static final int ORDER_CANTEEN_SCREEN = 5;
    public static final int PURCHASE_CANTEEN_SCREEN = 6;
    public static final int ORDER_DETAIL_CANTEEN_SCREEN = 7;




    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    ReusedConstraint reusedConstraint = new ReusedConstraint(context);

    public DrinkIncartAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<DrinkInCart> list){
        this.mDrinks = list;
        notifyDataSetChanged();
    }

    public void setNumScreen(int numScreen) {
        this.numScreen = numScreen;
    }

    public List<DrinkInCart> getmDrinks() {
        return mDrinks;
    }

    @NonNull
    @Override
    public DrinkIncartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;
        if(numScreen== ORDER_SCREEN){
            view = inflater.inflate(R.layout.item_drink_order,parent,false);
        }
        if(numScreen == IN_CART_SCREEN) {
             view = inflater.inflate(R.layout.item_drink_in_cart,parent,false);
        }
        if(numScreen == PURCHASE_SCREEN){
            view = inflater.inflate(R.layout.item_drink_purchase,parent,false);
        }
        if(numScreen == ORDER_DETAIL_SCREEN){
            view = inflater.inflate(R.layout.item_drink_purchase_detail,parent,false);
        }

        if(numScreen == IN_CART_CANTEEN_SCREEN){
            view = inflater.inflate(R.layout.item_dish_in_cart,parent,false);
        }
        if(numScreen == ORDER_CANTEEN_SCREEN){
            view = inflater.inflate(R.layout.item_dish_order,parent,false);
        }
        if(numScreen == PURCHASE_CANTEEN_SCREEN){
            view = inflater.inflate(R.layout.item_dish_purchase,parent,false);
        }
        if(numScreen == ORDER_DETAIL_CANTEEN_SCREEN){
            view = inflater.inflate(R.layout.item_dish_purchase_detail,parent,false);
        }
        return new ViewHolder(Objects.requireNonNull(view));
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkIncartAdapter.ViewHolder holder, int position) {
        DrinkInCart drink = mDrinks.get(position);
        if(drink==null)
        {
            return;
        }



        if(numScreen != IN_CART_CANTEEN_SCREEN && numScreen != ORDER_CANTEEN_SCREEN && numScreen != PURCHASE_CANTEEN_SCREEN
        && numScreen!= ORDER_DETAIL_CANTEEN_SCREEN){
            holder.txtIceLevel.setText(drink.toIcePercentString());
            holder.txtSugarLevel.setText(drink.toSugarPercentString());
            holder.txtSize.setText(drink.getSize());
        }
        holder.txtPriceDrink.setText(reusedConstraint.formatCurrency(drink.getTotalPrice()));
        holder.imvThumbDrink.setImageResource(drink.getThumbDrink());
        holder.txtQuantity.setText(String.valueOf(drink.getQuantityDrink()));
        holder.txtNameDrink.setText(drink.getDrinkName());

        if(numScreen==IN_CART_CANTEEN_SCREEN | numScreen ==ORDER_CANTEEN_SCREEN | numScreen == PURCHASE_CANTEEN_SCREEN| numScreen == ORDER_DETAIL_CANTEEN_SCREEN){
            holder.txtNote.setText(String.valueOf(drink.getNote()));
        }



        if(numScreen == ORDER_SCREEN | numScreen == ORDER_CANTEEN_SCREEN | numScreen == IN_CART_SCREEN | numScreen == IN_CART_CANTEEN_SCREEN ){
            viewBinderHelper.bind(holder.swipeRevealLayout,drink.getDrinkName());
            viewBinderHelper.setOpenOnlyOne(true);

            holder.imbPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drink.setQuantityDrink(drink.getQuantityDrink() + 1);
                    holder.txtQuantity.setText(String.valueOf(drink.getQuantityDrink()));
                    holder.txtPriceDrink.setText(reusedConstraint.formatCurrency(drink.getTotalPrice()));

                    callBack.setTextPriceTotal(mDrinks);

                }
            });

            holder.imbMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(drink.getQuantityDrink()>0){
                        drink.setQuantityDrink(drink.getQuantityDrink() - 1);
                        holder.txtQuantity.setText(String.valueOf(drink.getQuantityDrink()));
                        holder.txtPriceDrink.setText(reusedConstraint.formatCurrency(drink.getTotalPrice()));

                        callBack.setTextPriceTotal(mDrinks);

                        // C?? th??? d??ng notifyDataSetChanged
                    }
                    if(drink.getQuantityDrink() == 0){
                        deleteItem(holder);
                    }
                }
            });

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(holder);
                }
            });

            holder.btnEditDrink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pushData(drink);
                }
            });

        }




        if(numScreen==IN_CART_SCREEN|| numScreen == IN_CART_CANTEEN_SCREEN){
            setSelectedStatus(holder,drink);
            holder.imvSelectedItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(drink.isSelected()){
                        drink.setSelected(false);
                    }
                    else{
                        drink.setSelected(true);
                    }
                    setSelectedStatus(holder,drink);
                }
            });
        }


    }



    private void pushData(DrinkInCart drink) {
        Intent intent;
        if(numScreen == IN_CART_CANTEEN_SCREEN | numScreen == ORDER_CANTEEN_SCREEN){
            intent = new Intent(context, AddToCartCanteenScreen.class);
        }
        else{
            intent = new Intent(context, AddToCartScreen.class);
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppUtil.SELECTED_ITEM_TRANS,drink);
        intent.putExtra(AppUtil.MY_BUNDLE_TRANS, bundle);

        context.startActivity(intent);
    }

    private long mLastClickTime = 0;

    private void deleteItem(DrinkIncartAdapter.ViewHolder holder){
        if (SystemClock.elapsedRealtime() - mLastClickTime > 500){
            mLastClickTime = SystemClock.elapsedRealtime();
            String itemName = mDrinks.get(holder.getAdapterPosition()).getDrinkName();
            mDrinks.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            callBack.showSnackBar(itemName);
            callBack.getListSizeRemain(mDrinks.size());
            callBack.setTextPriceTotal(mDrinks);
        }
    }


    private void setSelectedStatus(DrinkIncartAdapter.ViewHolder holder, DrinkInCart drink){
        if (drink.isSelected()){
            holder.imvSelectedItem.setImageResource(R.drawable.ic_rad_yellow_checked);
        }
        else{
            holder.imvSelectedItem.setImageResource(R.drawable.ic_rad_yellow_unchecked);
        }
    }


    DrinkIncartAdapter.MyCallBack callBack;
    public interface MyCallBack {
        void setTextPriceTotal(List<DrinkInCart> list);
        void showSnackBar(String itemName);
        void getListSizeRemain(int size);
    }
    public void setCallBack(DrinkIncartAdapter.MyCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getItemCount() {
        if(mDrinks !=null)
            return mDrinks.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvSelectedItem;
        private ImageView imvThumbDrink, imbMinus, imbPlus;
        private TextView txtQuantity, txtNameDrink, txtIceLevel, txtSugarLevel, txtSize, txtPriceDrink, txtNote;
        private Button btnEditDrink, btnDelete;
        private SwipeRevealLayout swipeRevealLayout;
        private View viewSeparate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvSelectedItem = itemView.findViewById(R.id.imvSelectedItem);
            imvThumbDrink = itemView.findViewById(R.id.imvThumbDrink);
            imbMinus = itemView.findViewById(R.id.imbMinus);
            imbPlus = itemView.findViewById(R.id.imbPlus);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtNameDrink = itemView.findViewById(R.id.txtNameDrink);
            txtIceLevel = itemView.findViewById(R.id.txtIceLevel);
            txtSugarLevel = itemView.findViewById(R.id.txtSugarLevel);
            txtSize = itemView.findViewById(R.id.txtSize);
            txtPriceDrink = itemView.findViewById(R.id.txtPriceDrink);
            btnEditDrink = itemView.findViewById(R.id.btnEditDrink);
            swipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            txtNote = itemView.findViewById(R.id.txtNote);
            viewSeparate = itemView.findViewById(R.id.viewSeparate);
        }
    }
}
