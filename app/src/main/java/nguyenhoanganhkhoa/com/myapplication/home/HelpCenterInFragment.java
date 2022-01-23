package nguyenhoanganhkhoa.com.myapplication.home;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

import nguyenhoanganhkhoa.com.customdialog.CustomDialog;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.signup.PersonalInformationSetScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpCenterInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpCenterInFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HelpCenterInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HelpCenterInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpCenterInFragment newInstance(String param1, String param2) {
        HelpCenterInFragment fragment = new HelpCenterInFragment();
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

    EditText editTextTextMultiLine;
    Button btnRequestHelpIn;
    ActivityResultLauncher activityResultLauncher;
    ActivityResultLauncher<String> permission;


    ImageView imv1,imv2,imv3, imvAdd;
    View viewAddPics;
    TextView txtService, txtAdd;

    LinearLayout layoutPics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help_center_in, container, false);
        linkView(view);
        setService();
        checkContent();
        addEvents();
        addPermissionResult();
        addResultLauncher();

        return view;
    }

    private void setService() {
        if(AppUtil.HELP_PROBLEM_CONTEXT.equals(ContactSupportScreen.class.toString())){
            txtService.setText(R.string.history);
        }
        else{
            txtService.setText(R.string.transaction);
        }
    }


    Bitmap bitmap1, bitmap2, bitmap3;
    private void addResultLauncher() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==-1 && result.getData()!=null){
                    Uri uri = result.getData().getData();
                    if(uri !=null){
                        try {
                            InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
                            if(bitmap1==null)
                            {
                                bitmap1 = BitmapFactory.decodeStream(inputStream);
                                layoutPics.setVisibility(View.VISIBLE);
                                imv1.setVisibility(View.VISIBLE);
                                imv1.setImageBitmap(bitmap1);
                            }
                            else if(bitmap2==null){
                                bitmap2 = BitmapFactory.decodeStream(inputStream);
                                imv2.setVisibility(View.VISIBLE);
                                imv2.setImageBitmap(bitmap2);
                            }

                            else {
                                bitmap3 = BitmapFactory.decodeStream(inputStream);
                                imv3.setVisibility(View.VISIBLE);
                                imv3.setImageBitmap(bitmap3);
                                viewAddPics.setVisibility(View.GONE);
                                txtAdd.setVisibility(View.GONE);
                                imvAdd.setVisibility(View.GONE);
                            }

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    }

    private void checkContent() {
        if(editTextTextMultiLine.getText().toString().isEmpty()){
            btnRequestHelpIn.setEnabled(false);
            btnRequestHelpIn.setBackground(getResources().getDrawable(R.drawable.button_login_block));
            btnRequestHelpIn.setTextColor(getResources().getColor(R.color.xamBlcok));
        }
        else{
            btnRequestHelpIn.setEnabled(true);
            btnRequestHelpIn.setBackground(getResources().getDrawable(R.drawable.button_login));
            btnRequestHelpIn.setTextColor(getResources().getColor(R.color.black));
        }
    }

    private void requestStoragePermission() {
        permission.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    private void pickAction(){
        try{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            activityResultLauncher.launch(intent);
        }
        catch (Exception e) {
            Log.d("Error", "Fail to pick Gallery: " + e);
        }

    }

    private void pickGallery() {
        if(ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            pickAction();
        }
        else{
            requestStoragePermission();
        }
    }
    private void addPermissionResult() {
         permission = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if(result) {
                            Toast.makeText(getContext(),"Permission Granted",Toast.LENGTH_LONG).show();
                            pickAction();
                        }
                        else{
                            Toast.makeText(getContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }


    private void addEvents() {
        viewAddPics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickGallery();
            }
        });

        editTextTextMultiLine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkContent();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnRequestHelpIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextTextMultiLine.getText().toString().isEmpty()){
                    CustomDialog dialog = new CustomDialog(getContext(),R.layout.custom_dialog_request_success);
                    dialog.btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(AppUtil.HELP_PROBLEM_CONTEXT.equals(ContactSupportScreen.class.toString())){
                                startActivity(new Intent(getContext(),HomePageScreen.class));
                            }
                            else{
                                startActivity(new Intent(getContext(),ShowAllTransactionScreen.class));
                            }
                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    private void linkView(View view) {
        editTextTextMultiLine = view.findViewById(R.id.editTextTextMultiLine);
        btnRequestHelpIn = view.findViewById(R.id.btnRequestHelpIn);
        imv1 = view.findViewById(R.id.imv1);
        imv2 = view.findViewById(R.id.imv2);
        imv3 = view.findViewById(R.id.imv3);
        viewAddPics = view.findViewById(R.id.viewAddPics);
        txtService = view.findViewById(R.id.txtService);

        layoutPics = view.findViewById(R.id.linearLayout);
        imvAdd = view.findViewById(R.id.imvAdd);
        txtAdd = view.findViewById(R.id.textView);
    }
}