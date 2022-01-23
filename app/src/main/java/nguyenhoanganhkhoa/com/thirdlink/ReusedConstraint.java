package nguyenhoanganhkhoa.com.thirdlink;

import android.content.Context;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import nguyenhoanganhkhoa.com.adapter.DialogNotificationAdapter;
import nguyenhoanganhkhoa.com.myapplication.R;

public class ReusedConstraint {
    Context context;

    public ReusedConstraint(Context context) {
        this.context = context;
    }

    public void prepareDots(Context context, int lengthList, LinearLayout layout, TextView dots[],
                                   int size) {
        for(int i = 0; i<lengthList;i++)
        {
            dots[i] = new TextView(context);
            dots[i].setText(Html.fromHtml("&#9679;"));
            dots[i].setTextSize(size);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(6,0,6,0);
            layout.addView(dots[i],layoutParams);
        }
    }
    public void seletedIndicator(TextView dots[], int position) {
        for (int i =0; i<dots.length;i++)
        {
            if(i==position)
            {
                dots[i].setTextColor(context.getResources().getColor(R.color.primary_yellow));

            }
            else {
                dots[i].setTextColor(context.getResources().getColor(R.color.xamChu));
            }
        }
    }

    public void showHidePassword(EditText edtPass, View view ) {
        // PasswordTransformationMethod.getInstance()) : Password dạng *
        // HideReturnsTransformationMethod.getInstance(): Password dạng hiện
        if(edtPass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
            //set con mắt mở ra
            ((ImageView)(view)).setImageResource(R.drawable.ic_open_toggle);
            //Show Password
            edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        }
        else{
            ((ImageView)(view)).setImageResource(R.drawable.ic_close_toggle);
            //Hide Password
            edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

    }


    public void setCustomColor(EditText edtCanSua,
                               int edtColor, int iconColor, int textColor) {
        // Chỉnh màu cho thanh edit text khi gặp error, focus, ...

        edtCanSua.setBackground(context.getDrawable(edtColor));
        edtCanSua.setCompoundDrawableTintList(context.getColorStateList(iconColor));
        edtCanSua.setTextColor(context.getColorStateList(textColor));
    }

    public void changeColor(TextView text, int numStart, int numEnd, int ColorChange) {
        String textVerifcation = text.getText().toString();

        SpannableString ss = new SpannableString(textVerifcation) ;
        ForegroundColorSpan fcsYellow = new ForegroundColorSpan(context.getColor(ColorChange));
        ss.setSpan(fcsYellow,numStart,numEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        StyleSpan typefaceSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(typefaceSpan,numStart,numEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        text.setText(ss);
    }

    public void addTimer(TextView txt, int time) {
        new CountDownTimer(time, 10) {
            public void onTick(long millisUntilFinished) {

                long remainedSecs = millisUntilFinished / 1000;
                txt.setText(remainedSecs + "s");
            }
            @Override
            public void onFinish() {
            }
        }.start();

    }

    public void addSearchForNotification(SearchView searchView, DialogNotificationAdapter adapter) {
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(adapter!=null){
                    try {
                        adapter.getFilter().filter(query);
                    }
                    catch (Exception e){
                        Log.d("Error", "onQueryTextSubmit: " + e);
                    }
                }
                else{
                    Log.d("SearchMethod", "adapter is null");
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(adapter!=null){
                    try {
                        adapter.getFilter().filter(newText);
                    }
                    catch (Exception e){
                        Log.d("Error", "onQueryTextSubmit: " + e);
                    }
                }
                else{
                    Log.d("SearchMethod", "adapter is null");
                }
                return false;
            }
        });
    }


    public String formatCurrency (double number){
        NumberFormat formatter = new DecimalFormat("#,###");
        return formatter.format(number);
    }

    public String formatCurrency (String number){
        double num = Double.parseDouble(number);
        NumberFormat formatter = new DecimalFormat("#,###");
        return formatter.format(num);
    }




}
