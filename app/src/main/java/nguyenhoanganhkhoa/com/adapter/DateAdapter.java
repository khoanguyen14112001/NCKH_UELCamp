package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.Date;
import nguyenhoanganhkhoa.com.models.History;
import nguyenhoanganhkhoa.com.myapplication.R;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

    private Context context;
    private List<Date> mDates;
    private int layout;


    public DateAdapter(Context context, int layout) {
        this.context = context;
        this.layout = layout;


    }

    public void setData(List<Date> list){
        this.mDates = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateAdapter.ViewHolder holder, int position) {
        Date date = mDates.get(position);
        if(date==null)
        {
            return;
        }

        holder.txtDayAll.setText(date.getDateParking());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        holder.rcvHistory.setLayoutManager(linearLayoutManager);
        HistoryAdapter historyAdapter = new HistoryAdapter(context);
        historyAdapter.setData(date.getHistories());
        holder.rcvHistory.setAdapter(historyAdapter);

    }

    @Override
    public int getItemCount() {
        if(mDates!=null)
            return mDates.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDayAll;
        private RecyclerView rcvHistory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDayAll = itemView.findViewById(R.id.txtDayAll);
            rcvHistory = itemView.findViewById(R.id.rcvHistory);
        }
    }
}
