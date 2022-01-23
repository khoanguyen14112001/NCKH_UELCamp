package nguyenhoanganhkhoa.com.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nguyenhoanganhkhoa.com.adapter.HomeButtonAdapter;
import nguyenhoanganhkhoa.com.adapter.TransAllAdapter;
import nguyenhoanganhkhoa.com.customdialog.CustomDialogFragmentHome;
import nguyenhoanganhkhoa.com.models.Date;
import nguyenhoanganhkhoa.com.models.HomeButtons;
import nguyenhoanganhkhoa.com.models.Transaction;
import nguyenhoanganhkhoa.com.myapplication.home.EditActivitiesScreen;
import nguyenhoanganhkhoa.com.myapplication.home.TopUpMainScreen;
import nguyenhoanganhkhoa.com.myapplication.home.AboutUsScreen;
import nguyenhoanganhkhoa.com.myapplication.home.AllNotificationScreen;
import nguyenhoanganhkhoa.com.myapplication.home.NewsScreen;
import nguyenhoanganhkhoa.com.myapplication.home.QRCodeScreen;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.ShowAllTransactionScreen;
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

    TransAllAdapter transAllAdapter;
    RecyclerView rcvHistoryTrans;
    TextView txtSeeAllTrans;
    ImageButton imbEditActivities;
    ImageView imvNoteBell;

    HomeButtonAdapter homeButtonAdapter;
    RecyclerView rcvButtonsHome;

    TextView txtNameDisplayUser, txtMoneyDisplay;
    ImageView imvInsideAvatar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        linkView(view);

        // Xử lý sự kiện
        getDataFromFirebase();
        initData();
        initButton();
        addEvents();
        return view;



    }

    private void initButton() {
        homeButtonAdapter = new HomeButtonAdapter(getContext(),R.layout.item_home_button);
        homeButtonAdapter.setData(getButtonList());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rcvButtonsHome.setLayoutManager(linearLayoutManager);

        rcvButtonsHome.setAdapter(homeButtonAdapter);
    }

    private List<HomeButtons> getButtonList() {
        List<HomeButtons> list = new ArrayList<>();
        list.add(new HomeButtons(R.drawable.ic_topup, AppUtil.top_up));
        list.add(new HomeButtons(R.drawable.ic_qrcode,AppUtil.qr_code));
        list.add(new HomeButtons(R.drawable.ic_news,AppUtil.news));
        list.add(new HomeButtons(R.drawable.ic_aboutus,AppUtil.about_us));
        return list;

    }

    private void linkView(View view) {
        rcvHistoryTrans = view.findViewById(R.id.rcvHistoryTrans);
        txtSeeAllTrans = view.findViewById(R.id.txtSeeAllTrans);
        imvNoteBell = view.findViewById(R.id.imvNoteBell);
        imbEditActivities = view.findViewById(R.id.imbEditActivities);
        rcvButtonsHome = view.findViewById(R.id.rcvButtonsHome);

        txtNameDisplayUser = view.findViewById(R.id.txtNameDisplayUser);
        imvInsideAvatar = view.findViewById(R.id.imvInsideAvatar);

        txtMoneyDisplay = view.findViewById(R.id.txtMoneyDisplay);
    }

    private void addEvents() {

        imbEditActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CustomDialogEditActivities dialog = new CustomDialogEditActivities(getActivity(),
//                        R.layout.custom_dialog_edit_activities,imbEditActivities);
//                dialog.cvEdit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view){
//                        Intent intent = new Intent(getContext(),EditActivitiesScreen.class);
//                        startActivity(intent);
//
//                    }
//                });
//                dialog.show();




                Context wrapper = new ContextThemeWrapper(getContext(),R.style.PopUpCustomStyle);
                PopupMenu popupMenu = new PopupMenu(wrapper,imbEditActivities);
                popupMenu.getMenuInflater().inflate(R.menu.menu_edit_activities,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Intent intent = new Intent(getContext(),EditActivitiesScreen.class);
                        startActivity(intent);
                        return true;
                    }
                });
                popupMenu.show();


            }
        });


        txtSeeAllTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShowAllTransactionScreen.class);
                startActivity(intent);
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


    private void initData() {
        try {
            transAllAdapter = new TransAllAdapter(getContext());
            transAllAdapter.setData(getTransactionList());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
            rcvHistoryTrans.setLayoutManager(linearLayoutManager);

            rcvHistoryTrans.setAdapter(transAllAdapter);
        }
        catch (Exception e){
            Log.d("Error", "Cannot load adapter in HomeFragment: " + e);
        }

    }


    private List<Transaction> getTransactionList() {
        List<Transaction> list = new ArrayList<>();
        list.add(new Transaction("Top up","20 Oct, 10:07 ","+50.000",R.drawable.ic_topup,R.drawable.ic_tickbutton));
        list.add(new Transaction("Parking payment","10 Oct, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        list.add(new Transaction("Parking payment","09 Oct, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        list.add(new Transaction("Parking payment","08 Oct, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        list.add(new Transaction("Parking payment","07 Oct, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        list.add(new Transaction("Parking payment","06 Oct, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        list.add(new Transaction("Parking payment","05 Oct, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_tickbutton));
        list.add(new Transaction("Parking payment","04 Oct, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_warning_red));
        list.add(new Transaction("Parking payment","03 Oct, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_warning_red));

        return list;
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