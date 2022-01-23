package nguyenhoanganhkhoa.com.myapplication.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;


public class HelpCenterOutFragment extends Fragment {


    Button btnRequestHelp;

    TextView txtProblem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help_center_out, container, false);;

        linkView(view);
        addEvents();
        getData();
        return view;
    }

    private void getData(){
        txtProblem.setText(AppUtil.PROBLEM);
    }

    private void addEvents() {
        btnRequestHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_fragment,new HelpCenterInFragment());
                    fragmentTransaction.addToBackStack(null).commit();
                }
                catch (Exception e){
                    Log.d("Error", "onClick - Fail to load HelpCenterInFragment: " + e);
                }

            }
        });
    }

    private void linkView(View view) {
        btnRequestHelp = view.findViewById(R.id.btnRequestHelp);
        txtProblem = view.findViewById(R.id.txtProblem);
    }
}