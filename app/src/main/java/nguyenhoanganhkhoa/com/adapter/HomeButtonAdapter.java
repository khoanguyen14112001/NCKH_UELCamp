package nguyenhoanganhkhoa.com.adapter;

import static nguyenhoanganhkhoa.com.thirdlink.AppUtil.top_up;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import nguyenhoanganhkhoa.com.fragments.HomeFragment;
import nguyenhoanganhkhoa.com.models.HomeButtons;
import nguyenhoanganhkhoa.com.models.KidTerm;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.AboutUsScreen;
import nguyenhoanganhkhoa.com.myapplication.home.NewsScreen;
import nguyenhoanganhkhoa.com.myapplication.home.QRCodeScreen;
import nguyenhoanganhkhoa.com.myapplication.home.TopUpMainScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

public class HomeButtonAdapter extends RecyclerView.Adapter<HomeButtonAdapter.ViewHolder> {
    private Context context;
    private int layout;

    public HomeButtonAdapter(Context context, int layout) {
        this.context = context;
        this.layout = layout;
    }

    private List<HomeButtons> homeButtonsList;

    public void setData(List<HomeButtons> list){
        this.homeButtonsList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HomeButtonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeButtonAdapter.ViewHolder holder, int position) {
        HomeButtons homeButtons = homeButtonsList.get(position);
        if(homeButtons==null){
            return;
        }

        if(layout == R.layout.item_add_in_home_button)
        {
            holder.txtButtonName.setText(homeButtons.getNameButton());
            holder.imbButtonHome.setImageResource(homeButtons.getThumbButton());
            holder.imvStatusButton.setImageResource(homeButtons.getStatusButton());
        }



        if(layout==R.layout.item_home_button){
            holder.txtButtonName.setText(homeButtons.getNameButton());
            holder.imbButtonHome.setImageResource(homeButtons.getThumbButton());
            Context mContext = context.getApplicationContext();

            View.OnClickListener clickForEvents = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Class mClass = null;
                    switch (homeButtons.getNameButton()){
                        case AppUtil.top_up:
                            mClass = TopUpMainScreen.class;
                            break;
                        case AppUtil.qr_code:
                            mClass = QRCodeScreen.class;
                            break;
                        case AppUtil.news:
                            mClass = NewsScreen.class;
                            break;
                        case AppUtil.about_us:
                            mClass = AboutUsScreen.class;
                            break;
                    }
                    Intent intent = new Intent(mContext,mClass);
                    context.startActivity(intent);
                }
            };
            holder.imbButtonHome.setOnClickListener(clickForEvents);
        }




    }



    @Override
    public int getItemCount() {
        if(homeButtonsList !=null)
            return homeButtonsList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imbButtonHome;
        TextView txtButtonName;
        ImageView imvStatusButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imbButtonHome =itemView.findViewById(R.id.imbButtonHome);
            txtButtonName =itemView.findViewById(R.id.txtButtonName);
            imvStatusButton =itemView.findViewById(R.id.imvStatusButton);
        }
    }
}
