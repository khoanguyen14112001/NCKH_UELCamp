package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogTwoButton;
import nguyenhoanganhkhoa.com.models.Address;
import nguyenhoanganhkhoa.com.models.DrinkOption;
import nguyenhoanganhkhoa.com.myapplication.R;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private Context context;
    private List<Address> mAddress;
    private int layout;



    public AddressAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<Address> list){
        this.mAddress = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_address,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.ViewHolder holder, int position) {
        Address address = mAddress.get(position);
        if(address ==null)
        {
            return;
        }
        if(holder.getAdapterPosition()==0){
            holder.layout_default_address.setVisibility(View.VISIBLE);
        }

        if(isNewRadioChecked){
            setDefaultAddress(holder,address);
        }
        else{
            if(holder.getAdapterPosition() ==0){
                address.setDefaultAddress(true);
                setDefaultAddress(holder,address);
            }

        }

        holder.txtLocationAddress.setText(address.getLocationAddress());
        holder.txtNameAddress.setText(address.getNamePeopleAddress());
        holder.txtPhoneAddress.setText(address.getPhoneAddress());

        holder.layout_item_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isNewRadioChecked = true;
                mAddress.get(lastCheckedPosition).setDefaultAddress(false);
                address.setDefaultAddress(true);
                lastCheckedPosition = holder.getAdapterPosition();

                notifyDataSetChanged();
            }
        });


    }

    private int lastCheckedPosition = 0;
    private boolean isNewRadioChecked = false;
    private void setDefaultAddress(ViewHolder holder, Address address){
        if(address.isDefaultAddress()){
            holder.layout_item_address.setBackgroundColor(context.getColor(R.color.primary_yellow));
        }
        else{
            holder.layout_item_address.setBackgroundColor(context.getColor(R.color.xamNen));
        }

    }
    @Override
    public int getItemCount() {
        if(mAddress !=null){
            return mAddress.size();
        }
        else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPhoneAddress, txtLocationAddress, txtNameAddress;
        LinearLayout layout_item_address;
        ConstraintLayout layout_default_address;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item_address = itemView.findViewById(R.id.layout_item_address);
            txtLocationAddress = itemView.findViewById(R.id.txtLocationAddress);
            txtPhoneAddress = itemView.findViewById(R.id.txtPhoneAddress);
            txtNameAddress = itemView.findViewById(R.id.txtNameAddress);
            layout_default_address = itemView.findViewById(R.id.layout_default_address);

        }
    }
}
