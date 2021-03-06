package nguyenhoanganhkhoa.com.myapplication.home.notification;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.NotificationAdapter;
import nguyenhoanganhkhoa.com.models.Notification;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllNoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllNoticeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllNoticeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllNoticeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllNoticeFragment newInstance(String param1, String param2) {
        AllNoticeFragment fragment = new AllNoticeFragment();
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

    RecyclerView rcvRecentNotice, rcvBeforeNotice;
    SearchView searchView;
    ReusedConstraint reusedConstraint = new ReusedConstraint(getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_notice, container, false);

        linkView(view);

        initAdapterRecent();
        initAdapterBefore();
        addEvents();




        return view;
    }

    private void addEvents() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_notification,new AllAllNoticeFragment());
                fragmentTransaction.addToBackStack(null).commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_notification,new AllAllNoticeFragment());
                fragmentTransaction.addToBackStack(null).commit();
                return false;
            }
        });
    }

    private void initAdapterBefore(){
        try {
            NotificationAdapter adapterBefore = new NotificationAdapter(getContext(),R.layout.item_notification_all_bold);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
            rcvBeforeNotice.setLayoutManager(linearLayoutManager);

            adapterBefore.setData(getListBeforeNotification());
            rcvBeforeNotice.setAdapter(adapterBefore);
        }
        catch (Exception e){
            Log.d("Error", "Fail to load AdapterBefore in AllNoticeFragment " + e);
        }



    }

    private void initAdapterRecent(){
        try {
            NotificationAdapter adapterRecent = new NotificationAdapter(getContext(),R.layout.item_notification_all_bold);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
            rcvRecentNotice.setLayoutManager(linearLayoutManager);

            adapterRecent.setData(getListRecentNotification());
            rcvRecentNotice.setAdapter(adapterRecent);

        }
        catch (Exception e){
            Log.d("Error", "Fail to load AdapterRecent in AllNoticeFragment " + e);
        }

    }



    private void linkView(View view) {
        rcvRecentNotice = view.findViewById(R.id.rcvRecentNotice);
        rcvBeforeNotice = view.findViewById(R.id.rcvBeforeNotice);
        searchView = requireActivity().findViewById(R.id.svNotification);

    }





    public static List<Notification> getListRecentNotification() {
        List<Notification> list = new ArrayList<>();
        list.add(new Notification(NotificationAdapter.NOTIFICATION_FRIEND,
                "T?????ng Vy has accepted your friend request",
                "13 Jan, 19:04",
                true,
                true,
                NotificationAdapter.NOTIFICATION_GENDER_MALE
        ));

        list.add(new Notification(NotificationAdapter.NOTIFICATION_PARKING,
                "Due to the Christmas holiday, the parking lot will not be open",
                "23 Dec, 18:35",
                true
        ));


        return list;
    }
    public static List<Notification> getListBeforeNotification() {
        List<Notification> list = new ArrayList<>();
        list.add(new Notification(NotificationAdapter.NOTIFICATION_FRIEND,
                "H???i Thanh sent you a friend request",
                "10 Nov, 21:00",
                false,
                true,
                NotificationAdapter.NOTIFICATION_GENDER_MALE
                ));

        list.add(new Notification(NotificationAdapter.NOTIFICATION_WALLET,
                "You currently do not have enough money to pay, please top up",
                "03 Nov, 17:05",
                false
        ));

        list.add(new Notification(NotificationAdapter.NOTIFICATION_TRANSFER,
                "You have successfully transfer 50,000 VND",
                "01 Nov, 18:45",
                false
        ));

        list.add(new Notification(NotificationAdapter.NOTIFICATION_PROMOTION,
                "You are given a voucher for 20% off when paying for any service",
                "23 Dec, 18:35",
                true
        ));

        list.add(new Notification(NotificationAdapter.NOTIFICATION_WALLET,
                "You currently do not have enough money to pay, please top up",
                "03 Nov, 17:05",
                false
        ));

        list.add(new Notification(NotificationAdapter.NOTIFICATION_QUANCAFE,
                "Due to the Christmas holiday, the SLSpace will not be open",
                "23 Dec, 18:35",
                true
        ));


        list.add(new Notification(NotificationAdapter.NOTIFICATION_FRIEND,
                "H???i Y??n has accepted your friend request",
                "03 Nov, 17:05",
                false,
                true,
                NotificationAdapter.NOTIFICATION_GENDER_MALE
        ));

        list.add(new Notification(NotificationAdapter.NOTIFICATION_CANTEEN,
                "Canteen is temporarily closed on 24 Dec",
                "23 Dec, 18:35",
                true
        ));


        list.add(new Notification(NotificationAdapter.NOTIFICATION_PARKING,
                "Due to the Christmas holiday, the parking lot will not be open",
                "01 Nov, 18:45",
                false
        ));


        return list;
    }
}