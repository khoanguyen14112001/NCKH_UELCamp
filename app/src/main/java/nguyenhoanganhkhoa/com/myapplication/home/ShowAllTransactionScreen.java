package nguyenhoanganhkhoa.com.myapplication.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.MonthTransAdapter;
import nguyenhoanganhkhoa.com.customdialog.CustomBottomSheetFilter;
import nguyenhoanganhkhoa.com.models.Month;
import nguyenhoanganhkhoa.com.models.Transaction;
import nguyenhoanganhkhoa.com.myapplication.R;

public class ShowAllTransactionScreen extends AppCompatActivity {

    RecyclerView rcvDisplayTransaction;
    MonthTransAdapter monthTransAdapter;
    ImageView imvAllTransBack, imvFilterTrans;
    RadioButton radTransAllAll, radTransAllTopUp, radTransAllParkingPayment;

    CustomBottomSheetFilter bottomSheetDialog = null;
    ImageView imvClose;


    private void linkView() {
        imvAllTransBack= findViewById(R.id.imvAllTransBack);
        radTransAllAll= findViewById(R.id.radTransAllAll);
        radTransAllTopUp= findViewById(R.id.radTransAllTopUp);
        radTransAllParkingPayment= findViewById(R.id.radTransAllParkingPayment);

        imvClose= findViewById(R.id.imvClose);
        imvFilterTrans= findViewById(R.id.imvFilterTrans);

        rcvDisplayTransaction = findViewById(R.id.rcvDisplayTransaction);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_transaction_screen);

        linkView();
        initAdapter();
        addEvents();
        

    }


    private void createBottomSheetDialog() {
        if(bottomSheetDialog ==null){
            bottomSheetDialog = new CustomBottomSheetFilter(ShowAllTransactionScreen.this,R.style.BottomSheetDialogTheme,R.layout.custom_bottomdialog_filter);
        }
        bottomSheetDialog.show();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ShowAllTransactionScreen.this,HomePageScreen.class));
    }

    private void addEvents() {
        imvFilterTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBottomSheetDialog();
            }
        });

        imvAllTransBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowAllTransactionScreen.this,HomePageScreen.class));
            }
        });
        radTransAllTopUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });
        radTransAllAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });
        radTransAllParkingPayment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });
    }

    private void initAdapter() {
        try {
            monthTransAdapter= new MonthTransAdapter(ShowAllTransactionScreen.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowAllTransactionScreen.this,RecyclerView.VERTICAL,false);
            rcvDisplayTransaction.setLayoutManager(linearLayoutManager);

            monthTransAdapter.setData(getListMonth());
            rcvDisplayTransaction.setAdapter(monthTransAdapter);
        }
        catch (Exception e)
        {
            Log.d("Error", "Fail to load adapter in ShowAllTransactionScreen: " + e);
        }

    }

    private List<Month> getListMonth() {
        List<Month> listMonth = new ArrayList<>();

        List<Transaction> listTrans1 = new ArrayList<>();
        List<Transaction> listTrans2 = new ArrayList<>();
        List<Transaction> listTrans3 = new ArrayList<>();
        List<Transaction> listTrans4 = new ArrayList<>();
        List<Transaction> listTrans5 = new ArrayList<>();

        listTrans1.add(new Transaction("Top up","20 Oct, 10:07 ","+50.000",R.drawable.ic_topup,R.drawable.ic_tickbutton));
        listTrans1.add(new Transaction("Parking payment","10 Oct, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        listTrans1.add(new Transaction("Parking payment","09 Oct, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));

        listTrans2.add(new Transaction("Parking payment","20 Sep, 10:07 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        listTrans2.add(new Transaction("Parking payment","10 Sep, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        listTrans2.add(new Transaction("Parking payment","09 Sep, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        listTrans2.add(new Transaction("Parking payment","08 Sep, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));

        listTrans3.add(new Transaction("Parking payment","20 Aug, 10:07 ","-3.000",R.drawable.ic_topup,R.drawable.ic_tickbutton));
        listTrans3.add(new Transaction("Parking payment","10 Aug, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        listTrans3.add(new Transaction("Parking payment","09 Aug, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_warning_red));
        listTrans3.add(new Transaction("Parking payment","08 Aug, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        listTrans3.add(new Transaction("Top up","07 Aug, 16:19 ","+20.000",R.drawable.ic_topup,R.drawable.ic_warning_red));


        listTrans4.add(new Transaction("Parking payment","20 July, 10:07 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        listTrans4.add(new Transaction("Parking payment","10 July, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        listTrans4.add(new Transaction("Parking payment","09 July, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        listTrans4.add(new Transaction("Parking payment","08 July, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        listTrans4.add(new Transaction("Top up","07 July, 16:19 ","+20.000",R.drawable.ic_topup,R.drawable.ic_tickbutton));
        listTrans4.add(new Transaction("Parking up","06 July, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_warning_red));
        listTrans4.add(new Transaction("Parking up","05 July, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        listTrans4.add(new Transaction("Top up","04 July, 16:19 ","+20.000",R.drawable.ic_topup,R.drawable.ic_tickbutton));




        listMonth.add(new Month("Oct 2021",addGetCategory(listTrans1)));
        listMonth.add(new Month("Sep 2021",addGetCategory(listTrans2)));
        listMonth.add(new Month("Aug 2021",addGetCategory(listTrans3)));
        listMonth.add(new Month("July 2021",addGetCategory(listTrans4)));
        listMonth.add(new Month("Jun 2021",addGetCategory(listTrans5)));

        // Nếu tháng nào không có dữ liệu thì xóa
        for(int i =0; i< listMonth.size();i++)
        {
            if(listMonth.get(i).getTransactions().isEmpty())
            {
                listMonth.remove(i);

            }
        }
        return listMonth;
    }

    private List<Transaction> addGetCategory(List<Transaction> list) {

        List<Transaction> listValue = new ArrayList<>();
        if(radTransAllAll.isChecked())
        {
            return list;
        }
        if(radTransAllTopUp.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getStatusTrans().equals("Top up"))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }
        if(radTransAllParkingPayment.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getStatusTrans().equals("Parking payment"))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }
        return listValue;

    }


}