package nguyenhoanganhkhoa.com.myapplication.home.transfer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import nguyenhoanganhkhoa.com.adapter.FriendAdapter;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class AllFriendScreen extends AppCompatActivity {

    ImageView imvBackAllFriends;
    RecyclerView rcvFriends;
    Button btnAddFriend;
    FriendAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_friend_screen);

        linkView();
        initAdapter();
        addEvents();
        reusedConstraint.openNav(this);

    }
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    private void initAdapter() {
        adapter = new FriendAdapter(this,R.layout.item_friends_all);
        adapter.setData(TransferHomeFragment.getListFriend());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvFriends.setLayoutManager(linearLayoutManager);

        rcvFriends.setAdapter(adapter);
    }

    private void addEvents() {
        imvBackAllFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllFriendScreen.this, AddNewFriendScreen.class));
            }
        });

    }

    private void linkView() {
        imvBackAllFriends = findViewById(R.id.imvBackAllFriends);
        btnAddFriend = findViewById(R.id.btnAddFriend);
        rcvFriends = findViewById(R.id.rcvFriends);
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }
}