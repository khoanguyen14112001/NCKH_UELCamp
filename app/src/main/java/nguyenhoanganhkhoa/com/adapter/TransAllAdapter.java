package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import nguyenhoanganhkhoa.com.models.Transaction;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.transaction.DetailTransaction;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

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

    public static final String TRANSACTION_TOPUP = "topup";
    public static final String TRANSACTION_TRANSFER = "transfer";
    public static final String TRANSACTION_CANTEEN = "canteen";
    public static final String TRANSACTION_PARKING = "parking";
    public static final String TRANSACTION_QUANCAFE = "SLSpace";
    public static final String TRANSACTION_THUQUAN = "Stationery";


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

        setItemTransaction(holder, transaction);

        holder.layout_item_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailTransaction.class);
                pushData(intent, transaction);
                context.startActivity(intent);

            }


        });

    }

    private void setItemTransaction(TransAllAdapter.ViewHolder holder, Transaction transaction) {
        switch (transaction.getTypeTrans()){
            case TRANSACTION_TOPUP:
                setNameAndImage(holder,R.drawable.ic_topup,"Top up");
                break;
            case TRANSACTION_TRANSFER:
                setNameAndImage(holder,R.drawable.ic_transfer,"Transfer");
                break;
            case TRANSACTION_CANTEEN:
                setNameAndImage(holder,R.drawable.ic_canteen,"Canteen");
                break;
            case TRANSACTION_PARKING:
                setNameAndImage(holder,R.drawable.ic_bike,"Parking");
                break;
            case TRANSACTION_THUQUAN:
                setNameAndImage(holder,R.drawable.ic_thuquan,"Stationery");
                break;
            case TRANSACTION_QUANCAFE:
                setNameAndImage(holder,R.drawable.ic_quancafe,"SLSpace");
                break;
        }
        holder.txtDateTrans.setText(transaction.getDateTrans());
        if(transaction.isSuccess()){
            holder.imvStatusTrans.setImageResource(R.drawable.ic_tickbutton);
        }
        else{
            holder.imvStatusTrans.setImageResource(R.drawable.ic_warning_red);
        }

        if(transaction.isIncome()){
            holder.txtStatusIncomeOut.setText("+");
        }
        else{
            holder.txtStatusIncomeOut.setText("-");
        }

        holder.txtAmountTrans.setText(reusedConstraint.formatCurrency(transaction.getAmountTrans()));

    }

    ReusedConstraint reusedConstraint = new ReusedConstraint(context);





    private void setNameAndImage(TransAllAdapter.ViewHolder holder, int thumb, String name){
        holder.imvTypeTrans.setImageResource(thumb);
        holder.txtTypeTrans.setText(name);
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
        TextView txtDateTrans, txtStatusTrans,txtAmountTrans, txtTypeTrans, txtStatusIncomeOut;
        ImageView imvStatusTrans,imvTypeTrans;

        ConstraintLayout layout_item_trans;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStatusTrans =itemView.findViewById(R.id.txtStatusTrans);

            txtDateTrans =itemView.findViewById(R.id.txtDateTrans);
            txtTypeTrans =itemView.findViewById(R.id.txtTypeTrans);
            imvTypeTrans =itemView.findViewById(R.id.imvTypeTrans);
            txtStatusIncomeOut =itemView.findViewById(R.id.txtStatusIncomeOut);

            txtAmountTrans =itemView.findViewById(R.id.txtAmountTrans);
            imvStatusTrans =itemView.findViewById(R.id.imvStatusTrans);

            layout_item_trans =itemView.findViewById(R.id.layout_item_trans);

        }
    }
}
