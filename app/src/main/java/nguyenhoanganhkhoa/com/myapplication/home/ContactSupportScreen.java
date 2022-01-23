package nguyenhoanganhkhoa.com.myapplication.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DetailProTransAdapter;
import nguyenhoanganhkhoa.com.models.DetailProTrans;
import nguyenhoanganhkhoa.com.models.History;
import nguyenhoanganhkhoa.com.models.Transaction;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

public class ContactSupportScreen extends AppCompatActivity {

    RecyclerView rcvContactSupport;
    DetailProTransAdapter adapter;

    History history;

    ImageView imvBack;

    ImageView imvStatus;
    TextView txtStatusSupport, txtDateSupport;

    private void linkView() {
        rcvContactSupport = findViewById(R.id.rcvContactSupport);
        imvBack = findViewById(R.id.imvBack);

        imvStatus = findViewById(R.id.imvStatus);
        txtStatusSupport = findViewById(R.id.txtStatusSupport);
        txtDateSupport = findViewById(R.id.txtDateSupport);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_support_screen);

        linkView();
        initAdapter();
        getData();
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

    private void initAdapter() {
        try{
            adapter = new DetailProTransAdapter(this,R.layout.item_detail_problem_trans);
            adapter.setData(getListProblem());

            LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            rcvContactSupport.setLayoutManager(manager);
            rcvContactSupport.setAdapter(adapter);
        }
        catch (Exception e){
            Log.d("Error", "Fail to load adapter to ContactSupportScreen: " + e);
        }

    }

    private List<DetailProTrans> getListProblem() {
        List<DetailProTrans> list = new ArrayList<>();
        list.add(new DetailProTrans("This is not my entry and exit history"));
        list.add(new DetailProTrans("There is confusion between entry and exit"));
        list.add(new DetailProTrans("Datetime format incorrect"));
        list.add(new DetailProTrans("I do not want to receive notification history about my parking"));

        return list;
    }

    private void getData() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra(AppUtil.MY_BUNDLE_TRANS);
            if(bundle!=null){
                history = (History) bundle.getSerializable(AppUtil.SELECTED_ITEM_TRANS);
                imvStatus.setImageResource(history.getColorHis());
                txtStatusSupport.setText(history.getStatusInOut());
                txtDateSupport.setText(history.getDayInOut());
            }
        }

        catch (Exception e){
            Log.d("Error", "Cannot get data from history adapter " + e);
        }




    }
}