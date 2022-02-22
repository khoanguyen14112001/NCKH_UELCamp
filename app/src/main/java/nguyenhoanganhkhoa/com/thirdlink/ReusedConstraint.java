package nguyenhoanganhkhoa.com.thirdlink;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import nguyenhoanganhkhoa.com.adapter.NotificationAdapter;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.custom.rightnav.LeftNavFragment;

public class ReusedConstraint implements Serializable {
    Context context;

    public ReusedConstraint(Context context) {
        this.context = context;
    }
    public ReusedConstraint() {
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

    public void changeColor(TextView text, int numStart, int numEnd, int ColorChange, Activity activity) {
        String textVerifcation = text.getText().toString();

        SpannableString ss = new SpannableString(textVerifcation) ;
        ForegroundColorSpan fcsYellow = new ForegroundColorSpan(activity.getColor(ColorChange));
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



    public void addSearchForNotification(SearchView searchView, NotificationAdapter adapter) {
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
    public String formatFloat (float number){
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("0.0", decimalFormatSymbols);

        String moneyAft = decimalFormat.format(number);

        return moneyAft;
    }


    public String formatCurrency (double number){
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0", decimalFormatSymbols);

        String moneyAft = decimalFormat.format(number);

        return moneyAft;
    }

    public String formatCurrency (String number){
        double num = Double.parseDouble(number);

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0", decimalFormatSymbols);

        String moneyAft = decimalFormat.format(num);

        return moneyAft;
    }


    public void openNav (Activity activity){
        ImageView imvOpenNav = activity.findViewById(R.id.imvOpenNav);
        DrawerLayout drawerLayout = activity.findViewById(R.id.drawerLayout);

        imvOpenNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.END)){
                    drawerLayout.closeDrawer(GravityCompat.END);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });

        ((FragmentActivity)activity).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layoutFragmentLeftNav, new LeftNavFragment())
                .commit();
    }

    public void checkNavStatusComeBack(Activity activity){
        DrawerLayout drawerLayout = activity.findViewById(R.id.drawerLayout);
        if(drawerLayout.isDrawerVisible(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }
        else{
            activity.finish();
        }
    }

    public void checkNavStatusComeBack(Activity activity, Class activityComeback){
        DrawerLayout drawerLayout = activity.findViewById(R.id.drawerLayout);
        if(drawerLayout.isDrawerVisible(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }
        else{
           Intent intent = new Intent(context, activityComeback);
           context.startActivity(intent);
        }
    }

    public void setActionComeBack(Activity activity){
        ImageView imvBack = activity.findViewById(R.id.imvBack);
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkNavStatusComeBack(activity);
            }
        });
    }



}
