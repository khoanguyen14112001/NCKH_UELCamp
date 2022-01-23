package nguyenhoanganhkhoa.com.myapplication.home;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DialogNotificationAdapter;
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
    ReusedConstraint reusedConstraint = new ReusedConstraint(getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_notice, container, false);

        linkView(view);

        initAdapterRecent();
        initAdapterBefore();



        return view;
    }

    private void initAdapterBefore(){
        try {
            DialogNotificationAdapter adapterBefore = new DialogNotificationAdapter(getContext(),R.layout.item_notification_all_bold);

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
            DialogNotificationAdapter adapterRecent = new DialogNotificationAdapter(getContext(),R.layout.item_notification_all_bold);

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

    }



    public static List<Notification> getListRecentNotification() {
        List<Notification> list = new ArrayList<>();
        list.add(new Notification(R.drawable.img_newnotice,"The parking lot will be under " +
                "maintenance from 14, Jan to 20, Jan","13 Jan, 19:04"));
        list.add(new Notification(R.drawable.img_newnotice,"Due to the Christmas holiday, " +
                "the parking lot will not be open","23 Dec, 18:35"));
        list.add(new Notification(R.drawable.img_notice,"Due to the Christmas holiday, " +
                "the parking lot will not be open","23 Dec, 18:35"));

        return list;
    }
    public static List<Notification> getListBeforeNotification() {
        List<Notification> list = new ArrayList<>();
        list.add(new Notification(R.drawable.img_newnotice,"The parking lot will be under " +
                "maintenance from 11 Nov to 14 Nov","13 Jan, 19:04"));
        list.add(new Notification(R.drawable.img_newnotice,"You currently do not have enough " +
                "money to pay, please top up","03 Nov, 17:05"));
        list.add(new Notification(R.drawable.img_newnotice,"You currently do not have enough " +
                "money to pay, please top up","01 Nov, 18:45"));
        list.add(new Notification(R.drawable.ic_img_nomoney_notice_new,"You currently do not have enough " +
                "money to pay, please top up","01 Nov, 18:45"));
        list.add(new Notification(R.drawable.ic_img_nomoney_notice_new,"You currently do not have enough " +
                "money to pay, please top up","01 Nov, 18:45"));
        list.add(new Notification(R.drawable.ic_img_nomoney_notice_new,"You currently do not have enough " +
                "money to pay, please top up","01 Nov, 18:45"));
        list.add(new Notification(R.drawable.img_nomoney_notice,"You currently do not have enough " +
                "money to pay, please top up","01 Nov, 18:45"));
        list.add(new Notification(R.drawable.img_nomoney_notice,"You currently do not have enough " +
                "money to pay, please top up","01 Nov, 18:45"));
        list.add(new Notification(R.drawable.img_nomoney_notice,"You currently do not have enough " +
                "money to pay, please top up","01 Nov, 18:45"));
        list.add(new Notification(R.drawable.img_nomoney_notice,"You currently do not have enough " +
                "money to pay, please top up","01 Nov, 18:45"));
        return list;
    }
}