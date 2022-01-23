package nguyenhoanganhkhoa.com.fragments;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;

import nguyenhoanganhkhoa.com.customdialog.CustomDialogTwoButton;
import nguyenhoanganhkhoa.com.models.Student;
import nguyenhoanganhkhoa.com.myapplication.home.EditInfomationScreen;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.login.LoginScreen;
import nguyenhoanganhkhoa.com.myapplication.signup.EmailScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    ImageView imvChangeProfile;
    TextView txtDateOfBirthIns,txtFacultyIns, txtMajorIns, txtPhoneIns, txtNameIns,txtGender, txtIDIns, txtSignOut,txtEmailIns ;

    ImageView imvAvatarIns;
    ReusedConstraint reusedConstraint = new ReusedConstraint(getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        linkView(view);
        getDataFromFirebase();
        addEvents();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromFirebase();


    }
    public static DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference("account");

    private void getDataFromFirebase() {
        databaseReference.child(AppUtil.DATA_OBJECT).child(AppUtil.USERNAME_AFTER_LOGGIN)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            String name = snapshot.child(AppUtil.FB_FULLNAME).getValue(String.class);
                            String birth = snapshot.child(AppUtil.FB_DATE_OF_BIRTH).getValue(String.class);
                            String ID = snapshot.child(AppUtil.FB_ID).getValue(String.class);
                            String faculty = snapshot.child(AppUtil.FB_FACULTY).getValue(String.class);
                            String major = snapshot.child(AppUtil.FB_MAJOR).getValue(String.class);
                            String phone = snapshot.child(AppUtil.FB_PHONE).getValue(String.class);
                            String email = snapshot.child(AppUtil.FB_EMAIL).getValue(String.class);
                            String gender = snapshot.child(AppUtil.FB_GENDER).getValue(String.class);

                            int avatar = 0;
                            String uri = snapshot.child(AppUtil.FB_IMAGES_BITMAP).getValue(String.class);
                            if(!uri.isEmpty() && !uri.equals("Null")){
                                if(getContext()!=null){
                                    Glide.with(getContext()).load(uri).into(imvAvatarIns);
                                }
                            }
                            else{
                                if(gender.equals("Male")){
                                    avatar = R.drawable.img_avatar_male;
                                }
                                if(gender.equals("Female")){
                                    avatar = R.drawable.img_avatar_female;
                                }
                                imvAvatarIns.setImageResource(avatar);
                            }

                            txtNameIns.setText(name);
                            txtDateOfBirthIns.setText(birth);
                            txtIDIns.setText(ID);
                            txtFacultyIns.setText(faculty);
                            txtMajorIns.setText(major);
                            txtPhoneIns.setText(phone);
                            txtEmailIns.setText(email);
                            txtGender.setText(gender);
                        }
                        catch (Exception e){
                            Log.d("Error", "Fail to parse and add value to view in AccountFragment: " + e);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("Error", "Fail to load info in Account Fragment" + error.toString());
                    }
                });

    }

    private void pushData(Intent intent) {
        try {
            Bundle bundle = new Bundle();
            String fullname = txtNameIns.getText().toString();
            String phone = txtPhoneIns.getText().toString();
            String ID = txtIDIns.getText().toString();
            String major = txtMajorIns.getText().toString();
            String birth = txtDateOfBirthIns.getText().toString();
            String faculty = txtFacultyIns.getText().toString();
            String gender = txtGender.getText().toString();
            int avatar = 0;

            if(gender.equals("Male")){
                avatar = R.drawable.img_avatar_male;
            }
            if(gender.equals("Female")){
                avatar = R.drawable.img_avatar_female;
            }

            Student student = new Student(fullname,phone,ID,major,birth,faculty,gender,avatar);
            bundle.putSerializable("my_item",student);
            intent.putExtra("my_bundle",bundle);
        }
        catch (Exception e){
            Log.d("Error", "Fail to push data to EditInfoScreen from Account Fragment: " + e);
        }

    }

    private void linkView(View view) {
        imvChangeProfile = view.findViewById(R.id.imvChangeProfile);
        txtDateOfBirthIns = view.findViewById(R.id.txtDateOfBirthIns);
        txtFacultyIns = view.findViewById(R.id.txtFacultyIns);
        txtMajorIns = view.findViewById(R.id.txtMajorIns);
        txtPhoneIns = view.findViewById(R.id.txtPhoneIns);
        txtNameIns = view.findViewById(R.id.txtNameIns);
        txtGender = view.findViewById(R.id.txtGender);
        txtIDIns = view.findViewById(R.id.txtIDIns);
        txtSignOut = view.findViewById(R.id.txtSignOut);

        imvAvatarIns = view.findViewById(R.id.imvAvatarIns);
        txtEmailIns = view.findViewById(R.id.txtEmailIns);

    }

    private void addEvents() {
        txtSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogTwoButton customDialogTwoButton = new CustomDialogTwoButton(getContext(),R.layout.custom_dialog_signout);
                customDialogTwoButton.btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), LoginScreen.class);
                        startActivity(intent);
                    }
                });
                customDialogTwoButton.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        customDialogTwoButton.dismiss();
                    }
                });

                customDialogTwoButton.show();

            }
        });
        imvChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditInfomationScreen.class);
                pushData(intent);
                startActivity(intent);
            }
        });
    }
}