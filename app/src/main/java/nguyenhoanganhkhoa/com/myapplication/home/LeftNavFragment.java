package nguyenhoanganhkhoa.com.myapplication.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import nguyenhoanganhkhoa.com.customdialog.CustomDialogTwoButton;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.parkinglot.ParkingLotHomeScreen;
import nguyenhoanganhkhoa.com.myapplication.home.setting.SettingScreen;
import nguyenhoanganhkhoa.com.myapplication.home.topup.TopUpMainScreen;
import nguyenhoanganhkhoa.com.myapplication.home.transfer.TransferMainScreen;
import nguyenhoanganhkhoa.com.myapplication.login.LoginScreen;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeftNavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftNavFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LeftNavFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeftNavFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeftNavFragment newInstance(String param1, String param2) {
        LeftNavFragment fragment = new LeftNavFragment();
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
    LinearLayout lnOpenHome, lnOpenParkingLot , lnOpenTopUp, lnOpenTransfer, lnOpenQRCode, lnOpenSetting, lnSignoutNav;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_left_nav, container, false);
        linkView(view);
        addEvents();
        return view;
    }

    private void addEvents() {
        DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawerLayout);

        lnOpenHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HomePageScreen.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });

        lnOpenParkingLot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ParkingLotHomeScreen.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });

        lnOpenTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TopUpMainScreen.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });

        lnOpenTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TransferMainScreen.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });

        lnOpenQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QRCodeScreen.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.END);

            }
        });

        lnOpenSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SettingScreen.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });


        lnSignoutNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogTwoButton dialog = new CustomDialogTwoButton(requireContext(), R.layout.custom_dialog_signout);
                dialog.btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), LoginScreen.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.END);
                    }
                });
                dialog.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    private void linkView(View view) {
        lnOpenHome = view.findViewById(R.id.lnOpenHome);

        lnOpenParkingLot = view.findViewById(R.id.lnOpenParkingLot);

        lnOpenTopUp = view.findViewById(R.id.lnOpenTopUp);
        lnOpenTransfer = view.findViewById(R.id.lnOpenTransfer);
        lnOpenQRCode = view.findViewById(R.id.lnOpenQRCode);

        lnOpenSetting = view.findViewById(R.id.lnOpenSetting);
        lnSignoutNav = view.findViewById(R.id.lnSignoutNav);
    }
}