package nguyenhoanganhkhoa.com.myapplication.home.transaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DetailProTransAdapter;
import nguyenhoanganhkhoa.com.adapter.TransAllAdapter;
import nguyenhoanganhkhoa.com.models.DetailProTrans;
import nguyenhoanganhkhoa.com.models.Transaction;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.homepage.HomePageScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class DetailTransaction extends AppCompatActivity {

    RecyclerView rcvDetailProblem;
    DetailProTransAdapter adapter;
    ImageView imvBack;

    ImageView imvStatus, imvStatusTrans;
    TextView txtStatusTrans, txtMoneyTrans, txtDateTrans, txtMoneyPlusMinus;

    Transaction transaction;


    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    private void linkView() {
        rcvDetailProblem = findViewById(R.id.rcvDetailProblem);
        imvBack = findViewById(R.id.imvBack);

        imvStatus = findViewById(R.id.imvStatus);
        txtStatusTrans = findViewById(R.id.txtStatusTrans);
        txtMoneyTrans = findViewById(R.id.txtMoneyTrans);
        txtDateTrans = findViewById(R.id.txtDateTrans);
        imvStatusTrans = findViewById(R.id.imvStatusTrans);
        txtMoneyPlusMinus = findViewById(R.id.txtMoneyPlusMinus);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaction);

        linkView();
        initAdapter();
        getData();
        addEvents();
        reusedConstraint.openNav(this);

    }


    private void getData(){
        try{
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra(AppUtil.MY_BUNDLE);
            if(bundle!=null){
                transaction = (Transaction) bundle.getSerializable(AppUtil.SELECTED_ITEM);
                switch (transaction.getTypeTrans()){
                    case TransAllAdapter.TRANSACTION_TOPUP:
                        setNameAndImage(R.drawable.ic_topup,"Top up");
                        break;
                    case TransAllAdapter.TRANSACTION_TRANSFER:
                        setNameAndImage(R.drawable.ic_transfer,"Transfer");
                        break;
                    case TransAllAdapter.TRANSACTION_CANTEEN:
                        setNameAndImage(R.drawable.ic_canteen,"Canteen");
                        break;
                    case TransAllAdapter.TRANSACTION_PARKING:
                        setNameAndImage(R.drawable.ic_bike,"Parking");
                        break;
                    case TransAllAdapter.TRANSACTION_THUQUAN:
                        setNameAndImage(R.drawable.ic_thuquan,"Stationery");
                        break;
                    case TransAllAdapter.TRANSACTION_QUANCAFE:
                        setNameAndImage(R.drawable.ic_quancafe,"SLSpace");
                        break;
                }

                if(transaction.isSuccess()){
                    imvStatusTrans.setImageResource(R.drawable.ic_tickbutton);
                }
                else{
                    imvStatusTrans.setImageResource(R.drawable.ic_warning_red);
                }

                if(transaction.isIncome()){
                    txtMoneyPlusMinus.setText("+");
                }
                else{
                    txtMoneyPlusMinus.setText("-");
                }
                txtDateTrans.setText(transaction.getDateTrans());
                formatMoney(transaction);
            }
        }
        catch (Exception e){
            Log.d("Error", "Cannot get data from transaction adapter: " + e);
        }

    }

    private void formatMoney(Transaction transaction) {
        double money = transaction.getAmountTrans();

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0", decimalFormatSymbols);

        String moneyAft = decimalFormat.format(money);
        txtMoneyTrans.setText(moneyAft);

    }


    private void setNameAndImage(int thumb, String name){
        imvStatus.setImageResource(thumb);
        txtStatusTrans.setText(name);
    }


    private void initAdapter() {
        try {
            adapter = new DetailProTransAdapter(this,R.layout.item_detail_problem_trans);
            adapter.setData(getListProDetail());

            LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            rcvDetailProblem.setLayoutManager(manager);

            rcvDetailProblem.setAdapter(adapter);
        }
        catch (Exception e){
            Log.d("Error", "Fail to load adapter in DetailTransaction: " + e);
        }


    }

    private List<DetailProTrans> getListProDetail() {
        List<DetailProTrans> list = new ArrayList<>();
        list.add(new DetailProTrans("I want to cancel the wrong top up"));
        list.add(new DetailProTrans("Fund transfer status is successful but my balance does not increase"));
        list.add(new DetailProTrans("E-wallet account is deducted 2 times for this transaction"));
        list.add(new DetailProTrans("E-wallet account has been deducted but myParking account has not changed"));

        return list;

    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.checkNavStatusComeBack(DetailTransaction.this);
            }
        });

    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }
}