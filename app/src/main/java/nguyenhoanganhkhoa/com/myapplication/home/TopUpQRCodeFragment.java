package nguyenhoanganhkhoa.com.myapplication.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopUpQRCodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopUpQRCodeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TopUpQRCodeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopUpQRCodeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopUpQRCodeFragment newInstance(String param1, String param2) {
        TopUpQRCodeFragment fragment = new TopUpQRCodeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView txtTimeMomo,txtAmount;
    Button btnSaveQRCode;
    ReusedConstraint reusedConstraint = new ReusedConstraint(getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_up_qr_code, container, false);
        linkView(view);
        addEvents();
        return view;
    }

    private void addEvents() {
        reusedConstraint.addTimer(txtTimeMomo,240000);
        txtAmount.setText(reusedConstraint.formatCurrency(AppUtil.AMOUNT_SEND));
        btnSaveQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBalanceToFirebase();
                startActivity(new Intent(getContext(),HomePageScreen.class));
            }
        });
    }

    private void updateBalanceToFirebase() {
        DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference("account")
                .child(AppUtil.DATA_OBJECT).child(AppUtil.USERNAME_AFTER_LOGGIN);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            long balance = snapshot.child(AppUtil.FB_BALANCE).getValue(Long.class);
                            double currentBalance = Double.parseDouble(String.valueOf(balance));
                            double plusBalance = Double.parseDouble(AppUtil.AMOUNT_SEND);
                            double newBalance = currentBalance + plusBalance;
                            databaseReference.child(AppUtil.FB_BALANCE).setValue(newBalance);
                            Toast.makeText(getContext(),"Your balance successfully updated!",Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e){
                            Log.d("Error", "Fail to set values to views in TopUpQRFragment: " + e);
                            Toast.makeText(getContext(), "Something was wrong when top up your balance", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("Error", "Fail to upload balance" + error.toString());
                    }
                });
    }

    private void linkView(View view) {
        txtTimeMomo = view.findViewById(R.id.txtTimeMomo);
        txtAmount = view.findViewById(R.id.txtAmount);
        btnSaveQRCode = view.findViewById(R.id.btnSaveQRCode);
    }
}