package nguyenhoanganhkhoa.com.myapplication.home.SLSpace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class ChooseAddressScreen extends AppCompatActivity {
    Button btnTakeAway, btnAtStore;

    ReusedConstraint reusedConstraint = new ReusedConstraint();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_address_screen);

        linkView();
        addEvents();

    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void addEvents() {
        reusedConstraint.setActionComeBack(this);
        reusedConstraint.openNav(this);
        btnTakeAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseAddressScreen.this,TakeAwayAddressScreen.class));
            }
        });

        btnAtStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void linkView() {
        btnAtStore = findViewById(R.id.btnAtStore);
        btnTakeAway = findViewById(R.id.btnTakeAway);
    }
}