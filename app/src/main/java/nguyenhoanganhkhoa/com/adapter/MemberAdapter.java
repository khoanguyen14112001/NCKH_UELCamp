package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import nguyenhoanganhkhoa.com.models.Member;
import nguyenhoanganhkhoa.com.myapplication.home.OurTeamScreen;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
    private Context context;
    private List<Member> mMember;

    public static int memberNumber;

    public MemberAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Member> list){
        this.mMember = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_member,parent,false);
        return new MemberAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.ViewHolder holder, int position) {
        Member member = mMember.get(position);
        if(member ==null)
        {
            return;
        }

        holder.imvAvatarMember.setImageResource(member.getMemberThumb());
        holder.txtCareerMember.setText(member.getMemberCareer());
        holder.txtRoleMember.setText(member.getMemberRole());
        holder.txtNameMember.setText(member.getMemberName());
        holder.layout_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDetail(holder.getAdapterPosition());
            }
        });

    }


    private void goToDetail(int position) {
        Intent intent = new Intent(context, OurTeamScreen.class);
        memberNumber = position;
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(mMember !=null)
            return mMember.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCareerMember,txtNameMember, txtRoleMember ;
        private CircleImageView imvAvatarMember;
        private ConstraintLayout layout_member;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_member = itemView.findViewById(R.id.layout_member);
            txtCareerMember = itemView.findViewById(R.id.txtCareerMember);
            txtNameMember = itemView.findViewById(R.id.txtNameMember);
            txtRoleMember = itemView.findViewById(R.id.txtRoleMember);
            imvAvatarMember = itemView.findViewById(R.id.imvAvatarMember);
        }
    }
}
