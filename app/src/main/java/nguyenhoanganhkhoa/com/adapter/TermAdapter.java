package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.Date;
import nguyenhoanganhkhoa.com.models.Term;
import nguyenhoanganhkhoa.com.myapplication.R;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {

    private Context context;
    private List<Term> mTerm;
    private int layout;



    public TermAdapter(Context context, int layout) {
        this.context = context;
        this.layout = layout;
    }

    public void setData(List<Term> list){
        this.mTerm = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TermAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.ViewHolder holder, int position) {
        Term term = mTerm.get(position);
        if(term==null)
        {
            return;
        }

        holder.txtTerms.setText(term.getTerm());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        holder.rcvKidTerms.setLayoutManager(linearLayoutManager);
        KidTermAdapter kidTermAdapter = new KidTermAdapter();
        kidTermAdapter.setData(term.getmListKidTerm());
        holder.rcvKidTerms.setAdapter(kidTermAdapter);

        boolean isExpandable = mTerm.get(position).isExpandable();
        holder.rcvKidTerms.setVisibility(getChange(holder,isExpandable));
        
    

    }

    private int getChange(TermAdapter.ViewHolder holder, boolean isExpandable) {
        if(isExpandable)
        {
            holder.imvArrowTerm.setImageResource(R.drawable.ic_arrrow_dropdown_up_black);
            holder.layout_terms.setBackgroundColor(context.getResources().getColor(R.color.primary_yellow));
            holder.rcvKidTerms.setVisibility(View.VISIBLE);
            return View.VISIBLE;

        }
        else
        {
            holder.imvArrowTerm.setImageResource(R.drawable.ic_arrow_down_spinner);
            holder.layout_terms.setBackgroundColor(context.getResources().getColor(R.color.xamNen));
            holder.rcvKidTerms.setVisibility(View.GONE);
            return View.GONE;

        }
    }


    @Override
    public int getItemCount() {
        if(mTerm !=null)
            return mTerm.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTerms;
        private RecyclerView rcvKidTerms;
        private ConstraintLayout layout_terms;
        private ImageView imvArrowTerm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTerms = itemView.findViewById(R.id.txtTerms);
            rcvKidTerms = itemView.findViewById(R.id.rcvKidTerms);
            layout_terms = itemView.findViewById(R.id.layout_terms);
            imvArrowTerm = itemView.findViewById(R.id.imvArrowTerm);

            layout_terms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Term term = mTerm.get(getAdapterPosition());
                    term.setExpandable(!term.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });


        }
    }
}
