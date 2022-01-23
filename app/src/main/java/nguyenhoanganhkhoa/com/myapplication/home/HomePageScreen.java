package nguyenhoanganhkhoa.com.myapplication.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import nguyenhoanganhkhoa.com.adapter.ViewPagerAdapter;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.login.LoginScreen;

public class HomePageScreen extends AppCompatActivity {
    BottomNavigationView navBottom;
    ViewPager2 vpagerHomePage;
    private FirebaseAuth mAuth;

    private void linkView() {
        navBottom = findViewById(R.id.navBottom);
        vpagerHomePage = findViewById(R.id.vpagerHomepage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_screen);

        linkView();

        addEvent();
    }

    private void addEvent() {
        setUpViewPager();
        navBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.act_home:
                        vpagerHomePage.setCurrentItem(0);
                        break;
                    case R.id.act_history:
                        vpagerHomePage.setCurrentItem(1);
                        break;
                    case R.id.act_wallet:
                        vpagerHomePage.setCurrentItem(2);
                        break;
                    case R.id.act_account:
                        vpagerHomePage.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        vpagerHomePage.setAdapter(viewPagerAdapter);
        vpagerHomePage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        navBottom.getMenu().findItem(R.id.act_home).setChecked(true);
                        break;
                    case 1:
                        navBottom.getMenu().findItem(R.id.act_history).setChecked(true);
                        break;
                    case 2:
                        navBottom.getMenu().findItem(R.id.act_wallet).setChecked(true);
                        break;
                    case 3:
                        navBottom.getMenu().findItem(R.id.act_account).setChecked(true);
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(this,LoginScreen.class));
        }
    }
}