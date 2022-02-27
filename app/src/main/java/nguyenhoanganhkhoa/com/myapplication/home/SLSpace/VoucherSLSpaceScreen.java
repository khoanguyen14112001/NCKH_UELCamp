package nguyenhoanganhkhoa.com.myapplication.home.SLSpace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.VoucherAdapter;
import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogInputVoucher;
import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogOneButtonNew;
import nguyenhoanganhkhoa.com.models.Voucher;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class VoucherSLSpaceScreen extends AppCompatActivity {

    RecyclerView rcvVoucherAvailable, rcvVoucherApply;
    VoucherAdapter adapter, adapter2;
    ImageView imvAddVoucher;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);
    ConstraintLayout layout_no_discount_apply;
    LinearLayout layout_apply_discount_success;
    private void linkView() {
        rcvVoucherAvailable = findViewById(R.id.rcvVoucherAvailable);
        rcvVoucherApply = findViewById(R.id.rcvVoucherApply);
        imvAddVoucher = findViewById(R.id.imvAddVoucher);
        layout_no_discount_apply = findViewById(R.id.layout_no_discount_apply);
        layout_apply_discount_success = findViewById(R.id.layout_apply_discount_success);
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
        imvAddVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogInputVoucher dialogInputVoucher = new CustomDialogInputVoucher(VoucherSLSpaceScreen.this);
                CustomDialogOneButtonNew dialogOneButtonNew = new CustomDialogOneButtonNew(VoucherSLSpaceScreen.this);

                dialogInputVoucher.btnApply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String voucher = dialogInputVoucher.edtVoucherInput.getText().toString().trim();

                        if(voucher.equals("1234")){
                            dialogInputVoucher.dismiss();
                            CustomDialogOneButtonNew dialogOneButtonNew = new CustomDialogOneButtonNew(VoucherSLSpaceScreen.this);
                            dialogOneButtonNew.txtContentDialog.setText("You have successfully applied the voucher.");
                            dialogOneButtonNew.txtTitleDialog.setText("Applied successfully");
                            dialogOneButtonNew.btnOK.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogOneButtonNew.dismiss();
                                }
                            });

                            adapter2 = new VoucherAdapter(VoucherSLSpaceScreen.this);
                            adapter2.setData(getListVoucher());
                            adapter2.setSizeList(1);
                            rcvVoucherApply.setLayoutManager(new LinearLayoutManager(VoucherSLSpaceScreen.this,LinearLayoutManager.VERTICAL,false));
                            rcvVoucherApply.setAdapter(adapter2);
                            layout_apply_discount_success.setVisibility(View.VISIBLE);
                            layout_no_discount_apply.setVisibility(View.GONE);
                            dialogInputVoucher.dismiss();


                            dialogOneButtonNew.show();

                        }
                        else{
                            if(voucher.isEmpty()){
                                Toast.makeText(VoucherSLSpaceScreen.this, "Please input voucher!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                dialogOneButtonNew.txtContentDialog.setText("You have applied the voucher unsuccessfully. Try again.");
                                dialogOneButtonNew.txtTitleDialog.setText("Applied failed");
                                dialogOneButtonNew.btnOK.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialogOneButtonNew.dismiss();
                                    }
                                });

                                dialogInputVoucher.dismiss();
                                dialogOneButtonNew.show();
                            }
                        }

                    }
                });

                dialogInputVoucher.show();

            }
        });
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