package nguyenhoanganhkhoa.com.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.Friends;
import nguyenhoanganhkhoa.com.models.KidTerm;
import nguyenhoanganhkhoa.com.myapplication.R;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    private Context context;
    private  int layout_item;
    private  int screen = 0;

    public FriendAdapter(Context context, int layout_item) {
        this.context = context;
        this.layout_item = layout_item;
    }

    public FriendAdapter(Context context, int layout_item, int screen) {
        this.context = context;
        this.layout_item = layout_item;
        this.screen = screen;

    }

    private List<Friends> mFriends;

    public void setData(List<Friends> list){
        this.mFriends = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layout_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.ViewHolder holder, int position) {
        Friends friends = mFriends.get(position);
        if(friends==null){
            return;
        }

        if(layout_item == R.layout.item_friends_quickacess){
            setFriend(holder,friends);
        }

        if(layout_item == R.layout.item_friends_all){
            setFriend(holder,friends);
            holder.txtPhoneFriend.setText(friends.getFriendPhone());
            if(friends.isFavoriteFriend()){
                holder.btnLove.setImageResource(R.drawable.ic_love_active);
            } else{
                holder.btnLove.setImageResource(R.drawable.ic_love);
            }
            holder.btnLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(friends.isFavoriteFriend()){
                        friends.setFavoriteFriend(false);
                        holder.btnLove.setImageResource(R.drawable.ic_love);
                    }
                    else{
                        friends.setFavoriteFriend(true);
                        holder.btnLove.setImageResource(R.drawable.ic_love_active);
                    }

                }
            });


        }

        if(layout_item == R.layout.item_friends_add){
            setFriend(holder,friends);
            holder.btnAddFriendItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!friends.isSendRequest()){
                        friends.setSendRequest(true);
                        holder.btnAddFriendItem.setBackground(context.getDrawable(R.drawable.custom_button_dialog_black));
                        holder.btnAddFriendItem.setText("Request sent");
                        holder.btnAddFriendItem.setTextColor(context.getColor(R.color.white));
                    }
                }
            });
        }

    }

    private void setFriend(FriendAdapter.ViewHolder holder, Friends friends){
        holder.txtNameFriend.setText(friends.getFriendName());
        if(friends.isMaleFriend()){
            holder.imvFriend.setImageResource(R.drawable.img_avatar_male);
        }
        else{
            holder.imvFriend.setImageResource(R.drawable.img_avatar_female);
        }
    }

    @Override
    public int getItemCount() {
        if(mFriends !=null){
            if(layout_item == R.layout.item_friends_quickacess){
                return 8;
            }
            if(layout_item == R.layout.item_friends_all && screen == 1){
                return 4;
            }
            else{
                return mFriends.size();
            }
        }
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameFriend, txtPhoneFriend;
        ImageView imvFriend, btnLove;
        Button btnAddFriendItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameFriend =itemView.findViewById(R.id.txtNameFriend);
            txtPhoneFriend =itemView.findViewById(R.id.txtPhoneFriend);
            imvFriend =itemView.findViewById(R.id.imvFriend);
            btnLove =itemView.findViewById(R.id.btnLove);

            btnAddFriendItem =itemView.findViewById(R.id.btnAddFriendItem);
        }
    }
}
