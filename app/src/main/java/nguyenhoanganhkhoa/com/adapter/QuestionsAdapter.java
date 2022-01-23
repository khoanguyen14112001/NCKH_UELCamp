package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.models.Images;
import nguyenhoanganhkhoa.com.models.QuestionsCategories;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.HelpCenterDetailScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> implements Filterable {

    private List<QuestionsCategories> mListQuesCate;
    private List<QuestionsCategories> mListQuesCateOld;
    int layout;
    Context context;

    public QuestionsAdapter(List<QuestionsCategories> mListQuesCate, int layout, Context context) {
        this.mListQuesCate = mListQuesCate;
        this.layout = layout;
        if(layout == R.layout.item_question){
            mListQuesCateOld = mListQuesCate;
        }
        this.context = context;
    }

    @NonNull
    @Override
    public QuestionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layout,parent,false);
        return new QuestionsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter.ViewHolder holder, int position) {
        QuestionsCategories questionsCategories = mListQuesCate.get(position);
        if(questionsCategories ==null){
            return;
        }
        if(layout==R.layout.item_question){
            holder.imvThumbQuestion.setImageResource(questionsCategories.getThumbQuestion_Categories());
            holder.txtQuestions.setText(questionsCategories.getNameQuestion_Categories());
            if(position%2 == 1){
                holder.cvQuetstions.setBackgroundTintList(context.getResources().getColorStateList(R.color.primary_yellow));
            }
            else if(position%2 ==0){
                holder.cvQuetstions.setBackgroundTintList(context.getResources().getColorStateList(R.color.xamNen));
            }
            String s1 = "I have not received money from Momo wallet";
            if(mListQuesCate.get(position).getNameQuestion_Categories().equals(s1)){
                holder.txtQuestions.setText(changeColor(s1,31,35,R.color.purple_momo));
            }

            holder.layout_question.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, HelpCenterDetailScreen.class);
                    AppUtil.PROBLEM = questionsCategories.getNameQuestion_Categories();
                    AppUtil.HELP_PROBLEM_CONTEXT = context.getClass().toString();
                    context.startActivity(intent);
                }
            });



        }
        else if(layout==R.layout.item_problem_categories){
            holder.imvProblemCategories.setImageResource(questionsCategories.getThumbQuestion_Categories());
            holder.txtNameCategories.setText(questionsCategories.getNameQuestion_Categories());
            holder.txtArticles.setText(String.valueOf(questionsCategories.getArticlesCategories()));

        }


    }
    public SpannableString changeColor(String textVerifcation, int numStart, int numEnd, int ColorChange) {
        SpannableString ss = new SpannableString(textVerifcation) ;
        ForegroundColorSpan fcsYellow = new ForegroundColorSpan(context.getColor(ColorChange));
        ss.setSpan(fcsYellow,numStart,numEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        StyleSpan typefaceSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(typefaceSpan,numStart,numEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss;
    }
    @Override
    public int getItemCount() {
        return mListQuesCate.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                if(layout == R.layout.item_question){
                    String textSearch = charSequence.toString();
                    if(textSearch.isEmpty()){
                        mListQuesCate = mListQuesCateOld;
                    }
                    else{
                        List<QuestionsCategories> list = new ArrayList<>();
                        for(QuestionsCategories question: mListQuesCateOld){
                            if(question.getNameQuestion_Categories().toLowerCase()
                                    .contains(textSearch.toLowerCase().trim())){
                                list.add(question);
                            }
                        }
                        mListQuesCate = list;
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mListQuesCate;
                    return filterResults;
                }
                return null;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if(layout ==R.layout.item_question){
                    mListQuesCate = (List<QuestionsCategories>) filterResults.values;
                    notifyDataSetChanged();
                }
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvThumbQuestion,imvProblemCategories;
        TextView txtNameCategories,txtArticles, txtQuestions ;
        CardView cvQuetstions;

        LinearLayout layout_question;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumbQuestion = itemView.findViewById(R.id.imvThumbQuestion);
            imvProblemCategories = itemView.findViewById(R.id.imvProblemCategories);
            txtNameCategories = itemView.findViewById(R.id.txtNameCategories);
            txtArticles = itemView.findViewById(R.id.txtArticles);
            txtQuestions = itemView.findViewById(R.id.txtQuestions);
            cvQuetstions = itemView.findViewById(R.id.cvQuetstions);

            layout_question = itemView.findViewById(R.id.layout_question);
        }
    }
}
