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

import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.models.Voucher;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.ViewHolder> {
    private Context context;
    private List<Voucher> mVoucher;
    private int sizeList = 0;

    public void setSizeList(int sizeList) {
        this.sizeList = sizeList;
    }

    public VoucherAdapter(Context context) {
        this.context = context;
    }


    public void setData(List<Voucher> list){
        this.mVoucher = list;
        notifyDataSetChanged();
    }
    public static final String VOUCHER_FREE_SHIP = "FREE SHIP";
    public static final String VOUCHER_UEL_CAMP = "UEL CAMP";
    public static final String VOUCHER_FREE_SHIP_toStringDisplay = "FREE\nSHIP";
    public static final String VOUCHER_UEL_CAMP_toStringDisplay = "UEL\nCAMP";



    @NonNull
    @Override
    public VoucherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_voucher,parent,false);
        // Chỗ view này nó còn đại diện kiểu hiển thị nữa nha, có ăn parent ko thì nó trong đây nè

        // cái view hiện tại đại diện cho recyleview
        return new ViewHolder(view);
    }

    ReusedConstraint reusedConstraint = new ReusedConstraint(context);

    @Override
    public void onBindViewHolder(@NonNull VoucherAdapter.ViewHolder holder, int position) {
        Voucher voucher = mVoucher.get(position);
        if(voucher ==null){
            return;
        }

        switch (voucher.getVoucherName()){
            case VOUCHER_FREE_SHIP:
                setTextColor(holder, R.color.white);
                holder.txtVoucherName.setText(VOUCHER_FREE_SHIP_toStringDisplay);
                break;
            case VOUCHER_UEL_CAMP:
                setTextColor(holder, R.color.black);
                holder.txtVoucherName.setText(VOUCHER_UEL_CAMP_toStringDisplay);
                break;
        }


        if(voucher.isActive()){
            switch (voucher.getVoucherName()){
                case VOUCHER_FREE_SHIP:
                    setBackgroundColor(holder, R.drawable.img_voucher_thumb_black );
                    break;
                case VOUCHER_UEL_CAMP:
                    setBackgroundColor(holder, R.drawable.img_voucher_thumb_yellow);
                    break;
            }
        }
        else{
            setBackgroundColor(holder,R.drawable.img_voucher_thumb_darkgrey);
        }


        holder.txtVoucherContent1.setText(voucher.getVoucherContent());
        if(voucher.getVoucherContent2().isEmpty()){
            holder.txtVoucherContent2.setVisibility(View.GONE);
        }
        else{
            holder.txtVoucherContent2.setText(voucher.getVoucherContent2());
            holder.txtVoucherContent2.setVisibility(View.VISIBLE);

        }

        if(voucher.getVoucherUpTo() == 0){
            holder.txtVoucherUpTo.setVisibility(View.GONE);
        }
        else{
            holder.txtVoucherUpTo.setVisibility(View.VISIBLE);
            holder.txtVoucherUpTo.setText(voucher.getVoucherUpTo_String());
        }

        holder.txtExpireDate.setText(voucher.getVoucherExpireDate());
    }

    private void setBackgroundColor(ViewHolder holder, int background){
        holder.layout_background_voucher.setBackground(context.getDrawable(background));
    }
    private void setTextColor(ViewHolder holder, int colorText){
        holder.txtVoucherName.setTextColor(context.getColor(colorText));
    }

    @Override
    public int getItemCount() {
        if(mVoucher !=null && sizeList == 0)
            return mVoucher.size();
        if(sizeList!= 0){
            return sizeList;
        }
        else
            return 0;
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout_background_voucher;
        TextView txtVoucherName, txtVoucherContent1, txtVoucherContent2, txtVoucherUpTo, txtExpireDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_background_voucher =itemView.findViewById(R.id.layout_background_voucher);
            txtVoucherName =itemView.findViewById(R.id.txtVoucherName);
            txtVoucherContent1 =itemView.findViewById(R.id.txtVoucherContent1);
            txtVoucherContent2 =itemView.findViewById(R.id.txtVoucherContent2);
            txtVoucherUpTo =itemView.findViewById(R.id.txtVoucherUpTo);
            txtExpireDate =itemView.findViewById(R.id.txtExpireDate);

        }
    }
}
