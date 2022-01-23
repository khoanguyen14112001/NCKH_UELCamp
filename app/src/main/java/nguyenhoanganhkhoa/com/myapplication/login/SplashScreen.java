package nguyenhoanganhkhoa.com.myapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nguyenhoanganhkhoa.com.myapplication.R;

public class SplashScreen extends AppCompatActivity {
    Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        linkView();
        addEvents();
    }
    private void linkView() {
        btnGetStarted = findViewById(R.id.btnGetStarted);

    }

    private void addEvents() {
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreen.this, LoginScreen.class));
            }
        });
    }

}