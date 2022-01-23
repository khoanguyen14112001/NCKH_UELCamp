package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.DetailProTrans;
import nguyenhoanganhkhoa.com.models.Transaction;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.DetailTransaction;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

public class TransAllAdapter extends RecyclerView.Adapter<TransAllAdapter.ViewHolder> {
    private Context context;
    private List<Transaction> mTransaction;

    public TransAllAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Transaction> list){
        this.mTransaction = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TransAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_transaction_all,parent,false);
        // Chỗ view này nó còn đại diện kiểu hiển thị nữa nha, có ăn parent ko thì nó trong đây nè

        // cái view hiện tại đại diện cho recyleview
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransAllAdapter.ViewHolder holder, int position) {
        Transaction transaction = mTransaction.get(position);
        if(transaction ==null){
            return;
        }

        holder.imvStatusTrans.setImageResource(transaction.getImgStatusTrans());
        holder.txtDateStatusTrans.setText(transaction.getDateTrans());
        holder.txtStatusTrans.setText(transaction.getStatusTrans());
        holder.txtMoneyTrans.setText(transaction.getMoneyTrans());
        holder.imvSuccessTrans.setImageResource(transaction.getImgSuccessTrans());

        holder.layout_item_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailTransaction.class);
                pushData(intent, transaction);
                context.startActivity(intent);

            }


        });

    }

    private void pushData(Intent intent, Transaction transaction) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppUtil.SELECTED_ITEM,transaction);
        intent.putExtra(AppUtil.MY_BUNDLE, bundle);
    }

    @Override
    public int getItemCount() {
        if(mTransaction !=null)
            return mTransaction.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDateStatusTrans, txtStatusTrans,txtMoneyTrans;
        ImageView imvStatusTrans,imvSuccessTrans;

        ConstraintLayout layout_item_trans;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStatusTrans =itemView.findViewById(R.id.txtStatusTrans);
            txtDateStatusTrans =itemView.findViewById(R.id.txtDateStatusTrans);
            imvStatusTrans =itemView.findViewById(R.id.imvStatusTrans);
            txtMoneyTrans =itemView.findViewById(R.id.txtMoneyTrans);
            imvSuccessTrans =itemView.findViewById(R.id.imvSuccessTrans);

            layout_item_trans =itemView.findViewById(R.id.layout_item_trans);

        }
    }
}
