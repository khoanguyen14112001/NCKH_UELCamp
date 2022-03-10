package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.Date;
import nguyenhoanganhkhoa.com.models.DrinkCateOption;
import nguyenhoanganhkhoa.com.models.DrinkOption;
import nguyenhoanganhkhoa.com.myapplication.R;

public class DrinkCateAdapter extends RecyclerView.Adapter<DrinkCateAdapter.ViewHolder> {

    private Context context;
    private List<DrinkCateOption> mCates;


    public DrinkCateAdapter(Context context) {
        this.context = context;


    }

    public void setData(List<DrinkCateOption> list){
        this.mCates = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DrinkCateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_drink_big_cate,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkCateAdapter.ViewHolder holder, int position) {
        DrinkCateOption cate = mCates.get(position);
        if(cate==null)
        {
            return;
        }
        holder.txtDrinkOptionBigCate.setText(cate.getOptionBigCate());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        holder.rcvOptionDrink.setLayoutManager(linearLayoutManager);
        DrinkCateOptionAdapter adapter = new DrinkCateOptionAdapter(context);
        adapter.setData(cate.getListOption());
        holder.rcvOptionDrink.setAdapter(adapter);


        adapter.setCallBack(new DrinkCateOptionAdapter.MyCallBack() {
            @Override
            public void plusPrice(List<DrinkOption> listDrinkOption) {
                adapter.setData(listDrinkOption);
                callBack.passDataToCart(mCates);
            }
        });



    }

    DrinkCateAdapter.MyCallBack callBack;
    public interface MyCallBack {
        void passDataToCart(List<DrinkCateOption> mCates);
    }
    public void setCallBack(DrinkCateAdapter.MyCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getItemCount() {
        if(mCates !=null)
            return mCates.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDrinkOptionBigCate;
        private RecyclerView rcvOptionDrink;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDrinkOptionBigCate = itemView.findViewById(R.id.txtDrinkOptionBigCate);
            rcvOptionDrink = itemView.findViewById(R.id.rcvOptionDrink);
        }
    }
}
