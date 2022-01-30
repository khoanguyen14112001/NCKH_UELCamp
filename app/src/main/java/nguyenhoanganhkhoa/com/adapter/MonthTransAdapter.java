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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import nguyenhoanganhkhoa.com.models.Date;
import nguyenhoanganhkhoa.com.models.Month;
import nguyenhoanganhkhoa.com.models.Transaction;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class MonthTransAdapter extends RecyclerView.Adapter<MonthTransAdapter.ViewHolder> {

    private Context context;
    private List<Month> mMonths;

    public MonthTransAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Month> list){
        this.mMonths = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MonthTransAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_transaction_month,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthTransAdapter.ViewHolder holder, int position) {
        Month month = mMonths.get(position);
        if(month ==null)
        {
            return;
        }

        holder.txtMonthAll.setText(month.getMonthTrans());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        holder.rcvTransactionList.setLayoutManager(linearLayoutManager);

        TransAllAdapter transAllAdapter = new TransAllAdapter(context);
        transAllAdapter.setData(month.getTransactions());

        holder.rcvTransactionList.setAdapter(transAllAdapter);

        if(month.getTransactions().isEmpty()){
            holder.lnItemMonthTrans.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = holder.lnItemMonthTrans.getLayoutParams();
            params.height = 0;
            params.width = 0;
            holder.lnItemMonthTrans.setLayoutParams(params);
        }

        holder.txtIncome.setText(reusedConstraint.formatCurrency(month.getMonthIncome()));
        holder.txtExpense.setText(reusedConstraint.formatCurrency(month.getMonthExpense()));
    }

    ReusedConstraint reusedConstraint = new ReusedConstraint(context);





    @Override
    public int getItemCount() {
        if(mMonths !=null)
            return mMonths.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMonthAll;
        private RecyclerView rcvTransactionList;
        private LinearLayout lnItemMonthTrans;
        private TextView txtIncome, txtExpense;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMonthAll = itemView.findViewById(R.id.txtMonthAll);

            txtIncome = itemView.findViewById(R.id.txtIncome);
            txtExpense = itemView.findViewById(R.id.txtExpense);


            rcvTransactionList = itemView.findViewById(R.id.rcvTransactionList);
            lnItemMonthTrans = itemView.findViewById(R.id.lnItemMonthTrans);
        }
    }
}
