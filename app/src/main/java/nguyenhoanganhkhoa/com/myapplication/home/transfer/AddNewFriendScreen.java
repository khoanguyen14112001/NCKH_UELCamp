package nguyenhoanganhkhoa.com.myapplication.home.transfer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import nguyenhoanganhkhoa.com.adapter.FriendAdapter;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class AddNewFriendScreen extends AppCompatActivity {

    RecyclerView rcvAddNewFriend;
    FriendAdapter adapter;
    ImageView imvBack;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_friend_screen);
        
        linkView();
        initAdapter();
        addEvents();
        reusedConstraint.openNav(this);
    }

    private void linkView() {
        rcvAddNewFriend = findViewById(R.id.rcvAddNewFriend);
        imvBack = findViewById(R.id.imvBack);
    }

    private void initAdapter() {
        adapter = new FriendAdapter(this,R.layout.item_friends_add);
        adapter.setData(TransferHomeFragment.getListFriend());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvAddNewFriend.setLayoutManager(linearLayoutManager);

        rcvAddNewFriend.setAdapter(adapter);
    }


    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reusedConstraint.checkNavStatusComeBack(AddNewFriendScreen.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(AddNewFriendScreen.this);
    }
}