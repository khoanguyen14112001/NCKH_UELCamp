package nguyenhoanganhkhoa.com.myapplication.home.transfer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import nguyenhoanganhkhoa.com.myapplication.R;

public class TransferResultScreen extends AppCompatActivity {
    ImageView imvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_result_screen);
        linkView();
        addEvents();    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void linkView() {
        imvBack = findViewById(R.id.imvBack);
    }
}