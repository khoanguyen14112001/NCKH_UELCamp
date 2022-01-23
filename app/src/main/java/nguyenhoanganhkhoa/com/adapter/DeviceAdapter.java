package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.nio.Buffer;
import java.util.List;

import nguyenhoanganhkhoa.com.customdialog.CustomDialogTwoButton;
import nguyenhoanganhkhoa.com.models.Date;
import nguyenhoanganhkhoa.com.models.Devices;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.LoginSettingScreen;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

    private Context context;
    private List<Devices> mDevices;
    private int layout;



    public DeviceAdapter(Context context, int layout) {
        this.context = context;
        this.layout = layout;

    }

    public void setData(List<Devices> list){
        this.mDevices = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DeviceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceAdapter.ViewHolder holder, int position) {
        Devices devices = mDevices.get(position);
        if(devices==null)
        {
            return;
        }
        devices.setCurrentlyUsedDevice(position == 0);

        if(devices.isCurrentlyUsedDevice()){
            holder.imvSignout.setVisibility(View.GONE);
            holder.txtCurrentlyUsedDevice.setVisibility(View.VISIBLE);
        }
        else{
            holder.imvSignout.setVisibility(View.VISIBLE);
            holder.txtCurrentlyUsedDevice.setVisibility(View.GONE);
        }
        holder.imvDevice.setImageResource(devices.getThumbDevice());
        holder.txtNameDevice.setText(devices.getNameDevice());
        holder.txtBrandDevice.setText(devices.getBrandDevice());
        holder.txtStatusDevice.setText(devices.getStatusDevice());

        addEvent(holder, position);


    }

    private void addEvent(ViewHolder holder, int position) {
        if (position!=0){
            holder.imvSignout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CustomDialogTwoButton dialog = new CustomDialogTwoButton(context,R.layout.custom_dialog_signout_device);
                    dialog.btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteItem(position);
                            dialog.dismiss();
                        }
                    });
                    dialog.btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }
    }

    private void deleteItem (int position){
        mDevices.remove(position);
        notifyItemRemoved(position);
        // dòng này để load lại dữ liệu sau khi đã xóa lên
        notifyItemRangeChanged(position, mDevices.size());
    }

    @Override
    public int getItemCount() {
        if(mDevices !=null)
            return mDevices.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvDevice, imvSignout;
        private TextView txtNameDevice, txtBrandDevice, txtStatusDevice,
                txtCurrentlyUsedDevice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvDevice = itemView.findViewById(R.id.imvDevice);
            imvSignout = itemView.findViewById(R.id.imvSignout);
            txtNameDevice = itemView.findViewById(R.id.txtNameDevice);
            txtBrandDevice = itemView.findViewById(R.id.txtBrandDevice);
            txtStatusDevice = itemView.findViewById(R.id.txtStatusDevice);
            txtCurrentlyUsedDevice = itemView.findViewById(R.id.txtCurrentlyUsedDevice);

        }
    }
}
