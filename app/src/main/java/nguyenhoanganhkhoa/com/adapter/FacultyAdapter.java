package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.List;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.models.Faculty;

public class FacultyAdapter extends ArrayAdapter<Faculty> {

    public static final int ERROR_STATUS = 0;
    public static final int NORMAL_STATUS = 1;

    public static final int SET_PERSONAL_INFO_SCREEN = 1;
    public static final int EDIT_PERSONAL_INFO_SCREEN = 2;
    public static final int ORDER_SCREEN = 3;

    private int currentPosition;
    private int statusAdapter;
    private int screen;

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public int getStatusAdapter() {
        return statusAdapter;
    }

    public void setStatusAdapter(int statusAdapter) {
        this.statusAdapter = statusAdapter;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }


    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(getScreen() == ORDER_SCREEN)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_selected,parent,false);
        }
        else{
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faculty_selected,parent,false);
        }
        TextView txtFacultySelected = convertView.findViewById(R.id.txtFacultySelectedItem);

        if(getScreen()==ORDER_SCREEN){
            Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.big_shoulder_display_bold);
            txtFacultySelected.setTypeface(typeface);
            txtFacultySelected.setTextColor(ContextCompat.getColorStateList(getContext(),R.color.black));
            txtFacultySelected.setTextSize(20);
        }

        Faculty faculty = this.getItem(position);
        if (faculty !=null){
            txtFacultySelected.setText(faculty.getNameFaculty());
        }

        if(getStatusAdapter() == ERROR_STATUS){
            if(position==0){
                txtFacultySelected.setTextColor(ContextCompat.getColorStateList(getContext(),R.color.red));
            }
        }
        else{
            if(getScreen() != ORDER_SCREEN){
                txtFacultySelected.setTextColor(ContextCompat.getColorStateList(getContext(),R.color.xamChu));
            }
        }
        return convertView;
//        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faculty,parent,false);
        TextView txtFaculty = convertView.findViewById(R.id.txtFacultyItem);

        Faculty faculty = this.getItem(position);
        if(getScreen() == ORDER_SCREEN){
            Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.big_shoulder_display_bold);
            txtFaculty.setTypeface(typeface);
            txtFaculty.setTextColor(ContextCompat.getColorStateList(getContext(),R.color.black));
            txtFaculty.setTextSize(20);
        }
        if (faculty !=null){
            txtFaculty.setText(faculty.getNameFaculty());
        }
        if(position ==0 && faculty.getNameFaculty().equals("Faculty*")){
            txtFaculty.setTextColor(ContextCompat.getColorStateList(getContext(),R.color.black));
        }
        else{
            if(getScreen()!=ORDER_SCREEN){
                txtFaculty.setTextColor(ContextCompat.getColorStateList(getContext(),R.color.xamChu));
            }
        }


        if(getCurrentPosition()==position){
            if(getScreen()==SET_PERSONAL_INFO_SCREEN){
                if(getCurrentPosition()!=0){
                    txtFaculty.setTextColor(ContextCompat.getColorStateList(getContext(),R.color.primary_yellow));
                }
            }
            else{
                txtFaculty.setTextColor(ContextCompat.getColorStateList(getContext(),R.color.primary_yellow));
            }
        }
        return convertView;

    }




    public boolean isEnabled(int position){
        Faculty faculty = this.getItem(position);
        if(getScreen() == SET_PERSONAL_INFO_SCREEN){
            if(position == 0 && faculty.getNameFaculty().equals("Faculty*"))
            {
                // Disable the first item from Spinner
                // First item will be use for hint
                return false;
            }
            else
            {
                return true;
            }
        }
        else{
            return true;
        }
    }


    public FacultyAdapter(@NonNull Context context, int resource, @NonNull List<Faculty> objects) {

        super(context, resource, objects);
    }

}
