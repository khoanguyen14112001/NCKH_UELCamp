package nguyenhoanganhkhoa.com.myapplication.home.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DialogNotificationAdapter;
import nguyenhoanganhkhoa.com.models.Notification;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.transfer.TransferMoneyDetailFragment;
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
       setCallBackAdapter();
       reusedConstraint.addSearchForNotification(searchView,adapter);

       return view;
    }

    private void linkView(View view) {
        rcvDisplayNotifications = view.findViewById(R.id.rcvDisplayNotifications);
        searchView = requireActivity().findViewById(R.id.svNotification);

    }

    private void setCallBackAdapter(){
        adapter.setCallBack(new DialogNotificationAdapter.MyCallBack() {
            @Override
            public void changeFragment() {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_notification,new AllNoticeFragment());
                fragmentTransaction.addToBackStack(null).commit();
            }

            @Override
            public void changeToNoResultFragment() {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layout_notification,new NoResultNotificationFragment());
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
    }


    private void initAdapter(){
        adapter = new DialogNotificationAdapter(getContext(),R.layout.item_notification_all_bold);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcvDisplayNotifications.setLayoutManager(linearLayoutManager);

        adapter.setData(getListAllAllNotification());
        rcvDisplayNotifications.setAdapter(adapter);

        //reusedConstraint.addSearchForNotification(searchView,adapterBefore);

    }

    public static List<Notification> getListAllAllNotification() {
        List<Notification> list = new ArrayList<>();

        list.addAll(AllNoticeFragment.getListRecentNotification());
        list.addAll(AllNoticeFragment.getListBeforeNotification());
        return list;
    }

}
