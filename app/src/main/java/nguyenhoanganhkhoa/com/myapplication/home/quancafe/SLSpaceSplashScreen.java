package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class SLSpaceSplashScreen extends AppCompatActivity {

    Button btnGo;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slspace_main_screen);

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
                startActivity(new Intent(SLSpaceSplashScreen.this, HomeSLSpaceScreen.class));
                finish();
            }
        };

        handler.postDelayed(runnable,3000);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacksAndMessages(null);
                startActivity(new Intent(SLSpaceSplashScreen.this, HomeSLSpaceScreen.class));
                finish();
            }
        });


    }

    private void linkView() {
        btnGo = findViewById(R.id.btnGo);
    }
}