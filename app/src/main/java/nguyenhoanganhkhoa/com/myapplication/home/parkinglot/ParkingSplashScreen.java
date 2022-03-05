package nguyenhoanganhkhoa.com.myapplication.home.parkinglot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.canteen.CanteenSplashScreen;
import nguyenhoanganhkhoa.com.myapplication.home.canteen.HomeCanteenScreen;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class ParkingSplashScreen extends AppCompatActivity {
    Button btnGo;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_splash_screen);
        linkView();
        autoMoveNextScreen();
    }

    Handler handler = new Handler();


    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
        finish();
    }

    private void autoMoveNextScreen() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ParkingSplashScreen.this, ParkingLotHomeScreen.class));
                finish();
            }
        };

        handler.postDelayed(runnable,3000);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacksAndMessages(null);
                startActivity(new Intent(ParkingSplashScreen.this, ParkingLotHomeScreen.class));
                finish();
            }
        });
    }

    private void linkView() {
        btnGo = findViewById(R.id.btnGo);
    }
}