package nguyenhoanganhkhoa.com.myapplication.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DetailProTransAdapter;
import nguyenhoanganhkhoa.com.models.DetailProTrans;
import nguyenhoanganhkhoa.com.models.Transaction;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

public class DetailTransaction extends AppCompatActivity {

    RecyclerView rcvDetailProblem;
    DetailProTransAdapter adapter;
    ImageView imvBack;

    ImageView imvStatus, imvStatusTrans;
    TextView txtStatusTrans, txtMoneyTrans, txtDateTrans;

    Transaction transaction;

    private void linkView() {
        rcvDetailProblem = findViewById(R.id.rcvDetailProblem);
        imvBack = findViewById(R.id.imvBack);

        imvStatus = findViewById(R.id.imvStatus);
        txtStatusTrans = findViewById(R.id.txtStatusTrans);
        txtMoneyTrans = findViewById(R.id.txtMoneyTrans);
        txtDateTrans = findViewById(R.id.txtDateTrans);
        imvStatusTrans = findViewById(R.id.imvStatusTrans);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaction);

        linkView();
        initAdapter();
        getData();
        addEvents();

    }


    private void getData(){
        try{
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra(AppUtil.MY_BUNDLE);
            if(bundle!=null){
                transaction = (Transaction) bundle.getSerializable(AppUtil.SELECTED_ITEM);
                imvStatus.setImageResource(transaction.getImgStatusTrans());
                imvStatusTrans.setImageResource(transaction.getImgSuccessTrans());
                txtDateTrans.setText(transaction.getDateTrans());
                txtMoneyTrans.setText(transaction.getMoneyTrans());
                txtStatusTrans.setText(transaction.getStatusTrans());
            }
        }
        catch (Exception e){
            Log.d("Error", "Cannot get data from transaction adapter: " + e);
        }

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
                Intent intent = new Intent(DetailTransaction.this,HomePageScreen.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetailTransaction.this,HomePageScreen.class);
        startActivity(intent);
    }
}