package nguyenhoanganhkhoa.com.myapplication.signup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import nguyenhoanganhkhoa.com.adapter.FacultyAdapter;
import nguyenhoanganhkhoa.com.adapter.FacultyAdapterError;
import nguyenhoanganhkhoa.com.adapter.MajorAdapter;
import nguyenhoanganhkhoa.com.customdialog.CustomDialog;
import nguyenhoanganhkhoa.com.customdialog.CustomDialogThreeButton;
import nguyenhoanganhkhoa.com.customdialog.CustomDialogTwoButton;
import nguyenhoanganhkhoa.com.models.Faculty;
import nguyenhoanganhkhoa.com.models.Major;
import nguyenhoanganhkhoa.com.models.Student;
import nguyenhoanganhkhoa.com.myapplication.another.CustomSpinner;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.EditInfomationScreen;
import nguyenhoanganhkhoa.com.myapplication.home.HomePageScreen;
import nguyenhoanganhkhoa.com.myapplication.login.LoginScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;



public class PersonalInformationSetScreen extends AppCompatActivity implements CustomSpinner.OnSpinnerEventsListener {

    Button btnSave;
    EditText edtIdStudent;
    CustomSpinner spnFaculty;
    RadioButton radFemale, radMale;
    CircleImageView imvAvatar;
    ImageView imvCamera, imvFaculty, imvDropdown, imvComebackUserinfo;
    TextView txtErrorIdStudent, txtErrorMarjor, txtErrorDateofBirth, txtErrorFaculty,edtDateofbirth;
    AutoCompleteTextView adtMajor;

    private String username, password,email
            ,fullname, phone, ID, major,dateOfBirth
            ,faculty, gender;
    private int avatar;

    ReusedConstraint reusedConstraint = new ReusedConstraint(PersonalInformationSetScreen.this);

    private void linkView() {
        btnSave = findViewById(R.id.btnSave);
        edtIdStudent = findViewById(R.id.edtIDStudent);
        edtDateofbirth = findViewById(R.id.edtDateOfBirth);
        spnFaculty = findViewById(R.id.spnFaculty);
        radFemale = findViewById(R.id.radFemale);
        radMale = findViewById(R.id.radMale);
        imvAvatar = findViewById(R.id.imvAvatar);
        imvCamera = findViewById(R.id.imvCamera);
        imvFaculty = findViewById(R.id.imvFaculty);
        imvDropdown = findViewById(R.id.imvDropdown);

        txtErrorDateofBirth= findViewById(R.id.txtErrorDataofbirth);
        txtErrorIdStudent= findViewById(R.id.txtErrorIDStudent);
        txtErrorMarjor= findViewById(R.id.txtErrorMajor);
        txtErrorFaculty= findViewById(R.id.txtErrorSpnFaculty);
        adtMajor = findViewById(R.id.adtMajors);
        imvComebackUserinfo=findViewById(R.id.imvComebackUserinfo);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information_set_screen);

        firebaseAuth = FirebaseAuth.getInstance();


        linkView();
        addResultLauncher();
        initAdapterFaculty();
        initAdapterMajor();

        addEvents();
    }


    //Các sự kiện validate
    private Boolean validateIDStudent(){
        String username = edtIdStudent.getText().toString().trim();
        if (username.isEmpty()){
            txtErrorIdStudent.setText(R.string.field_cannot_be_empty);
            txtErrorIdStudent.setTextSize(15);
            edtIdStudent.setHintTextColor(getColor(R.color.red));
            reusedConstraint.setCustomColor(edtIdStudent,R.drawable.edt_custom_error,R.color.red,R.color.red);
            return false;
        }



        else {
            reusedConstraint.setCustomColor(edtIdStudent,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            edtIdStudent.setHintTextColor(getColor(R.color.xamChu));

            txtErrorIdStudent.setText(null);
            txtErrorIdStudent.setTextSize(0);
            return true;
        }

    }
    private Boolean validateMajor(){
        String major = adtMajor.getText().toString().trim();
        if (major.isEmpty()){
            txtErrorMarjor.setText(R.string.field_cannot_be_empty);
            txtErrorMarjor.setTextSize(15);
            adtMajor.setHintTextColor(getColor(R.color.red));
            reusedConstraint.setCustomColor(adtMajor,R.drawable.edt_custom_error,R.color.red,R.color.red);
            return false;
        }


        else {
            reusedConstraint.setCustomColor(adtMajor,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            adtMajor.setHintTextColor(getColor(R.color.xamChu));

            txtErrorMarjor.setText(null);
            txtErrorMarjor.setTextSize(0);
            return true;
        }

    }
    private Boolean validateDateOfbirth(){

        String dateofbirth = edtDateofbirth.getText().toString().trim();
        if (dateofbirth.isEmpty()){
            txtErrorDateofBirth.setText(R.string.field_cannot_be_empty);
            txtErrorDateofBirth.setTextSize(15);
            edtDateofbirth.setHintTextColor(getColor(R.color.red));
            setCustomColortxt(edtDateofbirth,R.drawable.edt_custom_error,R.color.red,R.color.red);
            return false;
        }

        else {
            setCustomColortxt(edtDateofbirth,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            edtDateofbirth.setHintTextColor(getColor(R.color.xamChu));
            txtErrorDateofBirth.setText(null);
            txtErrorDateofBirth.setTextSize(0);
            return true;
        }


    }
    private Boolean validateFaculty(){
        String selectedItem = facultyAdapter.getItem(spnFaculty.getSelectedItemPosition()).getNameFaculty();

        if (selectedItem.equals("Faculty*")){
            try {
                facultyAdapterError = new FacultyAdapterError(this,R.layout.item_faculty_selected,getListFaculty());
                spnFaculty.setAdapter(facultyAdapterError);
            }
            catch (Exception e){
                Log.d("Error", "validateFaculty: " + e);
            }
            txtErrorFaculty.setText(R.string.field_cannot_be_empty);
            spnFaculty.setBackgroundResource(R.drawable.edt_custom_error);
            txtErrorFaculty.setTextSize(15);

            imvFaculty.setImageDrawable(getResources().getDrawable(R.drawable.ic_faculty_error));
            imvDropdown.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down_spinner_error));
            return false;
        }
        else {
            txtErrorFaculty.setText(null);
            return true;
        }
    }

    //Pick Date
    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    DatePickerDialog.OnDateSetListener setListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            edtDateofbirth.setText(AppUtil.dateFormat.format(calendar.getTime()));
            String s = edtDateofbirth.getText().toString();
        }
    };

    //Nạp adapter và dữ liệu cho các List
    FacultyAdapter facultyAdapter;
    FacultyAdapterError facultyAdapterError;
    private void initAdapterFaculty() {
        try {
            facultyAdapter = new FacultyAdapter(this,R.layout.item_faculty_selected,getListFaculty());
            spnFaculty.setAdapter(facultyAdapter);
        }
        catch (Exception e){
            Log.d("Error", "initAdapterFaculty: " + e);
        }


    }
    private List<Faculty> getListFaculty() {
        List<Faculty> list =new ArrayList<>();
        String[] facultyArray= getResources().getStringArray(R.array.faculty);
        int i;
        for (i = 0;i<facultyArray.length;i++)
            list.add(new Faculty(facultyArray[i]));

        return list;

    }
    public static int selectedFaculty = 0;

    MajorAdapter majorAdapter;
    private void initAdapterMajor() {
        try{
            majorAdapter = new MajorAdapter(this,R.layout.item_faculty_selected,getListMajor());
            adtMajor.setAdapter(majorAdapter);
        }
        catch (Exception e){
            Log.d("Error", "initAdapterMajor: " + e);
        }


    }
    private List<Major> getListMajor() {
        List<Major> list = new ArrayList<>();
        String[] majorArray= getResources().getStringArray(R.array.major);

        for (int i = 0;i<majorArray.length;i++)
            list.add(new Major(majorArray[i]));

        return list;
    }



    //Tạo sự kiện chụp ảnh
    Uri uri;
    StorageReference storageReference;
    FirebaseAuth auth;
    StorageTask uploadTask;
    String myUri = "";
    private void uploadProfileImages(DatabaseReference databaseReference) {
        storageReference = FirebaseStorage.getInstance().getReference().child("Profile Images");
        auth =  FirebaseAuth.getInstance();
        if(uri!=null){
            StorageReference fileRef = storageReference.child(auth.getCurrentUser().getUid() + ". jpg");
            uploadTask = fileRef.putFile(uri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUri = (Uri) task.getResult();
                        myUri = downloadUri.toString();
                        Log.d("TAG", "onComplete: " + myUri);
                        databaseReference.child(AppUtil.FB_IMAGES_BITMAP).setValue(myUri);
                    }
                }
            });
        }
        else {
            Toast.makeText(this,"Image not selected",Toast.LENGTH_SHORT).show();
        }
    }


    ActivityResultLauncher<Intent> activityResultLauncher;
    boolean isCamera;
    Bitmap bitmap = null;
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000,true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage, "Title", null);
        return Uri.parse(path);
    }
    private static final int STORAGE_REQUEST_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST_PERMISSION_CODE = 200;

    private void pickImageAction() {
        if(isCamera){
            if(ContextCompat.checkSelfPermission(PersonalInformationSetScreen.this,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(PersonalInformationSetScreen.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {
                pickCamera();
            }
            else{
                requestCameraPermission();
            }
        }
        else{
            if(ContextCompat.checkSelfPermission(PersonalInformationSetScreen.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                pickGallery();
            }
            else{
                requestStoragePermission();
            }

        }
    }
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(PersonalInformationSetScreen.this,new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                STORAGE_REQUEST_PERMISSION_CODE);
    }
    private void requestCameraPermission() {
        String[] permission = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(PersonalInformationSetScreen.this,permission,
                CAMERA_REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == STORAGE_REQUEST_PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission read storage granted",Toast.LENGTH_LONG).show();
                pickGallery();
            }else{
                Toast.makeText(this,"Permission read storage denied",Toast.LENGTH_LONG).show();
            }
        }

        if(requestCode == CAMERA_REQUEST_PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                pickCamera();
                Toast.makeText(this,"Permission camera granted",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Permission camera denied",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void pickCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activityResultLauncher.launch(cameraIntent);
    }
    private void pickGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activityResultLauncher.launch(intent);
    }
    private void addResultLauncher() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                    if(isCamera){
                        bitmap = (Bitmap) result.getData().getExtras().get("data");
                        imvAvatar.setImageBitmap(bitmap);
                        uri = getImageUri(getApplicationContext(),bitmap);
                    }
                    else{
                        uri = result.getData().getData();
                        if(uri !=null){
                            try {
                                InputStream inputStream = getContentResolver().openInputStream(uri);
                                bitmap = BitmapFactory.decodeStream(inputStream);
                                imvAvatar.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        });

    }
    private void cameraPickImage() {

        CustomDialogThreeButton customDialogThreeButton = new CustomDialogThreeButton
                (PersonalInformationSetScreen.this,R.layout.custom_dialog_chooss_image);
        customDialogThreeButton.btnTakePhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCamera = true;
                pickImageAction();
                customDialogThreeButton.dismiss();

            }
        });

        customDialogThreeButton.btnChooseFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCamera = false;
                pickImageAction();
                customDialogThreeButton.dismiss();
            }
        });
        customDialogThreeButton.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogThreeButton.dismiss();
            }
        });

        customDialogThreeButton.show();
    }

    // Tạo sự kiện thêm và đóng spinner
    @Override
    public void onPopupWindowOpened(Spinner spinner) {
        String thongdiep = getString(R.string.field_cannot_be_empty);

        if (txtErrorFaculty.getText().toString().equals(thongdiep))
        {
            imvDropdown.setImageResource(R.drawable.ic_arrrow_dropdown_up_errror);
        }
        else
            imvDropdown.setImageResource(R.drawable.ic_arrrow_dropdown_up);



    }

    @Override
    public void onBackPressed() {
        CustomDialogTwoButton customDialogTwoButton =
                new CustomDialogTwoButton(PersonalInformationSetScreen.this,R.layout.custom_dialog_unsaved_changes);
        customDialogTwoButton.btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalInformationSetScreen.this, EmailScreen.class);
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

    @Override
    public void onPopupWindowClosed(Spinner spinner) {
        String thongdiep = getString(R.string.field_cannot_be_empty);

        if (txtErrorFaculty.getText().toString().equals(thongdiep))
        {
            imvDropdown.setImageResource(R.drawable.ic_arrow_down_spinner_error);
        }
        else
            imvDropdown.setImageResource(R.drawable.ic_arrow_down_spinner);

    }

    // AddEvents và một vài sự kiện khác

    private void addEvents() {
        radMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b && bitmap==null){
                    imvAvatar.setImageResource(R.drawable.img_avatar_male);
                }

            }
        });

        radFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b && bitmap==null){
                    imvAvatar.setImageResource(R.drawable.img_avatar_female);
                }
            }
        });


        imvComebackUserinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogTwoButton customDialogTwoButton =
                        new CustomDialogTwoButton(PersonalInformationSetScreen.this,R.layout.custom_dialog_unsaved_changes);
                customDialogTwoButton.btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PersonalInformationSetScreen.this, EmailScreen.class);
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
        spnFaculty.setSpinnerEventsListener(PersonalInformationSetScreen.this);
        spnFaculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0)
                {
                    txtErrorFaculty.setText(null);
                    spnFaculty.setBackgroundResource(R.drawable.custom_edt);
                    imvFaculty.setImageDrawable(getResources().getDrawable(R.drawable.ic_faculty));
                    imvDropdown.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down_spinner));
                    txtErrorFaculty.setTextSize(0);
                    selectedFaculty = i;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edtIdStudent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateIDStudent();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        adtMajor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateMajor();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtDateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        PersonalInformationSetScreen.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,
                        year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();



            }
        });
        edtDateofbirth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateDateOfbirth();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //new DateTimeFormat(edtDateofbirth);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateIDStudent();
                validateMajor();
                validateDateOfbirth();
                validateFaculty();
                if(!validateIDStudent()|!validateMajor()|!validateDateOfbirth()|!validateFaculty())
                {
                    clearAllForcus();
                }
                else {
                    clearAllForcus();

                    pushAccountDataToFirebase();

                    CustomDialog customDialog = new
                            CustomDialog(PersonalInformationSetScreen.this, R.layout.custom_dialog_create_account_successful);
                    customDialog.btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            customDialog.dismiss();
                            Intent intent = new Intent(PersonalInformationSetScreen.this, LoginScreen.class);
                            startActivity(intent);
                        }
                    });
                    customDialog.show();
                }
            }
        });
        imvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraPickImage();
            }
        });
    }
    private void clearAllForcus(){
        edtIdStudent.clearFocus();
        adtMajor.clearFocus();
        edtDateofbirth.clearFocus();
        spnFaculty.clearFocus();
    }
    private void setCustomColortxt(TextView txtCanSua, int edtColor, int iconColor, int textColor){
        // Chỉnh màu cho thanh eTit text khi gặp error, focus, ...

        txtCanSua.setBackground(ContextCompat.getDrawable(PersonalInformationSetScreen.this,edtColor));
        txtCanSua.setCompoundDrawableTintList(ContextCompat.getColorStateList(PersonalInformationSetScreen.this,iconColor));
        txtCanSua.setTextColor(ContextCompat.getColorStateList(PersonalInformationSetScreen.this,textColor));
    }

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference("account").child(AppUtil.DATA_OBJECT);

    private void pushAccountDataToFirebase(){
        username = AppUtil.USERNAME_S.trim();
        password = AppUtil.PASSWORD_S.trim();
        phone = AppUtil.PHONE_S.trim();
        fullname = AppUtil.FULLNAME_S.trim();
        email = AppUtil.EMAIL_S.trim();



        if(radFemale.isChecked()){
            gender = radFemale.getText().toString().trim();
            avatar = R.drawable.img_avatar_female;
        }
        else if(radMale.isChecked()){
            gender = radMale.getText().toString().trim();
            avatar = R.drawable.img_avatar_male;
        }

        ID = edtIdStudent.getText().toString().trim();
        faculty = getListFaculty().get(selectedFaculty).getNameFaculty().trim();
        major = adtMajor.getText().toString().trim();
        dateOfBirth = edtDateofbirth.getText().toString().trim();


        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Student student = new Student(firebaseAuth.getCurrentUser().getUid(),username,password,email,
                                fullname,phone,ID,major,dateOfBirth,
                                faculty,gender,avatar,myUri,3000);

                        databaseReference.child(username).setValue(student)
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(PersonalInformationSetScreen.this, "Sign up fail", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(PersonalInformationSetScreen.this, "Sign up success", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        uploadProfileImages(databaseReference.child(username));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PersonalInformationSetScreen.this,"Email has been user, please try another!",Toast.LENGTH_SHORT).show();
                    }
                });





    }

}