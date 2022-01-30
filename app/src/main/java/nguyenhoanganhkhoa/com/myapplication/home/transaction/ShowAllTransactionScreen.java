package nguyenhoanganhkhoa.com.myapplication.home.transaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.MonthTransAdapter;
import nguyenhoanganhkhoa.com.adapter.TransAllAdapter;
import nguyenhoanganhkhoa.com.custom.bottomsheetdialog.CustomBottomSheetFilter;
import nguyenhoanganhkhoa.com.models.Month;
import nguyenhoanganhkhoa.com.models.Transaction;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.homepage.HomePageScreen;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class ShowAllTransactionScreen extends AppCompatActivity {

    RecyclerView rcvDisplayTransaction;
    MonthTransAdapter monthTransAdapter;
    ImageView imvAllTransBack, imvFilterTrans;
    RadioButton radTransAllAll, radTransAllWallet, radTransAllCanteen, radTransAllParking, radTransAlLSLSpace, radTransAllThuQuan, radTransAllTransfer;

    CustomBottomSheetFilter bottomSheetDialog = null;
    ImageView imvClose;


    private void linkView() {
        imvAllTransBack= findViewById(R.id.imvAllTransBack);

        radTransAllAll= findViewById(R.id.radTransAllAll);
        radTransAllWallet= findViewById(R.id.radTransAllWallet);
        radTransAllCanteen= findViewById(R.id.radTransAllCanteen);
        radTransAllParking= findViewById(R.id.radTransAllParking);
        radTransAlLSLSpace= findViewById(R.id.radTransAlLSLSpace);
        radTransAllThuQuan= findViewById(R.id.radTransAllThuQuan);
        radTransAllTransfer= findViewById(R.id.radTransAllTransfer);

        imvClose= findViewById(R.id.imvClose);
        imvFilterTrans= findViewById(R.id.imvFilterTrans);

        rcvDisplayTransaction = findViewById(R.id.rcvDisplayTransaction);

    }

    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_transaction_screen);

        linkView();
        initAdapter();
        addEvents();
        reusedConstraint.openNav(this);
        

    }




    private void createBottomSheetDialog() {
        if(bottomSheetDialog ==null){
            bottomSheetDialog = new CustomBottomSheetFilter(ShowAllTransactionScreen.this,R.style.BottomSheetDialogTheme,R.layout.custom_bottomdialog_filter);
        }
        bottomSheetDialog.show();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ShowAllTransactionScreen.this, HomePageScreen.class));
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
        radTransAllTransfer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        radTransAllWallet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });

        radTransAllCanteen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });


        radTransAllParking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });

        radTransAllThuQuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });

        radTransAlLSLSpace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        listTrans1.add(new Transaction(TransAllAdapter.TRANSACTION_TOPUP,"20 Oct, 10:07 ",true,50000));
        listTrans1.add(new Transaction(TransAllAdapter.TRANSACTION_PARKING,"20 10 Oct, 16:19  ",false,3000, false));
        listTrans1.add(new Transaction(TransAllAdapter.TRANSACTION_CANTEEN,"10 Oct, 16:19",false,3000));

        listTrans2.add(new Transaction(TransAllAdapter.TRANSACTION_THUQUAN,"20 Oct, 10:07 ",true,50000));
        listTrans2.add(new Transaction(TransAllAdapter.TRANSACTION_QUANCAFE,"20 10 Oct, 16:19  ",false,3000, false));

        listTrans3.add(new Transaction(TransAllAdapter.TRANSACTION_CANTEEN,"10 Oct, 16:19",false,5000));
        listTrans3.add(new Transaction(TransAllAdapter.TRANSACTION_TOPUP,"10 Oct, 16:19",true,120000));

        listMonth.add(new Month("Oct 2021",addGetCategory(listTrans1), totalIncome(addGetCategory(listTrans1)), totalExpense(addGetCategory(listTrans1))));
        listMonth.add(new Month("Sep 2021",addGetCategory(listTrans2), totalIncome(addGetCategory(listTrans2)), totalExpense(addGetCategory(listTrans2))));
        listMonth.add(new Month("Aug 2021",addGetCategory(listTrans3), totalIncome(addGetCategory(listTrans3)), totalExpense(addGetCategory(listTrans3))));


        // Nếu tháng nào không có dữ liệu thì xóa

        return listMonth;
    }



    private List<Transaction> addGetCategory(List<Transaction> list) {
        List<Transaction> listValue = new ArrayList<>();
        if(radTransAllAll.isChecked())
        {
            return list;
        }
        if(radTransAllWallet.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getTypeTrans().equals(TransAllAdapter.TRANSACTION_TOPUP))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }
        if(radTransAllCanteen.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getTypeTrans().equals(TransAllAdapter.TRANSACTION_CANTEEN))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }

        if(radTransAllParking.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getTypeTrans().equals(TransAllAdapter.TRANSACTION_PARKING))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }

        if(radTransAlLSLSpace.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getTypeTrans().equals(TransAllAdapter.TRANSACTION_QUANCAFE))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }


        if(radTransAllThuQuan.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getTypeTrans().equals(TransAllAdapter.TRANSACTION_THUQUAN))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }

        if(radTransAllTransfer.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getTypeTrans().equals(TransAllAdapter.TRANSACTION_TRANSFER))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }



        return null;

    }

    private double totalIncome(List<Transaction> list){
        if(!list.isEmpty()){
            double totalMoney = 0;
            for (int i = 0; i<list.size();i++){
                if(list.get(i).isIncome()){
                    totalMoney += list.get(i).getAmountTrans();
                }
            }
            return totalMoney;
        }
        else{
            return 0;
        }
    }

    private double totalExpense(List<Transaction> list){
        if(!list.isEmpty()){
            double totalMoney = 0;
            for (int i = 0; i<list.size();i++){
                if(!list.get(i).isIncome()){
                    totalMoney += list.get(i).getAmountTrans();
                }
            }
            return totalMoney;
        }
        else{
            return 0;
        }
    }


}