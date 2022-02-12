package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.VoucherAdapter;
import nguyenhoanganhkhoa.com.models.Voucher;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class VoucherSLSpaceScreen extends AppCompatActivity {

    RecyclerView rcvVoucherAvailable;
    VoucherAdapter adapter;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    private void linkView() {
        rcvVoucherAvailable = findViewById(R.id.rcvVoucherAvailable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_slspace_screen);

        linkView();
        initAdapter();
        addEvents();
    }

    private void addEvents() {
        reusedConstraint.setActionComeBack(this);
        reusedConstraint.openNav(this);
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void initAdapter() {
        adapter = new VoucherAdapter(this);
        adapter.setData(getListVoucher());
        rcvVoucherAvailable.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvVoucherAvailable.setAdapter(adapter);

    }

    private List<Voucher> getListVoucher() {
        List<Voucher> list = new ArrayList<>();

        list.add(new Voucher(VoucherAdapter.VOUCHER_FREE_SHIP,"All forms of payment","",
                20,"23 Feb, 2022"));
        list.add(new Voucher(VoucherAdapter.VOUCHER_FREE_SHIP,"All forms of payment","",
                15,"27 Feb, 2022"));
        list.add(new Voucher(VoucherAdapter.VOUCHER_UEL_CAMP,"Discount 10%","Minimum order 100k",
                20,"28 Feb, 2022"));
        list.add(new Voucher(VoucherAdapter.VOUCHER_UEL_CAMP,"Discount 5K","Minimum order 50k",
                20,"02 Mar, 2022", false));


        return list;
    }
}