package nguyenhoanganhkhoa.com.myapplication.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.HomeButtonAdapter;
import nguyenhoanganhkhoa.com.models.HomeButtons;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

public class EditActivitiesScreen extends AppCompatActivity {

    RecyclerView rcvFavoriteButton;
    ImageView imvBack;
    HomeButtonAdapter adapter, adapter2;

    RecyclerView rcvOtherAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activities_screen);

        linkView();
        initAdapterRcv();
        intitAdapterGv();

        addEvents();

    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void intitAdapterGv() {
        try {
            adapter2 = new HomeButtonAdapter(this,R.layout.item_add_in_home_button);
            adapter2.setData(getOtherButtonList());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
            rcvOtherAct.setLayoutManager(linearLayoutManager);
            rcvOtherAct.setLayoutManager(new GridLayoutManager(this, 4));

            rcvOtherAct.setAdapter(adapter2);
        }
        catch (Exception e){
            Log.d("Error", "Fail to load adapter button in EditActsScreen " + e);
        }




    }

    private List<HomeButtons> getFavoriteButtonList() {
        List<HomeButtons> list = new ArrayList<>();
        list.add(new HomeButtons(R.drawable.ic_topup, AppUtil.top_up,R.drawable.ic_redbutton));
        list.add(new HomeButtons(R.drawable.ic_qrcode, AppUtil.qr_code,R.drawable.ic_redbutton));
        list.add(new HomeButtons(R.drawable.ic_news, AppUtil.news));
        list.add(new HomeButtons(R.drawable.ic_aboutus, AppUtil.about_us));
        return list;
    }


    private List<HomeButtons> getOtherButtonList() {
        List<HomeButtons> list = new ArrayList<>();
        list.add(new HomeButtons(R.drawable.ic_topup, AppUtil.top_up,R.drawable.ic_tickbutton));
        list.add(new HomeButtons(R.drawable.ic_qrcode, AppUtil.qr_code,R.drawable.ic_tickbutton));
        list.add(new HomeButtons(R.drawable.ic_act_history, AppUtil.history, R.drawable.ic_plusbutton));
        list.add(new HomeButtons(R.drawable.ic_act_helpcenter, AppUtil.help_center,R.drawable.ic_plusbutton));
        list.add(new HomeButtons(R.drawable.ic_act_security, AppUtil.security_center,R.drawable.ic_plusbutton));

        return list;
    }



    private void initAdapterRcv() {
        try {
            adapter = new HomeButtonAdapter(this,R.layout.item_add_in_home_button);
            adapter.setData(getFavoriteButtonList());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
            rcvFavoriteButton.setLayoutManager(linearLayoutManager);

            rcvFavoriteButton.setAdapter(adapter);
        }
        catch (Exception e){
            Log.d("Error", "Fail to load favorite button adapter: " + e);
        }


    }

    private void linkView() {
        rcvFavoriteButton = findViewById(R.id.rcvFavoriteButton);
        rcvOtherAct = findViewById(R.id.rcvOtherAct);
        imvBack = findViewById(R.id.imvBack);
    }
}