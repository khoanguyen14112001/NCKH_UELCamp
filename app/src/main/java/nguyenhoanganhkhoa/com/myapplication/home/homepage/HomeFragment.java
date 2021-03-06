package nguyenhoanganhkhoa.com.myapplication.home.homepage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogFragmentHome;
import nguyenhoanganhkhoa.com.myapplication.home.canteen.CanteenSplashScreen;
import nguyenhoanganhkhoa.com.myapplication.home.notification.AllNotificationScreen;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.parkinglot.ParkingLotHomeScreen;
import nguyenhoanganhkhoa.com.myapplication.home.SLSpace.SLSpaceSplashScreen;
import nguyenhoanganhkhoa.com.myapplication.home.parkinglot.ParkingSplashScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    ImageView imvNoteBell;
    TextView txtNameDisplayUser, txtMoneyDisplay;
    ImageView imvInsideAvatar;

    CardView layout_parking, layout_canteen, layout_quancafe;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        linkView(view);

        // X??? l?? s??? ki???n
        getDataFromFirebase();
        addEvents();
        return view;



    }


    private void linkView(View view) {
        imvNoteBell = view.findViewById(R.id.imvNoteBell);

        txtNameDisplayUser = view.findViewById(R.id.txtNameDisplayUser);
        imvInsideAvatar = view.findViewById(R.id.imvInsideAvatar);

        txtMoneyDisplay = view.findViewById(R.id.txtMoneyDisplay);

        layout_parking = view.findViewById(R.id.layout_parking);
        layout_canteen = view.findViewById(R.id.layout_canteen);
        layout_quancafe = view.findViewById(R.id.layout_quancafe);
    }

    private void addEvents() {
        layout_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ParkingSplashScreen.class));
            }
        });
        layout_quancafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SLSpaceSplashScreen.class));

            }
        });

        layout_canteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CanteenSplashScreen.class));
            }
        });


        imvNoteBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogFragmentHome customDialogFragment = new CustomDialogFragmentHome(getActivity(),R.layout.custom_dialog_notification);
                customDialogFragment.btnSeeAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), AllNotificationScreen.class);
                        startActivity(intent);
                        customDialogFragment.dismiss();
                    }
                });
                customDialogFragment.show();
            }
        });
    }

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
                                imvInsideAvatar.setImageResource(idAva);
                            }
                            else{
                                if(getContext()!=null){
                                    Glide.with(getContext()).load(uri).into(imvInsideAvatar);
                                }
                            }
                            txtNameDisplayUser.setText(fullname);
                            txtMoneyDisplay.setText(reusedConstraint.formatCurrency(balanceDisplay));
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

    ReusedConstraint reusedConstraint = new ReusedConstraint(getContext());

}