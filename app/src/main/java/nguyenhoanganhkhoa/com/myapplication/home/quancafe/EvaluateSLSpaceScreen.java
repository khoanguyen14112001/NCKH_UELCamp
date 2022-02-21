package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.EvaluateAdapter;
import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogOneButtonNew;
import nguyenhoanganhkhoa.com.custom.dialog.CustomDialogThreeButton;
import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.models.DrinkInCart;
import nguyenhoanganhkhoa.com.models.Images;
import nguyenhoanganhkhoa.com.models.ImagesVideoEvaluate;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.signup.PersonalInformationSetScreen;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class EvaluateSLSpaceScreen extends AppCompatActivity {

    RecyclerView rcvEvaluateItem;
    Button btnEvaluate;
    EvaluateAdapter adapter = new EvaluateAdapter(this);
    ActivityResultLauncher<Intent> activityResultLauncher;

    ReusedConstraint reusedConstraint = new ReusedConstraint();


    private void linkView() {
        rcvEvaluateItem = findViewById(R.id.rcvEvaluateItem);
        btnEvaluate = findViewById(R.id.btnEvaluate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_slspace_screen);

        linkView();
        initAdapter();
        addResultLauncher();
        addEvents();


    }




    private List<Drink> getListDrinkPurchaseDetail(){
        return (List<Drink>) getIntent().getSerializableExtra(AppUtil.MY_BUNDLE_TRANS);
    }

    private void initAdapter() {
        adapter.setCallBack(new EvaluateAdapter.MyCallBack() {
            @Override
            public void openCamera(boolean isImage) {
                if(isImage){
                    captureAction();
                }
                else{
                    takeVideoAction();
                }
            }
        });
        adapter.setData(getListDrinkPurchaseDetail());
        rcvEvaluateItem.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvEvaluateItem.setAdapter(adapter);
    }

    private void takeVideoAction() {
        CustomDialogThreeButton customDialogThreeButton = new CustomDialogThreeButton
                (this,R.layout.custom_dialog_chooss_image);
        customDialogThreeButton.txtHeaderDialog.setText(R.string.select_video);
        customDialogThreeButton.btnTakePhotos.setText(R.string.take_video);
        customDialogThreeButton.btnTakePhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ACTION = ACTION_CAPTURE_VIDEO;
                pickImageAction();
                customDialogThreeButton.dismiss();

            }
        });

        customDialogThreeButton.btnChooseFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ACTION = ACTION_PICK_GALLERY_VIDEO;
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
    private void captureAction() {

        CustomDialogThreeButton customDialogThreeButton = new CustomDialogThreeButton
                (this,R.layout.custom_dialog_chooss_image);
        customDialogThreeButton.txtHeaderDialog.setText(R.string.select_picture);
        customDialogThreeButton.btnTakePhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ACTION = ACTION_TAKE_IMAGE;
                pickImageAction();
                customDialogThreeButton.dismiss();

            }
        });

        customDialogThreeButton.btnChooseFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ACTION = ACTION_PICK_GALLERY;
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

    private static final int ACTION_TAKE_IMAGE = 10;
    private static final int ACTION_PICK_GALLERY = 20;
    private static final int ACTION_CAPTURE_VIDEO = 30;
    private static final int ACTION_PICK_GALLERY_VIDEO = 40;

    private int ACTION;


    Bitmap bitmap = null;
    Uri uri;
    private void addResultLauncher() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                    if(ACTION == ACTION_TAKE_IMAGE){
                        bitmap = (Bitmap) result.getData().getExtras().get("data");
                        checkConditionToAddItemForImages();
                    }
                    if(ACTION == ACTION_PICK_GALLERY){
                        uri = result.getData().getData();
                        if(uri !=null){
                            try {
                                InputStream inputStream = getContentResolver().openInputStream(uri);
                                bitmap = BitmapFactory.decodeStream(inputStream);
                                checkConditionToAddItemForImages();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else{
                        uri = result.getData().getData();
                        checkConditionToAddItemForVideos();
                    }
                }
            }
        });
    }

    private void checkConditionToAddItemForImages(){
        if(adapter.getImagesList()==null){
            ImagesVideoEvaluate imagesEvaluate = new ImagesVideoEvaluate(bitmap);
            imagesEvaluate.setImage(true);
            adapter.addImageEvaluate(imagesEvaluate);
        }
        else{
            int k = 0;
            for(int i =0; i<adapter.getImagesList().size();i++){
                ImagesVideoEvaluate image = adapter.getImagesList().get(i);
                if(image.isImage()){
                    k ++;
                }
            }
            if(k < 5){
                ImagesVideoEvaluate imagesEvaluate = new ImagesVideoEvaluate(bitmap);
                imagesEvaluate.setImage(true);
                adapter.addImageEvaluate(imagesEvaluate);
            }
        }
    }
    private void checkConditionToAddItemForVideos(){
        if(adapter.getImagesList()==null){
            ImagesVideoEvaluate videoEvaluate = new ImagesVideoEvaluate(uri);
            videoEvaluate.setImage(false);
            adapter.addImageEvaluate(videoEvaluate);
        }
        else{
            int k = 0;
            for(int i =0; i<adapter.getImagesList().size();i++){
                ImagesVideoEvaluate image = adapter.getImagesList().get(i);
                if(!image.isImage()){
                    k ++;
                }
            }
            if(k < 1){
                ImagesVideoEvaluate videoEvaluate = new ImagesVideoEvaluate(uri);
                videoEvaluate.setImage(false);
                adapter.addImageEvaluate(videoEvaluate);
            }
        }
    }

    private static final int STORAGE_REQUEST_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST_PERMISSION_CODE = 200;

    private void pickImageAction() {
        if(ACTION == ACTION_TAKE_IMAGE){
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {
                pickCamera();
            }
            else{
                requestCameraPermission();
            }
        }
        if(ACTION == ACTION_PICK_GALLERY){
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                pickGallery();
            }
            else{
                requestStoragePermission();
            }
        }
        if(ACTION==ACTION_CAPTURE_VIDEO){
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {
                pickVideo();
            }
            else{
                requestCameraPermission();
            }
        }
        if(ACTION==ACTION_PICK_GALLERY_VIDEO){
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                pickGalleryVideo();
            }
            else{
                requestStoragePermission();
            }
        }
    }
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                STORAGE_REQUEST_PERMISSION_CODE);
    }
    private void requestCameraPermission() {
        String[] permission = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this,permission,
                CAMERA_REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == STORAGE_REQUEST_PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission read storage granted",Toast.LENGTH_LONG).show();
                if(ACTION == ACTION_PICK_GALLERY){
                    pickGallery();
                }
                if(ACTION==ACTION_PICK_GALLERY_VIDEO){
                    pickGalleryVideo();
                }
            }else{
                Toast.makeText(this,"Permission read storage denied",Toast.LENGTH_LONG).show();
            }
        }

        if(requestCode == CAMERA_REQUEST_PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                if (ACTION == ACTION_TAKE_IMAGE) {
                    pickCamera();
                }
                if (ACTION ==ACTION_CAPTURE_VIDEO){
                    pickVideo();
                }
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

    private void pickGalleryVideo(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        activityResultLauncher.launch(intent);
    }


    private void pickVideo(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        activityResultLauncher.launch(cameraIntent);
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void addEvents() {
        reusedConstraint.setActionComeBack(this);
        reusedConstraint.openNav(this);
        btnEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogOneButtonNew dialog = new CustomDialogOneButtonNew(EvaluateSLSpaceScreen.this);
                dialog.txtTitleDialog.setText(R.string.evaluate_successfully);
                dialog.txtContentDialog.setText(getString(R.string.evaluate_successfully_content));
                dialog.btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        finish();
                    }
                });
                dialog.show();
            }
        });
    }
}