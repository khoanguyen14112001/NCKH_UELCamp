package nguyenhoanganhkhoa.com.myapplication.home.parkinglot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DateAdapter;
import nguyenhoanganhkhoa.com.custom.bottomsheetdialog.CustomBottomSheetFilterHistory;
import nguyenhoanganhkhoa.com.models.Date;
import nguyenhoanganhkhoa.com.models.History;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class ShowAllParkingLotScreen extends AppCompatActivity {

    RecyclerView rcvHistoryParkingAll;
    RadioButton radHistoryAll, radHistoryEntry, radHistoryExit;
    ImageView imvFilter, imvBack;
    CustomBottomSheetFilterHistory bottomSheetDialog;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    private void linkView() {
        rcvHistoryParkingAll = findViewById(R.id.rcvHistoryParkingAll);
        radHistoryAll = findViewById(R.id.radHistoryAll);
        radHistoryEntry = findViewById(R.id.radHistoryEntry);
        radHistoryExit = findViewById(R.id.radHistoryExit);

        imvFilter = findViewById(R.id.imvFilter);
        imvBack = findViewById(R.id.imvBack);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_parking_lot_screen);
        linkView();
        initAdapter();
        reusedConstraint.openNav(this);
        addEvents();
    }

    private void initAdapter() {
        DateAdapter dateAdapter= new DateAdapter(this,R.layout.item_history_recycleview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvHistoryParkingAll.setLayoutManager(linearLayoutManager);

        dateAdapter.setData(getListDate());
        rcvHistoryParkingAll.setAdapter(dateAdapter);
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
        imvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bottomSheetDialog ==null){
                    bottomSheetDialog = new CustomBottomSheetFilterHistory(ShowAllParkingLotScreen.this,R.style.BottomSheetDialogTheme,R.layout.custom_bottomdialog_filter);
                }
                bottomSheetDialog.show();
            }
        });

        radHistoryAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();

            }
        });
        radHistoryExit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();

            }
        });
        radHistoryEntry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });

    }

    private List<Date> getListDate() {
        List<Date> listMonth = new ArrayList<>();

        List<History> listHis1 = new ArrayList<>();
        List<History> listHis2 = new ArrayList<>();
        List<History> listHis3 = new ArrayList<>();

        listHis1.add(new History("21 Oct, 20:07", true));
        listHis1.add(new History("21 Oct, 20:07", false));
        listHis1.add(new History("21 Oct, 20:07", true));
        listHis1.add(new History("21 Oct, 20:07", true));
        listHis1.add(new History("21 Oct, 20:07", false));
        listHis1.add(new History("21 Oct, 20:07", true));

        listHis2.add(new History("21 Sep, 20:07", true));
        listHis2.add(new History("21 Sep, 20:07", false));
        listHis2.add(new History("21 Sep, 20:07", true));
        listHis2.add(new History("21 Sep, 20:07", true));
        listHis2.add(new History("21 Sep, 20:07", false));
        listHis2.add(new History("21 Sep, 20:07", true));

        listHis3.add(new History("21 Aug, 20:07", true));
        listHis3.add(new History("21 Aug, 20:07", true));



        listMonth.add(new Date("Oct 2021",addGetCategory(listHis1)));
        listMonth.add(new Date("Sep 2021",addGetCategory(listHis2)));
        listMonth.add(new Date("Aug 2021",addGetCategory(listHis3)));


        return listMonth;
    }

    private List<History> addGetCategory(List<History> list) {

        List<History> listValue = new ArrayList<>();
        if(radHistoryAll.isChecked())
        {
            return list;
        }
        if(radHistoryEntry.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).isEntry())
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }
        if(radHistoryExit.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(!list.get(i).isEntry())
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }
        return listValue;

    }


}