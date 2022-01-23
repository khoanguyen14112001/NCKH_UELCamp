package nguyenhoanganhkhoa.com.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import nguyenhoanganhkhoa.com.customdialog.CustomDialogTwoButton;
import nguyenhoanganhkhoa.com.myapplication.home.HelpCenterScreen;
import nguyenhoanganhkhoa.com.myapplication.home.QRCodeScreen;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.SecurityCenterScreen;
import nguyenhoanganhkhoa.com.myapplication.home.SettingScreen;
import nguyenhoanganhkhoa.com.myapplication.home.ShowAllTransactionScreen;
import nguyenhoanganhkhoa.com.myapplication.home.TopUpMainScreen;
import nguyenhoanganhkhoa.com.myapplication.login.LoginScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WalletFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WalletFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WalletFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WalletFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WalletFragment newInstance(String param1, String param2) {
        WalletFragment fragment = new WalletFragment();
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

    ImageButton imbTopUpWallet, imbQRCodeWallet, imbTransaction;
    LinearLayout lnHelpCenter,lnSetting,lnSignout ,lnSecurityCenter;

    TextView txtMoneyDisplay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        linkView(view);
        displayBalanceFromFirebase();
        addEventS();


        return view;
    }

    private void addEventS() {


        lnSecurityCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SecurityCenterScreen.class);
                startActivity(intent);
            }
        });

        lnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogTwoButton customDialogTwoButton = new CustomDialogTwoButton(getContext(),R.layout.custom_dialog_signout);
                customDialogTwoButton.btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), LoginScreen.class);
                        startActivity(intent);
                        customDialogTwoButton.dismiss();
                    }
                });
                customDialogTwoButton.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        customDialogTwoButton.dismiss();
                    }
                });
                customDialogTwoButton.show();


            }
        });

        lnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SettingScreen.class);
                startActivity(intent);
            }
        });
        lnHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HelpCenterScreen.class);
                startActivity(intent);
            }
        });

        imbTopUpWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TopUpMainScreen.class);
                startActivity(intent);
            }
        });

        imbQRCodeWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QRCodeScreen.class);
                startActivity(intent);
            }
        });
        imbTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowAllTransactionScreen.class);
                startActivity(intent);
            }
        });

    }
    private void linkView(View view) {
        imbTopUpWallet = view.findViewById(R.id.imbTopUpWallet);
        imbQRCodeWallet = view.findViewById(R.id.imbQRCodeWallet);
        imbTransaction = view.findViewById(R.id.imbTransaction);
        lnHelpCenter = view.findViewById(R.id.lnHelpCenter);
        lnSetting = view.findViewById(R.id.lnSetting);
        lnSignout= view.findViewById(R.id.lnSignout);
        lnSecurityCenter= view.findViewById(R.id.lnSecurityCenter);

        txtMoneyDisplay= view.findViewById(R.id.txtMoneyDisplay);

    }
    ReusedConstraint reusedConstraint = new ReusedConstraint(getContext());
    private void displayBalanceFromFirebase(){
        AppUtil.databaseReference.child(AppUtil.DATA_OBJECT).child(AppUtil.USERNAME_AFTER_LOGGIN)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            long balance = snapshot.child(AppUtil.FB_BALANCE).getValue(Long.class);
                            double balanceDisplay = Double.parseDouble(String.valueOf(balance));
                            txtMoneyDisplay.setText(reusedConstraint.formatCurrency(balanceDisplay));
                        }
                        catch (Exception e){
                            Log.d("Error", "Cannot load value to views in WalletFragment: " + e);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("Error", "Fail to load info in Wallet fragment" + error.toString());
                    }
                });
    }

}