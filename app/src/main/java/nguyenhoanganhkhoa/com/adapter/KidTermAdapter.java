package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.History;
import nguyenhoanganhkhoa.com.models.KidTerm;
import nguyenhoanganhkhoa.com.myapplication.R;

public class KidTermAdapter extends RecyclerView.Adapter<KidTermAdapter.ViewHolder> {
    private Context context;
    private List<KidTerm> mKidTerm;

    public void setData(List<KidTerm> list){
        this.mKidTerm = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public KidTermAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_kid_term,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KidTermAdapter.ViewHolder holder, int position) {
        KidTerm kidTerm = mKidTerm.get(position);
        if(kidTerm==null){
            return;
        }

        holder.txtNumberTerm.setText(String.valueOf(position + 1) + ".");
        holder.txtNameKidTerm.setText(kidTerm.getNameKidTerm());
        holder.txtDetailKidTerm.setText(kidTerm.getDetailKidTerm());


    }

    @Override
    public int getItemCount() {
        if(mKidTerm!=null)
            return mKidTerm.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNumberTerm, txtNameKidTerm,txtDetailKidTerm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNumberTerm =itemView.findViewById(R.id.txtNumberTerm);
            txtNameKidTerm =itemView.findViewById(R.id.txtNameKidTerm);
            txtDetailKidTerm =itemView.findViewById(R.id.txtDetailKidTerm);
        }
    }
}
