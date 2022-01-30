package nguyenhoanganhkhoa.com.myapplication.home.transfer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class TransferResultScreen extends AppCompatActivity {
    ImageView imvBack;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_result_screen);
        linkView();
        addEvents();
        reusedConstraint.openNav(this);
    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.checkNavStatusComeBack(TransferResultScreen.this,TransferMainScreen.class);
            }
        });
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this,TransferMainScreen.class);
    }

    private void linkView() {
        imvBack = findViewById(R.id.imvBack);
    }
}