package nguyenhoanganhkhoa.com.myapplication.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DetailMemberAdapter;
import nguyenhoanganhkhoa.com.adapter.MemberAdapter;
import nguyenhoanganhkhoa.com.models.Member;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class OurTeamScreen extends AppCompatActivity {

    ViewPager2 viewPagerOurTeam;
    LinearLayout layout_dots;
    List<Member> mList = AboutUsScreen.getMemberList();
    ImageView imvBackOurTeam;
    TextView dots[] = new TextView[mList.size()];

    ReusedConstraint reusedConstraint= new ReusedConstraint(OurTeamScreen.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_team_screen);

        linkView();
        initAdapter();
        addEvents();

    }

    private void addEvents() {
        reusedConstraint.prepareDots(this,mList.size(),layout_dots,dots,14);
        imvBackOurTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewPagerOurTeam.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                reusedConstraint.seletedIndicator(dots,position);
                super.onPageSelected(position);

            }
        });

        viewPagerOurTeam.setCurrentItem(MemberAdapter.memberNumber);
    }


    private void initAdapter() {
        try {
            DetailMemberAdapter detailMemberAdapter = new DetailMemberAdapter(mList);
            viewPagerOurTeam.setAdapter(detailMemberAdapter);
        }
        catch (Exception e){
            Log.d("Error", "Fail to load adapter in OurTeamScreen: " + e);
        }
    }



    private void linkView() {
        viewPagerOurTeam = findViewById(R.id.viewPagerOurTeam);
        layout_dots = findViewById(R.id.layout_dots);
        imvBackOurTeam= findViewById(R.id.imvBackOurTeam);

    }
}