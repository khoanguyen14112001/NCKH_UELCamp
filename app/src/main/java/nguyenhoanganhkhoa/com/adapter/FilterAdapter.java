package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

import nguyenhoanganhkhoa.com.models.Faculty;
import nguyenhoanganhkhoa.com.models.Filter;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.signup.PersonalInformationSetScreen;

public class FilterAdapter extends ArrayAdapter<Filter> {


    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter_selected,parent,false);
        TextView txtFilterSeleted = convertView.findViewById(R.id.txtFilterSelectedItem);

        Filter filter = this.getItem(position);
        if (filter !=null){
            txtFilterSeleted.setText(filter.getNameFilter());

        }
        return convertView;
//        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter,parent,false);
        TextView txtFilter = convertView.findViewById(R.id.txtFilterItem);

        Filter filter = this.getItem(position);
        if (filter !=null){
            txtFilter.setText(filter.getNameFilter());
        }

        return convertView;

    }


    public FilterAdapter(@NonNull Context context, int resource, @NonNull List<Filter> objects) {

        super(context, resource, objects);
    }

}
