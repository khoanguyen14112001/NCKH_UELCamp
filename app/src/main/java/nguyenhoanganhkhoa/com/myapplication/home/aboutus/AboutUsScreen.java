package nguyenhoanganhkhoa.com.myapplication.home.aboutus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.MemberAdapter;
import nguyenhoanganhkhoa.com.models.Member;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class AboutUsScreen extends AppCompatActivity {
    RecyclerView rcvListMemberTeam;
    MemberAdapter memberAdapter;
    TextView txtIntroduceTeam, txtIntroduceSystem;
    ImageView imvComebackAboutUs;

    ReusedConstraint reusedConstraint = new ReusedConstraint(AboutUsScreen.this);

    private void linkView() {
        rcvListMemberTeam = findViewById(R.id.rcvListMemberTeam);
        txtIntroduceTeam = findViewById(R.id.txtIntroduceTeam);
        txtIntroduceSystem = findViewById(R.id.txtIntroduceSystem);
        imvComebackAboutUs = findViewById(R.id.imvComebackAboutUs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_screen);

        linkView();
        initAdapter();
        addEvents();
        reusedConstraint.openNav(this);
    }

    private void initAdapter() {
        try {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AboutUsScreen.this,RecyclerView.HORIZONTAL,false);
            rcvListMemberTeam.setLayoutManager(linearLayoutManager);

            memberAdapter = new MemberAdapter(AboutUsScreen.this);
            memberAdapter.setData(getMemberList());

            rcvListMemberTeam.setAdapter(memberAdapter);
        }
        catch (Exception e){
            Log.d("Error", "Fail to load adapter member in AboutUsScreen: " + e);
        }


    }

    public static List<Member> getMemberList() {
        List<Member> list = new ArrayList<>();
        list.add(new Member("Vo Thi Tuyet Trinh", false,"Business Analyst",
                "July 06, 2001",false));

        list.add(new Member("Nguyen Hoang Anh Khoa", true,"Developer",
                "November 14, 2001"));

        list.add(new Member("Truong Hoang y", false,"Designer",
                "July 06, 2001"));


        list.add(new Member("Tran Hoang Gia Bao", true,"Developer",
                "July 06, 2001"));

        list.add(new Member("Trinh Chan Khoa", true,"Designer",
                "July 06, 2001"));




        return list;
    }

    private void addEvents() {
        reusedConstraint.changeColor(txtIntroduceTeam,0,13,R.color.primary_yellow);
        reusedConstraint.changeColor(txtIntroduceSystem,0,8,R.color.primary_yellow);
        imvComebackAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }
}