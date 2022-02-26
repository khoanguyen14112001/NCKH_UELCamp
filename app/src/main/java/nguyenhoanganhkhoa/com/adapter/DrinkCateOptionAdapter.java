package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.DrinkOption;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class DrinkCateOptionAdapter extends RecyclerView.Adapter<DrinkCateOptionAdapter.ViewHolder> {
    private Context context;
    private List<DrinkOption> mOptions;

    public DrinkCateOptionAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<DrinkOption> list){
        this.mOptions = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DrinkCateOptionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_drink_cate,parent,false);
        // Chỗ view này nó còn đại diện kiểu hiển thị nữa nha, có ăn parent ko thì nó trong đây nè

        // cái view hiện tại đại diện cho recyleview
        return new ViewHolder(view);
    }

    ReusedConstraint reusedConstraint = new ReusedConstraint(context);
    @Override
    public void onBindViewHolder(@NonNull DrinkCateOptionAdapter.ViewHolder holder, int position) {
        DrinkOption option = mOptions.get(position);
        if(option ==null){
            return;
        }

        holder.txtDrinkLevelOption.setText(option.getOptionCate());
        if(option.isFree()){
            holder.txtDrinkPriceOption.setText("Free");
        }
        else{
            holder.txtDrinkPriceOption.setText("+" +  reusedConstraint.formatCurrency(option.getOptionAdditionPrice()) + " VND");
        }


        if(isNewRadioChecked){
            setChosenDrink(holder,option);
        }
        else{
            if(holder.getAdapterPosition() == 0){
                option.setChosen(true);
                setChosenDrink(holder,option);
            }

        }


        holder.layout_drink_cate_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isNewRadioChecked = true;
                mOptions.get(lastCheckedPosition).setChosen(false);
                option.setChosen(true);
                lastCheckedPosition = holder.getAdapterPosition();


                callBack.plusPrice(mOptions);

                notifyDataSetChanged();

            }
        });

    }
    DrinkCateOptionAdapter.MyCallBack callBack;
    public interface MyCallBack {
        void plusPrice(List<DrinkOption> listDrinkOption);
    }
    public void setCallBack(DrinkCateOptionAdapter.MyCallBack callBack) {
        this.callBack = callBack;
    }


    private int lastCheckedPosition = 0;
    private boolean isNewRadioChecked = false;
    private void setChosenDrink(DrinkCateOptionAdapter.ViewHolder holder, DrinkOption drinkOption){
        if(drinkOption.isChosen()){
            holder.imvChooseDrinkOptionStatus.setImageResource(R.drawable.ic_rad_yellow_checked);
        }
        else{
            holder.imvChooseDrinkOptionStatus.setImageResource(R.drawable.ic_rad_yellow_unchecked);
        }

    }


    @Override
    public int getItemCount() {
        if(mOptions !=null)
            return mOptions.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDrinkLevelOption, txtDrinkPriceOption ;
        ImageView imvChooseDrinkOptionStatus;
        ConstraintLayout layout_drink_cate_option;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDrinkLevelOption = itemView.findViewById(R.id.txtDrinkLevelOption);
            txtDrinkPriceOption = itemView.findViewById(R.id.txtDrinkPriceOption);
            imvChooseDrinkOptionStatus = itemView.findViewById(R.id.imvChooseDrinkOptionStatus);
            layout_drink_cate_option = itemView.findViewById(R.id.layout_drink_cate_option);


        }
    }
}
