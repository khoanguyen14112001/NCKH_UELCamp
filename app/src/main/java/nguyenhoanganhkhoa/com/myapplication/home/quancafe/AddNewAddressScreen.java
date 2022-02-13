package nguyenhoanganhkhoa.com.myapplication.home.quancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class AddNewAddressScreen extends AppCompatActivity {

    ImageView imvRadCheck;
    boolean isClicked = false;
    Button btnSave;
    EditText edtFullname, edtPhone, edtPlace;
    TextView txtErrorFullname, txtErrorPhone, txtErrorPlace;

    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    private void linkView() {
        imvRadCheck = findViewById(R.id.imvRadCheck);
        btnSave = findViewById(R.id.btnSave);
        edtFullname = findViewById(R.id.edtFullname);
        edtPhone = findViewById(R.id.edtPhone);
        edtPlace = findViewById(R.id.edtPlace);
        txtErrorFullname = findViewById(R.id.txtErrorFullname);
        txtErrorPhone = findViewById(R.id.txtErrorPhone);
        txtErrorPlace = findViewById(R.id.txtErrorPlace);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address_screen);

        linkView();
        addEvents();
    }
    private boolean validate(EditText edtInput){
        String text = edtInput.getText().toString().trim();
        if (text.isEmpty()){
            return false;
        }

        else {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void addEvents() {
        reusedConstraint.openNav(this);
        reusedConstraint.setActionComeBack(this);

        imvRadCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClicked){
                    imvRadCheck.setImageResource(R.drawable.ic_rad_red_unchecked);
                    isClicked = false;
                }
                else{
                    imvRadCheck.setImageResource(R.drawable.ic_rad_red_checked);
                    isClicked = true;
                }
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrectName = validate(edtFullname);
                boolean isCorrectPhone = validate(edtPhone);
                boolean isCorrectPlace = validate(edtPlace);
                if(isCorrectName && isCorrectPhone && isCorrectPlace){
                    finish();
                }
                else{
                    Toast.makeText(AddNewAddressScreen.this, "Please input all required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}