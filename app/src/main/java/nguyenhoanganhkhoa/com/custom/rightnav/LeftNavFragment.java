package nguyenhoanganhkhoa.com.custom.rightnav;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogTwoButton;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.QRCodeScreen;
import nguyenhoanganhkhoa.com.myapplication.home.aboutus.AboutUsScreen;
import nguyenhoanganhkhoa.com.myapplication.home.canteen.CanteenSplashScreen;
import nguyenhoanganhkhoa.com.myapplication.home.homepage.HomePageScreen;
import nguyenhoanganhkhoa.com.myapplication.home.parkinglot.ParkingLotHomeScreen;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.SLSpaceSplashScreen;
import nguyenhoanganhkhoa.com.myapplication.home.parkinglot.ParkingSplashScreen;
import nguyenhoanganhkhoa.com.myapplication.home.setting.SettingScreen;
import nguyenhoanganhkhoa.com.myapplication.home.topup.TopUpMainScreen;
import nguyenhoanganhkhoa.com.myapplication.home.transfer.TransferMainScreen;
import nguyenhoanganhkhoa.com.myapplication.login.LoginScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

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
    LinearLayout lnOpenTopUp, lnOpenTransfer, lnOpenQRCode, lnOpenSetting, lnSignoutNav;
    ConstraintLayout lnOpenQuanCafe, lnOpenCanteen, lnOpenParkingLot, lnOpenHome;
    TextView txtTransfer, txtParkingLot, txtTopUp, txtQuanCafe, txtSetting, txtQRCode,txtCanteen;
    DrawerLayout drawerLayout;

    TextView txtRealTextQuanCafe, txtName;
    ImageView imvAvatar, imvOpenInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_left_nav, container, false);
        linkView(view);
        setText();
        getDataFromFirebase();
        addEvents();
        return view;
    }

    private void openScreen(TextView txt, Class activity){

        try {
            TextView txtHeader = requireActivity().findViewById(R.id.txtHeader);
            if (!txt.getText().toString().toLowerCase().trim().equals(txtHeader.getText().toString().toLowerCase().trim())) {
                Intent intent = new Intent(getContext(), activity);
                startActivity(intent);
            }
        }
        catch (Exception e){
            Intent intent = new Intent(getContext(), activity);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    private void addEvents() {
        imvOpenInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AboutUsScreen.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });

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
               openScreen(txtParkingLot, ParkingSplashScreen.class);
            }
        });

        lnOpenQuanCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScreen(txtQuanCafe, SLSpaceSplashScreen.class);
            }
        });

        lnOpenTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScreen(txtTopUp,TopUpMainScreen.class);
            }
        });

        lnOpenTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScreen(txtTransfer,TransferMainScreen.class);
            }
        });

        lnOpenQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScreen(txtQRCode,QRCodeScreen.class);

            }
        });

        lnOpenSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScreen(txtSetting,SettingScreen.class);
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

        lnOpenCanteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScreen(txtCanteen, CanteenSplashScreen.class);
            }
        });
    }
    ReusedConstraint reusedConstraint = new ReusedConstraint(getContext());
    private void getDataFromFirebase(){
        AppUtil.databaseReference.child(AppUtil.DATA_OBJECT).child(AppUtil.USERNAME_AFTER_LOGGIN)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            String fullname = snapshot.child(AppUtil.FB_FULLNAME).getValue(String.class);
                            String uri = snapshot.child(AppUtil.FB_IMAGES_BITMAP).getValue(String.class);
                            long balance = snapshot.child(AppUtil.FB_BALANCE).getValue(Long.class);
                            double balanceDisplay = Double.parseDouble(String.valueOf(balance));

                            if(uri.isEmpty()|uri.equals("Null")){
                                long avatar = snapshot.child(AppUtil.FB_AVATAR).getValue(Long.class);
                                int idAva = Integer.parseInt(String.valueOf(avatar));
                                imvAvatar.setImageResource(idAva);
                            }
                            else{
                                if(getContext()!=null){
                                    Glide.with(getContext()).load(uri).into(imvAvatar);
                                }
                            }
                            txtName.setText(fullname);
                        }
                        catch (Exception e){
                            Log.d("Error", "Cannot parse value in HomeFragment: " + e);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("Error", "Fail to load info in Home fragment" + error.toString());
                    }
                });
    }


    private int getWindowWidth(Activity activity) {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void setText(){
        if(getWindowWidth(requireActivity())<850){
            txtRealTextQuanCafe.setText(R.string.uel_startup_language_space);
        }
    }

    private void linkView(View view) {
        lnOpenHome = view.findViewById(R.id.lnOpenHome);
        drawerLayout = requireActivity().findViewById(R.id.drawerLayout);

        lnOpenParkingLot = view.findViewById(R.id.lnOpenParkingLot);
        txtParkingLot = view.findViewById(R.id.txtParkingLot);

        lnOpenTopUp = view.findViewById(R.id.lnOpenTopUp);
        txtTopUp = view.findViewById(R.id.txtTopUp);

        lnOpenTransfer = view.findViewById(R.id.lnOpenTransfer);
        txtTransfer = view.findViewById(R.id.txtTransfer);

        lnOpenQRCode = view.findViewById(R.id.lnOpenQRCode);
        txtQRCode = view.findViewById(R.id.txtQRCode);

        lnOpenSetting = view.findViewById(R.id.lnOpenSetting);
        txtSetting = view.findViewById(R.id.txtSetting);

        lnSignoutNav = view.findViewById(R.id.lnSignoutNav);

        lnOpenQuanCafe=view.findViewById(R.id.lnOpenQuanCafe);
        txtQuanCafe=view.findViewById(R.id.txtQuanCafe);

        lnOpenCanteen = view.findViewById(R.id.lnOpenCanteen);
        txtCanteen = view.findViewById(R.id.txtCanteen);

        txtRealTextQuanCafe = view.findViewById(R.id.txtRealTextQuanCafe);

        imvAvatar = view.findViewById(R.id.imvAvatar);
        txtName = view.findViewById(R.id.txtName);
        imvOpenInfo = view.findViewById(R.id.imvOpenInfo);

    }
}