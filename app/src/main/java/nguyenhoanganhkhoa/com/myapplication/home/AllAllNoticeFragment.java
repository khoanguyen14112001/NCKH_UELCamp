package nguyenhoanganhkhoa.com.myapplication.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DialogNotificationAdapter;
import nguyenhoanganhkhoa.com.models.Notification;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class AllAllNoticeFragment extends Fragment {
    RecyclerView rcvDisplayNotifications;
    DialogNotificationAdapter adapter;
    SearchView searchView;
    ReusedConstraint reusedConstraint = new ReusedConstraint(getContext());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_all_all_notice,container,false);
       linkView(view);
       initAdapter();
       reusedConstraint.addSearchForNotification(searchView,adapter);

       return view;
    }

    private void linkView(View view) {
        rcvDisplayNotifications = view.findViewById(R.id.rcvDisplayNotifications);
        searchView = requireActivity().findViewById(R.id.svNotification);
    }


    private void initAdapter(){
        adapter = new DialogNotificationAdapter(getContext(),R.layout.item_notification_all_bold);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcvDisplayNotifications.setLayoutManager(linearLayoutManager);

        adapter.setData(getListAllAllNotification());
        rcvDisplayNotifications.setAdapter(adapter);

        //reusedConstraint.addSearchForNotification(searchView,adapterBefore);

    }

    private List<Notification> getListAllAllNotification() {
        List<Notification> list = new ArrayList<>();

        list.addAll(AllNoticeFragment.getListRecentNotification());
        list.addAll(AllNoticeFragment.getListBeforeNotification());
        return list;
    }

}
