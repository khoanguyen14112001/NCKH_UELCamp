package nguyenhoanganhkhoa.com.myapplication.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaos.view.PinView;

import nguyenhoanganhkhoa.com.customdialog.CustomDialog;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class VerificationSignupScreen extends AppCompatActivity {
    PinView pvVerificationSignUp;
    TextView txtErrorCodeSignUp,txtResendSignUp,txtVerificationTextSignUp;
    Button btnVerifySignUp;
    ImageView imvComebackSignUp;

    private void linkView() {
        pvVerificationSignUp = findViewById(R.id.pvVerificationSignUp);
        txtErrorCodeSignUp = findViewById(R.id.txtErrorCodeSignUp);
        btnVerifySignUp = findViewById(R.id.btnVerifySignUp);
        imvComebackSignUp = findViewById(R.id.imvComebackVerificationSignUp);
        txtResendSignUp = findViewById(R.id.txtResendCodeSignUp);
        txtVerificationTextSignUp= findViewById(R.id.txtVerificationCodeSignUp);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_signup_screen);

        linkView();
        addTimer();
        addEvents();
    }
    int time = 30000;

    private void addTimer() {


        new CountDownTimer(time, 10) {
            public void onTick(long millisUntilFinished) {

                long remainedSecs = millisUntilFinished / 1000;
                long secondLeft = remainedSecs % 60;
                txtVerificationTextSignUp.setText(getString(R.string.we_sent_your_code_to_your_email) +
                        getString(R.string.this_code_will_be_expired_in)+
                        (remainedSecs % 60) +
                        getString(R.string.seconds));

                // Set color seconds
                ReusedConstraint reusedConstraint = new ReusedConstraint(VerificationSignupScreen.this);
                reusedConstraint.changeColor(txtVerificationTextSignUp,62,64,R.color.primary_yellow);
                txtResendSignUp.setEnabled(false);
            }


            @Override
            public void onFinish() {
                txtVerificationTextSignUp.setText(getString(R.string.we_have_not_received_your_code_yet) +
                        getString(R.string.to_do_it_again_click_resend_below));
                txtResendSignUp.setEnabled(true);

            }
        }.start();
    }


    private Boolean validateCode() {
        String s = pvVerificationSignUp.getText().toString();
        if(s.isEmpty() | s.length()<4)
        {
            txtErrorCodeSignUp.setText(R.string.please_enter_four_numbers_of_code);
            txtErrorCodeSignUp.setTextSize(15);
            return false;
        }

        return true;
    }


    int attempt = 0;

    private void addEvents() {

        txtResendSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTimer();
            }
        });

        imvComebackSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnVerifySignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateCode())
                {
                    return;
                }

                else
                {
                    if (pvVerificationSignUp.getText().toString().equals(AppUtil.VERIFICATION_CODE_APP)) {
                       CustomDialog customDialog = new CustomDialog
                               (VerificationSignupScreen.this,R.layout.custom_dialog_verification_successful);
                       customDialog.btnOK.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Intent intent = new Intent(VerificationSignupScreen.this, PersonalInformationSetScreen.class);
                               startActivity(intent);
                           }
                       });
                       customDialog.show();

                    }
                    else if(attempt==5)
                    { AppUtil.eMessageForSignUp=AppUtil.LOCK_CONDITION_SIGNUP;
                        CustomDialog customDialog = new CustomDialog(VerificationSignupScreen.this,R.layout.custom_dialog_verification_2);
                        customDialog.btnOK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(VerificationSignupScreen.this, EmailScreen.class);
                                startActivity(intent);
                            }
                        });
                        customDialog.show();
                    }
                    else
                    {
                        CustomDialog customDialog = new CustomDialog(VerificationSignupScreen.this,R.layout.custom_dialog_verification);
                        customDialog.btnOK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customDialog.dismiss();
                            }
                        });
                        customDialog.show();
                        txtErrorCodeSignUp.setTextSize(0);
                        attempt++;
                    }
                }
            }
        });
    }
}