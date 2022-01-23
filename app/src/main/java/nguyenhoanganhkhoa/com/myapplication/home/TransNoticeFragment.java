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

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DialogNotificationAdapter;
import nguyenhoanganhkhoa.com.models.Notification;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransNoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransNoticeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransNoticeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransNoticeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransNoticeFragment newInstance(String param1, String param2) {
        TransNoticeFragment fragment = new TransNoticeFragment();
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
    RecyclerView rcvDisplayNotifications;
    DialogNotificationAdapter adapter;
    SearchView searchView;
    ReusedConstraint reusedConstraint = new ReusedConstraint(getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_from_admin_notice, container, false);
        linkView(view);
        initAdapter();
        reusedConstraint.addSearchForNotification(searchView,adapter);

        return view;
    }
    private void linkView(View view) {
        rcvDisplayNotifications = view.findViewById(R.id.rcvDisplayNotifications);
        searchView = requireActivity().findViewById(R.id.svNotification);
    }


    private void initAdapter() {
        try {
            adapter = new DialogNotificationAdapter(getContext(),R.layout.item_notification_all_bold);
            rcvDisplayNotifications.setAdapter(adapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
            rcvDisplayNotifications.setLayoutManager(linearLayoutManager);

            adapter.setData(getListNotify());
            rcvDisplayNotifications.setAdapter(adapter);
        }

        catch (Exception e) {
            Log.d("Error", "Fail to set adapter in TransNoticeFragment: " + e);
        }

    }

    private List<Notification> getListNotify() {
        List<Notification> list = new ArrayList<>();
        list.add(new Notification(R.drawable.img_nomoney_notice,"You currently do not have enough " +
                "money to pay, please top up","01 Nov, 18:45"));
        list.add(new Notification(R.drawable.ic_img_nomoney_notice_new,"You currently do not have enough " +
                "money to pay, please top up","01 Nov, 18:45"));
        list.add(new Notification(R.drawable.img_nomoney_notice,"You currently do not have enough " +
                "money to pay, please top up","01 Nov, 18:45"));
        list.add(new Notification(R.drawable.ic_img_nomoney_notice_new,"You currently do not have enough " +
                "money to pay, please top up","01 Nov, 18:45"));
        return list;
    }

}