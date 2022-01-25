package nguyenhoanganhkhoa.com.myapplication.home.transfer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.FriendAdapter;
import nguyenhoanganhkhoa.com.models.Friends;
import nguyenhoanganhkhoa.com.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransferHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransferHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransferHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransferHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransferHomeFragment newInstance(String param1, String param2) {
        TransferHomeFragment fragment = new TransferHomeFragment();
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

    RecyclerView rcvQuickAccess, rcvAllFriends;
    FriendAdapter adapter;
    TextView txtSeeAllFriend;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transfer_home, container, false);

        linkView(view);
        initAdapterQuickAccess();
        initAdapterSomeFriend();
        addEvents();
        return view;
    }

    private void initAdapterSomeFriend() {
        try {
            adapter= new FriendAdapter(getContext(), R.layout.item_friends_all, 1);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);

            rcvAllFriends.setLayoutManager(linearLayoutManager);
            adapter.setData(getListFriend());
            rcvAllFriends.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Log.d("Error", "Fail to load adapter in TransferHomeFragment: " + e);
        }
    }

    private void initAdapterQuickAccess() {
        try {
            adapter= new FriendAdapter(getContext(), R.layout.item_friends_quickacess);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);

            rcvQuickAccess.setLayoutManager(linearLayoutManager);
            rcvQuickAccess.setLayoutManager(new GridLayoutManager(getContext(), 4));

            adapter.setData(getListFriend());
            rcvQuickAccess.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Log.d("Error", "Fail to load adapter in TransferHomeFragment: " + e);
        }

    }

    public static List<Friends> getListFriend() {
        List<Friends> list = new ArrayList<>();
        list.add(new Friends(false,"Ý Heo","090855577",true));
        list.add(new Friends(true,"Anh Khoa","090855577",true));
        list.add(new Friends(false,"Tuyết Trinh","090855577",false));
        list.add(new Friends(true,"Gia Bảo","090855577",true));
        list.add(new Friends(false,"Chấn Khoa","090855577",false));
        list.add(new Friends(true,"Minh Thúy","090855577",true));
        list.add(new Friends(false,"Ngọc Anh","090855577",false));
        list.add(new Friends(true,"Phước Ngọc","090855577",true));
        list.add(new Friends(false,"Bin Love","090855577",false));

        return list;

    }

    private void linkView(View view) {
        rcvQuickAccess = view.findViewById(R.id.rcvQuickAccess);
        rcvAllFriends = view.findViewById(R.id.rcvAllFriends);
        txtSeeAllFriend = view.findViewById(R.id.txtSeeAllFriend);
    }

    private void addEvents() {
        txtSeeAllFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AllFriendScreen.class));
            }
        });

    }

}