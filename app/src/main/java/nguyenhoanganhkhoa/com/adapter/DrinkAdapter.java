package nguyenhoanganhkhoa.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.models.Notification;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.quancafe.HomeSLSpaceScreen;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<Drink> mDrink;
    private List<Drink> mDrinkOld;

    public DrinkAdapter(Context context) {
        this.context = context;
    }

    public static final String DRINK_TITLE_BEST_SELLER = "Best Seller";

    public void setData(List<Drink> list){
        this.mDrink = list;
        this.mDrinkOld = list;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public DrinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_requested_order,parent,false);
        // Chỗ view này nó còn đại diện kiểu hiển thị nữa nha, có ăn parent ko thì nó trong đây nè

        // cái view hiện tại đại diện cho recyleview
        return new ViewHolder(view);
    }

    ReusedConstraint reusedConstraint = new ReusedConstraint(context);

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.ViewHolder holder, int position) {
        Drink drink = mDrink.get(position);
        if(drink ==null){
            return;
        }

        if(drink.isFavoriteDrink()){
            holder.imvFavoriteDrink.setImageResource(R.drawable.ic_love_active_yellow);
        }
        else{
            holder.imvFavoriteDrink.setImageResource(R.drawable.ic_love_yellow);
        }
        holder.imvThumbDrink.setImageResource(drink.getThumbDrink());
        holder.txtNameDrink.setText(drink.getDrinkName());
        holder.txtTypeDrink.setText(drink.getDrinkType());

        double discount = drink.getDrinkDiscount();
        double prePrice = drink.getDrinkPrePrice();
        double aftPrice = prePrice - prePrice*discount;
        if(discount == 0){
            holder.layout_preprice.setVisibility(View.GONE);
            holder.txtAftpriceDrink.setText(reusedConstraint.formatCurrency(aftPrice));
        }
        else{
            holder.layout_preprice.setVisibility(View.VISIBLE);
            holder.txtAftpriceDrink.setText(reusedConstraint.formatCurrency(aftPrice));
            holder.txtPrepriceDrink.setText(reusedConstraint.formatCurrency(prePrice));
        }

        changeColorTitle(holder,drink,discount);

        holder.imvFavoriteDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drink.isFavoriteDrink()){
                    holder.imvFavoriteDrink.setImageResource(R.drawable.ic_love_yellow);
                    drink.setFavoriteDrink(false);
                }
                else{
                    holder.imvFavoriteDrink.setImageResource(R.drawable.ic_love_active_yellow);
                    drink.setFavoriteDrink(true);
                }
            }
        });


    }

    private void changeColorTitle(DrinkAdapter.ViewHolder holder, Drink drink, double discount){
        if(drink.getDrinkTitle().equals(DRINK_TITLE_BEST_SELLER)){
            holder.txtTitleDrink.setTextColor(context.getColor(R.color.red));
        }
        if(drink.getDrinkTitle().isEmpty()){
            if(drink.getDrinkDiscount()!=0){
                holder.txtTitleDrink.setText(reusedConstraint.formatCurrency(discount*100)  + "% off");
                holder.txtTitleDrink.setTextColor(context.getColor(R.color.green));
            }
            else{
                holder.txtTitleDrink.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public int getItemCount() {
        if(mDrink !=null)
            return mDrink.size();
        else
            return 0;
    }


    MyCallBack callBack;
    public interface MyCallBack {
        void hideFilter();
        void showFilter();
        void showListAgain();
    }
    public void setCallBack(MyCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String textSearch = charSequence.toString();
                if(textSearch.isEmpty()){
                    mDrink = mDrinkOld;
                    if(callBack!=null){
                        callBack.showFilter();
                        callBack.showListAgain();
                    }
                    Log.d("TAG", "performFiltering: " + mDrink);
                }
                else{
                    List<Drink> list = new ArrayList<>();
                    for(Drink drink: mDrinkOld){
                        if(drink.getDrinkName().toLowerCase()
                                .contains(textSearch.toLowerCase().trim())){
                            list.add(drink);
                        }
                    }
                    if(callBack!=null){
                        callBack.hideFilter();
                    }
                    mDrink = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDrink;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDrink = (List<Drink>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleDrink, txtNameDrink, txtTypeDrink, txtPrepriceDrink, txtAftpriceDrink ;
        ImageView imvThumbDrink, imvFavoriteDrink;
        ConstraintLayout layout_preprice, layout_aftprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleDrink =itemView.findViewById(R.id.txtTitleDrink);
            txtNameDrink =itemView.findViewById(R.id.txtNameDrink);
            txtTypeDrink =itemView.findViewById(R.id.txtTypeDrink);
            imvThumbDrink =itemView.findViewById(R.id.imvThumbDrink);
            imvFavoriteDrink =itemView.findViewById(R.id.imvFavoriteDrink);
            layout_preprice =itemView.findViewById(R.id.layout_preprice);
            layout_aftprice =itemView.findViewById(R.id.layout_aftprice);
            txtPrepriceDrink =itemView.findViewById(R.id.txtPrepriceDrink);
            txtAftpriceDrink =itemView.findViewById(R.id.txtAftpriceDrink);
        }
    }
}
