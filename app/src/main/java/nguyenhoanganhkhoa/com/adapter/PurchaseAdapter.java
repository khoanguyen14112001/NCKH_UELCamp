package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import nguyenhoanganhkhoa.com.models.PurchaseItem;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.EvaluateSLSpaceScreen;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.OrderDetailScreen;
import nguyenhoanganhkhoa.com.myapplication.home.canteen.PurchaseCanteenScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {

    private Context context;
    private List<PurchaseItem> mPurchase;

    public static final int PURCHASE_SLSPACE_SCREEN = 1;
    public static final int PURCHASE_CANTEEN_SCREEN = 2;

    private int screen = PURCHASE_SLSPACE_SCREEN;

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public static final String TYPE_PENDING = "Pending";
    public static final String TYPE_DELIVERING = "Delivering";
    public static final String TYPE_IN_PROGRESS = "In Progress";
    public static final String TYPE_COMPLETED = "Completed";
    public static final String TYPE_CANCELLED = "Cancelled";

    public static final String TEXT_PENDING = "The order is pending confirmation";
    public static final String TEXT_DELIVERING = "The order is being delivered to you";
    public static final String TEXT_IN_PROGRESS = "Order in progress ";
    public static final String TEXT_COMPLETED = "Successfully delivered to you";
    public static final String TEXT_CANCELLED = "Cancelled by you";

    public static final int IMAGE_PENDING = R.drawable.ic_purchase_pending;
    public static final int IMAGE_DELIVERING = R.drawable.ic_purchase_delivery;
    public static final int IMAGE_IN_PROGRESS = R.drawable.ic_purchase_in_progress;
    public static final int IMAGE_COMPLETED = R.drawable.ic_purchase_sucessfully;
    public static final int IMAGE_CANCELLED = R.drawable.ic_purchase_cancelled;

    public PurchaseAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<PurchaseItem> list){
        this.mPurchase = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PurchaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_purchase,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseAdapter.ViewHolder holder, int position) {
        PurchaseItem purchase = mPurchase.get(position);
        if(purchase ==null)
        {
            return;
        }

        if(AppUtil.statusOrder.equals(TYPE_COMPLETED)){
            holder.btnEvaluate.setVisibility(View.VISIBLE);
        }
        else{
            holder.btnEvaluate.setVisibility(View.GONE);
        }

        holder.btnEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushData(purchase, EvaluateSLSpaceScreen.class);
            }
        });

        switch (purchase.getTypePurchase()){
            case TYPE_CANCELLED:
                setTextAndThumb(holder,TEXT_CANCELLED, IMAGE_CANCELLED);
                break;
            case TYPE_COMPLETED:
                setTextAndThumb(holder,TEXT_COMPLETED,IMAGE_COMPLETED);
                break;
            case TYPE_DELIVERING:
                setTextAndThumb(holder,TEXT_DELIVERING,IMAGE_DELIVERING);
                break;
            case TYPE_PENDING:
                setTextAndThumb(holder,TEXT_PENDING,IMAGE_PENDING);
                break;
            case TYPE_IN_PROGRESS:
                setTextAndThumb(holder,TEXT_IN_PROGRESS,IMAGE_IN_PROGRESS);
                break;
        }
        initAdapter(holder, purchase);

        holder.txtQuantity.setText(purchase.getQuantityItems_toString());
        holder.txtTotal.setText(purchase.getTotalPrice_toString());

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushData(purchase, OrderDetailScreen.class);
            }
        });
    }

    private void setTextAndThumb(ViewHolder holder, String text, int thumb) {
        holder.txtPurchaseStatus.setText(text);
        holder.imvIconPurchaseStatus.setImageResource(thumb);
    }


    private void initAdapter(ViewHolder holder, PurchaseItem purchase) {
        DrinkIncartAdapter adapter = new DrinkIncartAdapter(context);
        adapter.setData(purchase.getListItems());
        if(getScreen() == PURCHASE_CANTEEN_SCREEN){
            adapter.setNumScreen(DrinkIncartAdapter.PURCHASE_CANTEEN_SCREEN);
        }
        else{
            adapter.setNumScreen(DrinkIncartAdapter.PURCHASE_SCREEN);
        }
        holder.rcvPurchase.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        holder.rcvPurchase.setAdapter(adapter);


    }

    private void pushData(PurchaseItem purchase, Class screen) {
        Intent intent = new Intent(context, screen);
        intent.putExtra(AppUtil.MY_BUNDLE_TRANS, (Serializable) purchase.getListItems());
        intent.putExtra(AppUtil.MY_BUNDLE, purchase.getPurchaseID());
        context.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        if(mPurchase !=null)
            return mPurchase.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuantity, txtTotal, txtPurchaseStatus;
        RecyclerView rcvPurchase;
        ImageView imvIconPurchaseStatus;
        Button btnDetail, btnEvaluate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtTotal = itemView.findViewById(R.id.txtTotal);
            txtPurchaseStatus = itemView.findViewById(R.id.txtPurchaseStatus);
            rcvPurchase = itemView.findViewById(R.id.rcvPurchase);
            imvIconPurchaseStatus = itemView.findViewById(R.id.imvIconPurchaseStatus);
            btnDetail = itemView.findViewById(R.id.btnDetail);
            btnEvaluate = itemView.findViewById(R.id.btnEvaluate);
        }
    }
}
