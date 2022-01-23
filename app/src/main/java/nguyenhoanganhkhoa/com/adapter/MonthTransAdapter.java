package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.Date;
import nguyenhoanganhkhoa.com.models.Month;
import nguyenhoanganhkhoa.com.myapplication.R;

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

    }

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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMonthAll = itemView.findViewById(R.id.txtMonthAll);
            rcvTransactionList = itemView.findViewById(R.id.rcvTransactionList);
        }
    }
}
