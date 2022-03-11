package nguyenhoanganhkhoa.com.myapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.HomeSLSpaceScreen;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.SLSpaceSplashScreen;

public class OnBoardingScreen extends AppCompatActivity {
    Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);
        linkView();
        autoMoveNextScreen();
    }
    private void linkView() {
        btnGetStarted = findViewById(R.id.btnGetStarted);

    }

    private void addEvents() {
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingScreen.this, LoginScreen.class));
            }
        });
    }
    Handler handler = new Handler();
    private void autoMoveNextScreen() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(OnBoardingScreen.this, LoginScreen.class));
                finish();
            }
        };

        handler.postDelayed(runnable,2000);
        addEvents();
    }


}