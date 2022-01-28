package nguyenhoanganhkhoa.com.myapplication.home.parkinglot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.HistoryAdapter;
import nguyenhoanganhkhoa.com.models.History;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class ParkingLotHomeScreen extends AppCompatActivity {

    RecyclerView rcvHistory;
    ImageView imvBack;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    TextView txtSeeAll;

    private void linkView() {
        rcvHistory = findViewById(R.id.rcvHistory);
        imvBack = findViewById(R.id.imvBack);
        txtSeeAll = findViewById(R.id.txtSeeAll);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot_home_screen);
        linkView();
        initAdapter();
        addEvents();
        reusedConstraint.openNav(this);

    }

    private void initAdapter() {
        HistoryAdapter adapter = new HistoryAdapter(this);
        adapter.setData(getListHistory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);

        rcvHistory.setLayoutManager(linearLayoutManager);
        rcvHistory.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txtSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParkingLotHomeScreen.this,ShowAllParkingLotScreen.class));
            }
        });
    }

    private List<History> getListHistory(){
        List<History> listHis1 = new ArrayList<>();
        listHis1.add(new History("21 Oct, 20:07", true));
        listHis1.add(new History("19 Oct, 20:07", false));
        listHis1.add(new History("18 Oct, 20:07", true));
        listHis1.add(new History("17 Oct, 20:07", false));
        listHis1.add(new History("16 Oct, 20:07", true));
        listHis1.add(new History("15 Oct, 20:07", false));
        listHis1.add(new History("14 Oct, 20:07", true));
        return listHis1;
    }


}