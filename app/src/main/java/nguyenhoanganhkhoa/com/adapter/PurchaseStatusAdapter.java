package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.Date;
import nguyenhoanganhkhoa.com.models.PurchaseStatus;
import nguyenhoanganhkhoa.com.myapplication.R;

public class PurchaseStatusAdapter extends RecyclerView.Adapter<PurchaseStatusAdapter.ViewHolder> {

    private Context context;
    private List<PurchaseStatus> mPurchase;

    private int lastChosenPosition = 0;
    private boolean isNewRadioChecked = false;


    public PurchaseStatusAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<PurchaseStatus> list){
        this.mPurchase = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PurchaseStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_purchase_status,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseStatusAdapter.ViewHolder holder, int position) {
        PurchaseStatus purchaseStatus = mPurchase.get(position);
        if(purchaseStatus==null)
        {
            return;
        }
        holder.txtStatusPurchase.setText(purchaseStatus.getNameStatus());
        holder.txtAmountPurchase.setText(purchaseStatus.getQuantityPurchase_toString());

        if(purchaseStatus.getQuantity() == 0){
            holder.txtAmountPurchase.setVisibility(View.GONE);
        }
        else{
            holder.txtAmountPurchase.setVisibility(View.VISIBLE);
        }

        if(isNewRadioChecked){
            setChosenPurchaseStatus(holder,purchaseStatus);
        }
        else{
            if(holder.getAdapterPosition() == 0){
                purchaseStatus.setChosen(true);
                setChosenPurchaseStatus(holder,purchaseStatus);
            }

        }

        if(holder.getAdapterPosition()==0){
            holder.layout_purchase_status.setPadding(50,0,10,0);
        }
        if(holder.getAdapterPosition() == mPurchase.size()){
            holder.layout_purchase_status.setPadding(10,0,50,0);
        }


        holder.layout_purchase_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isNewRadioChecked = true;
                mPurchase.get(lastChosenPosition).setChosen(false);
                purchaseStatus.setChosen(true);
                lastChosenPosition = holder.getAdapterPosition();

                callBack.changeDataInAdapter(purchaseStatus.getNameStatus());

                notifyDataSetChanged();


            }
        });

    }

    PurchaseStatusAdapter.MyCallBack callBack;
    public interface MyCallBack {
        void changeDataInAdapter(String name);
    }
    public void setCallBack(PurchaseStatusAdapter.MyCallBack callBack) {
        this.callBack = callBack;
    }

    private void setChosenPurchaseStatus(ViewHolder holder, PurchaseStatus purchaseStatus){
        if(purchaseStatus.isChosen()){
            holder.viewUnder.setVisibility(View.VISIBLE);
            holder.txtStatusPurchase.setTextColor(context.getColor(R.color.primary_yellow));
        }
        else{
            holder.viewUnder.setVisibility(View.GONE);
            holder.txtStatusPurchase.setTextColor(context.getColor(R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        if(mPurchase !=null)
            return mPurchase.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStatusPurchase, txtAmountPurchase;
        View viewUnder;
        ConstraintLayout layout_purchase_status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStatusPurchase = itemView.findViewById(R.id.txtStatusPurchase);
            txtAmountPurchase = itemView.findViewById(R.id.txtAmountPurchase);
            viewUnder = itemView.findViewById(R.id.viewUnder);
            layout_purchase_status = itemView.findViewById(R.id.layout_purchase_status);
        }
    }
}
