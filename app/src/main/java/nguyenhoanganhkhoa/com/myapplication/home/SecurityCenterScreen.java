package nguyenhoanganhkhoa.com.myapplication.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DialogNotificationAdapter;
import nguyenhoanganhkhoa.com.adapter.TermAdapter;
import nguyenhoanganhkhoa.com.models.KidTerm;
import nguyenhoanganhkhoa.com.models.Term;
import nguyenhoanganhkhoa.com.myapplication.R;

public class SecurityCenterScreen extends AppCompatActivity {

    RecyclerView rcvTerms;
    TermAdapter termAdapter;
    ImageView imvSecuritySettingBack;

    private void linkView() {
        rcvTerms = findViewById(R.id.rcvTerms);
        imvSecuritySettingBack = findViewById(R.id.imvSecuritySettingBack);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_center_screen);

        linkView();
        initAdapter();
        addEvents();
    }

    private void initAdapter() {
        try {
            termAdapter= new TermAdapter(this,R.layout.item_term);
            rcvTerms.setAdapter(termAdapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
            rcvTerms.setLayoutManager(linearLayoutManager);

            termAdapter.setData(getTermList());
            rcvTerms.setAdapter(termAdapter);
        }
        catch (Exception e) {
            Log.d("Error", "Fail to load adapter in SecurityScreen: " + e);

        }


    }

    private List<Term> getTermList() {
        List<KidTerm> listKidTerm1 = new ArrayList<>();

        List<Term> termList = new ArrayList<>();

        listKidTerm1.add(new KidTerm("General terms","Something"));
        listKidTerm1.add(new KidTerm("Your account","Something"));
        listKidTerm1.add(new KidTerm("Personal information","Something"));
        listKidTerm1.add(new KidTerm("Your responsibility","Something"));
        listKidTerm1.add(new KidTerm("Financial terms","Something"));
        listKidTerm1.add(new KidTerm("Indemnification and liability","Something"));

        termList.add(new Term("Terms of service",listKidTerm1));
        termList.add(new Term("Privacy Policy",listKidTerm1));
        termList.add(new Term("Data Sharing and Third Parties",listKidTerm1));



        return termList;
    }

    private void addEvents() {
        imvSecuritySettingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}