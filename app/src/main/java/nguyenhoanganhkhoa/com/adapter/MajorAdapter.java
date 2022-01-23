package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.models.Major;

public class MajorAdapter extends ArrayAdapter<Major> {
    private List<Major> mListMajor;

    public MajorAdapter(@NonNull Context context, int resource, @NonNull List<Major> objects) {
        super(context, resource, objects);
        mListMajor = new ArrayList<>(objects);
    }


    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Major> mListSuggest = new ArrayList<>();

                if(charSequence==null||charSequence.length()==0){
                    mListSuggest.addAll(mListMajor);
                }
                else{
                    String filter = charSequence.toString().toLowerCase().trim();
                    for(Major major : mListMajor)
                    {
                        if(major.getNameMajor().toLowerCase().trim().contains(filter))
                        {
                            mListSuggest.add(major);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListSuggest;
                filterResults.count = mListSuggest.size();
                return filterResults;
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((Major)resultValue).getNameMajor();
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                clear();
                addAll((List<Major>)filterResults.values);
                notifyDataSetChanged();

            }
        };
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faculty,parent,false);
        }
        TextView txtFaculty = convertView.findViewById(R.id.txtFacultyItem);
        Major major = getItem(position);
        txtFaculty.setText(major.getNameMajor());

        return convertView;
    }

}
