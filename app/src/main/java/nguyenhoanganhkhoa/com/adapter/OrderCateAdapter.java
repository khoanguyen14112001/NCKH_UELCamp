package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.History;
import nguyenhoanganhkhoa.com.models.OrderCategories;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.helpcenter.ContactSupportScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

public class OrderCateAdapter extends RecyclerView.Adapter<OrderCateAdapter.ViewHolder> {
    private Context context;
    private List<OrderCategories> mOrder;

    public OrderCateAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<OrderCategories> list){
        this.mOrder = list;
        notifyDataSetChanged();
    }

    public static final String ORDER_COFFEE = "Coffee";
    public static final String ORDER_TEA = "Tea";
    public static final String ORDER_SODA = "Soda";
    public static final String ORDER_JUICE = "Juice";
    public static final String ORDER_YOGURT = "Yogurt";
    public static final String ORDER_MACHIATO = "Machiato";
    public static final String ORDER_FRAPPUCHINO = "Frappuchino";
    public static final String ORDER_DISCOUNT = "Discount";


    @NonNull
    @Override
    public OrderCateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_order_cate,parent,false);
        // Chỗ view này nó còn đại diện kiểu hiển thị nữa nha, có ăn parent ko thì nó trong đây nè

        // cái view hiện tại đại diện cho recyleview
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCateAdapter.ViewHolder holder, int position) {
        OrderCategories orderCategories = mOrder.get(position);
        if(orderCategories ==null){
            return;
        }
        String type = orderCategories.getOrderCateType();
        holder.txtNameOrder.setText(type);
        holder.imvThumbOrder.setImageResource(orderCategories.getOrderCateThumb());
        holder.lnOrderType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type){
                    case ORDER_COFFEE:
                        //Do something

                        break;
                    case ORDER_TEA:
                        //Do something
                        break;
                    case ORDER_SODA:
                        //Do something

                        break;
                    case ORDER_JUICE:
                        //Do something

                        break;
                    case ORDER_YOGURT:
                        //Do something

                        break;
                    case ORDER_MACHIATO:
                        //Do something

                        break;
                    case ORDER_FRAPPUCHINO:
                        //Do something

                        break;

                    case ORDER_DISCOUNT:
                        //Do something

                        break;
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        if(mOrder !=null)
            return mOrder.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameOrder;
        ImageView imvThumbOrder;
        LinearLayout lnOrderType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameOrder =itemView.findViewById(R.id.txtNameOrder);
            imvThumbOrder =itemView.findViewById(R.id.imvThumbOrder);
            lnOrderType =itemView.findViewById(R.id.lnOrderType);
        }
    }
}
