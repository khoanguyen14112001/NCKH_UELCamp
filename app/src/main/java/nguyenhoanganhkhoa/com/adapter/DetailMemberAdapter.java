package nguyenhoanganhkhoa.com.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.Member;
import nguyenhoanganhkhoa.com.models.Notification;
import nguyenhoanganhkhoa.com.myapplication.R;

public class DetailMemberAdapter extends RecyclerView.Adapter<DetailMemberAdapter.ViewHolder>  {

    private List<Member> mListDetailMember;

    public DetailMemberAdapter(List<Member> mListDetailMember) {
        this.mListDetailMember = mListDetailMember;

    }

    @NonNull
    @Override
    public DetailMemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_detail_member,parent,false);
        return new DetailMemberAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailMemberAdapter.ViewHolder holder, int position) {
        Member member = mListDetailMember.get(position);
        if(member ==null)
        {
            return;
        }
        holder.imvAvatarMemberDetail.setImageResource(member.getMemberThumb());
        holder.txtCareerMemberDetail.setText(member.getMemberCareer());
        holder.txtRoleMemberDetail.setText(member.getMemberRole());
        holder.txtGenderMemberDetail.setText(member.getMemberGender());
        holder.txtDateOfBirthMemberDetail.setText(member.getMemberDateOfBirth());
        holder.txtNameMemberDetail.setText(member.getMemberName());
    }

    @Override
    public int getItemCount() {
        return mListDetailMember.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvAvatarMemberDetail;
        TextView txtCareerMemberDetail, txtNameMemberDetail, txtRoleMemberDetail, txtGenderMemberDetail,
                txtDateOfBirthMemberDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvAvatarMemberDetail = itemView.findViewById(R.id.imvAvatarMemberDetail);

            txtCareerMemberDetail = itemView.findViewById(R.id.txtCareerMemberDetail);
            txtNameMemberDetail = itemView.findViewById(R.id.txtNameMemberDetail);
            txtRoleMemberDetail = itemView.findViewById(R.id.txtRoleMemberDetail);
            txtGenderMemberDetail = itemView.findViewById(R.id.txtGenderMemberDetail);
            txtDateOfBirthMemberDetail = itemView.findViewById(R.id.txtDateOfBirthMemberDetail);

        }
    }
}
