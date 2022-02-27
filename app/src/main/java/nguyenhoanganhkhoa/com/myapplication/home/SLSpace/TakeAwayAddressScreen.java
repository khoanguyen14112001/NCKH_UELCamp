package nguyenhoanganhkhoa.com.myapplication.home.SLSpace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.AddressAdapter;
import nguyenhoanganhkhoa.com.models.Address;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;


public class TakeAwayAddressScreen extends AppCompatActivity {

    RecyclerView rcvAddress;
    AddressAdapter adapter = new AddressAdapter(this);
    Button btnAddNewAddress;
    ReusedConstraint reusedConstraint = new ReusedConstraint();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_away_address_screen);

        linkView();
        initAdapter();
        addEvents();
    }

    private void initAdapter() {
        adapter.setData(getListAddress());
        rcvAddress.setAdapter(adapter);
        rcvAddress.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    private List<Address> getListAddress() {
        List<Address> list = new ArrayList<>();

        list.add(new Address("TRƯƠNG HOÀNG Ý",
                "Room A.707 - Court A, University of Economics and Law",
                "0943782999"));

        list.add(new Address("VÕ THỊ TUYẾT TRINH",
                "2nd Floor - Library B2, University of Economics and Law",
                "0353748980"));

        list.add(new Address("NGUYỄN HOÀNG ANH KHOA",
                "Room B1.402 - Court B1, University of Economics and Law",
                "0935348905"));

        list.add(new Address("TRỊNH CHẤN KHOA",
                "Room A.301 - Court A, University of Economics and Law",
                "0974862900"));

        return list;
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        reusedConstraint.setActionComeBack(this);
        btnAddNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TakeAwayAddressScreen.this, AddNewAddressScreen.class));
            }
        });
    }

    private void linkView() {
        rcvAddress = findViewById(R.id.rcvAddress);
        btnAddNewAddress = findViewById(R.id.btnAddNewAddress);
    }
}