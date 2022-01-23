package nguyenhoanganhkhoa.com.myapplication.home;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.function.IntUnaryOperator;

import de.hdodenhof.circleimageview.CircleImageView;
import nguyenhoanganhkhoa.com.adapter.FacultyAdapter;
import nguyenhoanganhkhoa.com.adapter.FacultyEditAdapter;
import nguyenhoanganhkhoa.com.adapter.MajorAdapter;
import nguyenhoanganhkhoa.com.customdialog.CustomDialog;
import nguyenhoanganhkhoa.com.customdialog.CustomDialogThreeButton;
import nguyenhoanganhkhoa.com.customdialog.CustomDialogTwoButton;
import nguyenhoanganhkhoa.com.fragments.AccountFragment;
import nguyenhoanganhkhoa.com.models.Faculty;
import nguyenhoanganhkhoa.com.models.Major;
import nguyenhoanganhkhoa.com.models.Student;
import nguyenhoanganhkhoa.com.myapplication.another.CustomSpinner;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.signup.PersonalInformationSetScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class EditInfomationScreen extends AppCompatActivity implements CustomSpinner.OnSpinnerEventsListener {
    Button btnSave;
    EditText edtIdStudent, edtPhone,edtNameEditInfo;
    CustomSpinner spnFaculty;
    RadioButton radFemale, radMale;
    CircleImageView imvAvatar;
    ImageView imvCamera, imvFaculty, imvDropdown, imvComebackEditInfo;
    TextView txtErrorIdStudent, txtErrorMarjor, txtErrorDateofBirth, txtErrorFaculty,edtDateofbirth,
            txtErrorPhone;
    AutoCompleteTextView adtMajor;

    boolean isComeback = false;



    ReusedConstraint reusedConstraint = new ReusedConstraint(EditInfomationScreen.this);


    private void linkView() {
        btnSave = findViewById(R.id.btnSaveEditInfo);
        edtIdStudent = findViewById(R.id.edtIDStudentEditInfo);
        edtDateofbirth = findViewById(R.id.edtDateOfBirthEditInfo);
        edtNameEditInfo = findViewById(R.id.edtNameEditInfo);


        spnFaculty = findViewById(R.id.spnFacultyEditInfo);
        radFemale = findViewById(R.id.radFemaleEditInfor);
        radMale = findViewById(R.id.radMaleEditInfor);

        imvAvatar = findViewById(R.id.imvAvatarEditInfo);
        imvCamera = findViewById(R.id.imvCameraEditInfo);
        imvFaculty = findViewById(R.id.imvFacultyEditInfo);
        imvDropdown = findViewById(R.id.imvDropdownEditInfo);

        txtErrorDateofBirth= findViewById(R.id.txtErrorDataofbirthEditInfo);
        txtErrorIdStudent= findViewById(R.id.txtErrorIDStudentEditInfo);
        txtErrorMarjor= findViewById(R.id.txtErrorMajorEditInfo);
        txtErrorFaculty= findViewById(R.id.txtErrorSpnFacultyEditInfo);
        txtErrorPhone = findViewById(R.id.txtErrorPhoneEditInfo);

        adtMajor = findViewById(R.id.adtMajorsEditInfo);
        edtPhone = findViewById(R.id.edtPhoneEditInfo);

        imvComebackEditInfo=findViewById(R.id.imvComebackEditInfo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_infomation_screen);



        linkView();
        initAdapterFaculty();
        initAderterMarjor();
        addResultLauncher();
        getData();
        getUserImages();
        addEvents();


    }

    Student student;
    String oName, oBirth, oID, oPhone, oMajor, oGender, oFaculty;

    private void getData() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("my_bundle");
            if (bundle != null) {
                student = (Student) bundle.getSerializable("my_item");

                oName = student.getFullnameStudent();
                oBirth = student.getDateOfBirthStudent();
                oID = student.getIDStudent();
                oPhone = student.getPhoneStudent();
                oMajor = student.getMajorStudent();
                oGender = student.getGenderStudent();
                oFaculty = student.getFacultyStudent();

                edtNameEditInfo.setText(oName);
                edtDateofbirth.setText(oBirth);
                edtIdStudent.setText(oID);
                edtPhone.setText(oPhone);
                adtMajor.setText(oMajor);

                if(oGender.equals("Female"))
                {
                    radFemale.setChecked(true);
                }
                else if(oGender.equals("Male"))
                {
                    radMale.setChecked(true);
                }
                int i;
                for (i = 0; i < getListFaculty().size(); i++) {
                    if (getListFaculty().get(i).getNameFaculty().equals(oFaculty)) {
                        spnFaculty.setSelection(i);
                        break;
                    }
                }
                imvAvatar.setImageResource(student.getAvatarStudent());
            }

        }
        catch (Exception e){
            Log.d("Error", "Fail to get data from Account fragment: " + e);
        }

    }

    DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference("account")
            .child(AppUtil.DATA_OBJECT).child(AppUtil.USERNAME_AFTER_LOGGIN);

    private boolean nameIsChanged(){
        String name = edtNameEditInfo.getText().toString().trim();
        if(name.equals(oName)){
            return false;
        }
        else{
            if(!isComeback){
                databaseReference.child(AppUtil.FB_FULLNAME).setValue(name);
            }
            Log.d("TAG2", "nameIsChanged: ");
            return true;
        }
    }


    private boolean genderIsChanged(){
        String gender = null;
        int avatar = 0;
        if(radMale.isChecked())
        {
            gender = "Male";
            avatar = R.drawable.img_avatar_male;
        }
        if(radFemale.isChecked()){
            gender = "Female";
            avatar = R.drawable.img_avatar_female;
        }

        if(gender.equals(oGender)){
            return false;
        }
        else{
            if(!isComeback){
                databaseReference.child(AppUtil.FB_GENDER).setValue(gender);
                if(bitmap==null){
                    databaseReference.child(AppUtil.FB_AVATAR).setValue(avatar);
                }
            }
            Log.d("TAG2", "genderIsChanged: ");
            return true;
        }
    }
    private boolean birthIsChanged(){
        String birth = edtDateofbirth.getText().toString().trim();
        if(birth.equals(oBirth)){
            return false;
        }
        else{
            if(!isComeback){
                databaseReference.child(AppUtil.FB_DATE_OF_BIRTH).setValue(birth);
            }
            Log.d("TAG2", "birthIsChanged: ");
            return true;
        }
    }
    private boolean idIsChanged(){
        String id = edtIdStudent.getText().toString().trim();
        if(id.equals(oID)){
            return false;
        }
        else{
            if(!isComeback){
                databaseReference.child(AppUtil.FB_ID).setValue(id);
            }
            Log.d("TAG2", "idIsChanged: ");
            return true;
        }
    }

    private boolean facultyIsChanged(){
        String faculty = getListFaculty().get(selectedFaculty).getNameFaculty();
        if(faculty.equals(oFaculty)){
            return false;
        }
        else{
            if(!isComeback){
                databaseReference.child(AppUtil.FB_FACULTY).setValue(faculty);
            }
            Log.d("TAG2", "facultyIsChanged: " + facultyAdapter.getItemAtPostion(getListFaculty()));
            return true;
        }
    }

    private boolean majorIsChanged(){
        String major = adtMajor.getText().toString().trim();
        if(major.equals(oMajor)){
            return false;
        }
        else{
            if(!isComeback){
                databaseReference.child(AppUtil.FB_MAJOR).setValue(major);
            }
            Log.d("TAG2", "majorIsChanged: " );
            return true;
        }
    }
    private boolean phoneIsChanged(){
        String phone = edtPhone.getText().toString().trim();
        if(phone.equals(oPhone)){
            return false;
        }
        else{
            if(!isComeback){
                databaseReference.child(AppUtil.FB_PHONE).setValue(phone);
            }
            return true;
        }
    }






    // Sự kiện addEvents() và các sự kiện khác
    private void comeBackScreen() {
        isComeback = true;
        if(!nameIsChanged() && !genderIsChanged() && !birthIsChanged() && !idIsChanged() && !facultyIsChanged() &&
                !majorIsChanged() && !phoneIsChanged()){
            Log.d("TAG", "updateDataFireBase: all data is not changed" );
            finish();
        }
        else{
            CustomDialogTwoButton customDialogTwoButton =
                    new CustomDialogTwoButton(EditInfomationScreen.this,R.layout.custom_dialog_unsaved_changes);
            customDialogTwoButton.btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    customDialogTwoButton.dismiss();
                    finish();
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
    }
    @Override
    public void onBackPressed() {
        comeBackScreen();
    }

    private void addEvents() {

        imvComebackEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               comeBackScreen();
            }
        });
        spnFaculty.setSpinnerEventsListener(EditInfomationScreen.this);
        spnFaculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txtErrorFaculty.setText(null);
                spnFaculty.setBackgroundResource(R.drawable.custom_edt);
                imvFaculty.setImageDrawable(getResources().getDrawable(R.drawable.ic_faculty));
                imvDropdown.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down_spinner));
                txtErrorFaculty.setTextSize(0);
                selectedFaculty = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validatePhoneNumber();
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
                        EditInfomationScreen.this,
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
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateIDStudent()|!validateMajor()|!validateDateOfbirth()|!validatePhoneNumber())
                {
                    clearAllForcus();
                }
                else {
                    clearAllForcus();
                    uploadProfileImages();
                    updateDataFireBase();
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
    private void runCheckChange(){
        nameIsChanged();
        genderIsChanged();
        birthIsChanged();
        idIsChanged();
        facultyIsChanged();
        majorIsChanged();
        phoneIsChanged();
    }

    private void updateDataFireBase() {
        isComeback=false;
        runCheckChange();
        if(!nameIsChanged() && !genderIsChanged() && !birthIsChanged() && !idIsChanged() && !facultyIsChanged() &&
                !majorIsChanged() && !phoneIsChanged()){
            Log.d("TAG", "updateDataFireBase: all data is not changed" );
            finish();
        }
        else{
            CustomDialog customDialog = new
                    CustomDialog(EditInfomationScreen.this, R.layout.custom_dialog_save_editifor_change);
            customDialog.btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    customDialog.dismiss();
                    finish();
                }
            });
            Log.d("TAG", "updateDataFireBase:  something is change");
            customDialog.show();
        }
    }



    private void clearAllForcus(){
        edtIdStudent.clearFocus();
        adtMajor.clearFocus();
        edtDateofbirth.clearFocus();
        spnFaculty.clearFocus();
    }
    private void setCustomColortxt(TextView txtCanSua, int edtColor, int iconColor, int textColor){
        // Chỉnh màu cho thanh eTit text khi gặp error, focus, ...

        txtCanSua.setBackground(ContextCompat.getDrawable(EditInfomationScreen.this,edtColor));
        txtCanSua.setCompoundDrawableTintList(ContextCompat.getColorStateList(EditInfomationScreen.this,iconColor));
        txtCanSua.setTextColor(ContextCompat.getColorStateList(EditInfomationScreen.this,textColor));
    }


    // Tạo sự kiện thêm và đóng spinner
    @Override
    public void onPopupWindowOpened(Spinner spinner) {
        imvDropdown.setImageResource(R.drawable.ic_arrrow_dropdown_up);
    }
    @Override
    public void onPopupWindowClosed(Spinner spinner) {
        imvDropdown.setImageResource(R.drawable.ic_arrow_down_spinner);
    }

    // Tạo sự kiện chụp ảnh
    ActivityResultLauncher<Intent> activityResultLauncher;
    boolean isCamera;
    Bitmap bitmap = null;
    Uri uri;

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000,true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage, "Title", null);
        return Uri.parse(path);
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

    StorageReference storageReference;
    FirebaseAuth auth;
    StorageTask uploadTask;
    String myUri = "";
    private void uploadProfileImages() {
        storageReference =FirebaseStorage.getInstance().getReference().child("Profile Images");
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
            Log.d("TAG", "Images not selected");
        }
    }

    private void getUserImages(){
        databaseReference.child(AppUtil.FB_IMAGES_BITMAP).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    try {
                        String image = snapshot.getValue().toString();
                        if(!image.isEmpty()&&!image.equals("Null")){
                            Glide.with(getApplicationContext()).load(image).into(imvAvatar);
                        }
                        else {
                            changeDefaultAvatar();
                        }
                    }
                    catch (Exception e){
                        Log.d("Error", "Fail to get image from Firebase: " + e);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error", "onCancelled: " + error.toString());

            }
        });
    }

    private void changeDefaultAvatar() {
        radFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(bitmap==null){
                        imvAvatar.setImageResource(R.drawable.img_avatar_female);
                    }
                }
            }
        });
        radMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(bitmap==null){
                        imvAvatar.setImageResource(R.drawable.img_avatar_male);
                    }
                }
            }
        });
    }

    private static final int STORAGE_REQUEST_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST_PERMISSION_CODE = 200;

    private void pickImageAction() {
        if(isCamera){
            if(ContextCompat.checkSelfPermission(EditInfomationScreen.this,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(EditInfomationScreen.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {
                pickCamera();
            }
            else{
                requestCameraPermission();
            }
        }
        else{
            if(ContextCompat.checkSelfPermission(EditInfomationScreen.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                pickGallery();
            }
            else{
                requestStoragePermission();
            }

        }
    }
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(EditInfomationScreen.this,new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                STORAGE_REQUEST_PERMISSION_CODE);
    }
    private void requestCameraPermission() {
        String[] permission = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(EditInfomationScreen.this,permission,
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
        try {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            activityResultLauncher.launch(cameraIntent);
        }
        catch (Exception e){
            Log.d("Error", "Fail to pickCamera in EditInformationScreen: " + e);
        }

    }
    private void pickGallery(){
        try {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            activityResultLauncher.launch(intent);
        }
        catch (Exception e){
            Log.d("Error", "Fail to pickGallery in EditInformationScreen: " + e);
        }
    }


    private void cameraPickImage() {

        CustomDialogThreeButton customDialogThreeButton = new CustomDialogThreeButton
                (EditInfomationScreen.this,R.layout.custom_dialog_chooss_image);
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

    //Tạo sự kiện pick datetime
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

    //Tạo sự kiện Validate
    private Boolean validateIDStudent(){
        String username = edtIdStudent.getText().toString().trim();
        if (username.isEmpty()){
            txtErrorIdStudent.setText(R.string.field_cannot_be_empty);
            txtErrorIdStudent.setTextSize(15);
            edtIdStudent.setHintTextColor(getColor(R.color.red));
            reusedConstraint.setCustomColor(edtIdStudent,R.drawable.edt_custom_error,R.color.red,R.color.red);
            return false;
        }

//

        else {
            reusedConstraint.setCustomColor(edtIdStudent,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            edtIdStudent.setHintTextColor(getColor(R.color.xamChu));

            txtErrorIdStudent.setText(null);
            txtErrorIdStudent.setTextSize(0);
            return true;
        }

    }
    private Boolean validatePhoneNumber(){
        String username = edtPhone.getText().toString().trim();
        if (username.isEmpty()){
            txtErrorPhone.setText(R.string.field_cannot_be_empty);
            txtErrorPhone.setTextSize(15);
            edtPhone.setHintTextColor(getColor(R.color.red));
            reusedConstraint.setCustomColor(edtPhone,R.drawable.edt_custom_error,R.color.red,R.color.red);
            return false;
        }

//

        else {
            reusedConstraint.setCustomColor(edtPhone,R.drawable.custom_edt,R.color.blackUI,R.color.xamChu);
            edtPhone.setHintTextColor(getColor(R.color.xamChu));
            txtErrorPhone.setText(null);
            txtErrorPhone.setTextSize(0);
            return true;
        }

    }
    private Boolean validateMajor(){
        String username = adtMajor.getText().toString().trim();
        if (username.isEmpty()){
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


    //Các sự kiện nạp adapter
    MajorAdapter majorAdapter;
    private void initAderterMarjor() {
        majorAdapter = new MajorAdapter(this,R.layout.item_faculty_selected,getListMajor());
        adtMajor.setAdapter(majorAdapter);

    }
    private List<Major> getListMajor() {
        List<Major> list = new ArrayList<>();
        String[] majorArray= getResources().getStringArray(R.array.major);

        for (int i = 0;i<majorArray.length;i++)
            list.add(new Major(majorArray[i]));

        return list;
    }

    public static int selectedFaculty = 0;
    FacultyEditAdapter facultyAdapter;
    private void initAdapterFaculty() {
        facultyAdapter = new FacultyEditAdapter(this,R.layout.item_faculty_selected,getListFaculty());
        spnFaculty.setAdapter(facultyAdapter);



    }
    private List<Faculty> getListFaculty() {
        List<Faculty> list =new ArrayList<>();
        String[] facultyArray= getResources().getStringArray(R.array.facultyEditInfo);

        for (int i = 0;i<facultyArray.length;i++)
            list.add(new Faculty(facultyArray[i]));

        return list;

    }

}