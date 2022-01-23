package nguyenhoanganhkhoa.com.customdialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.DialogNotificationAdapter;
import nguyenhoanganhkhoa.com.models.Notification;
import nguyenhoanganhkhoa.com.myapplication.R;

public class CustomDialogFragmentHome extends Dialog {
    public Button btnSeeAll;
    Activity activity;
    RecyclerView rcvNotification;
    DialogNotificationAdapter dialogNotificationAdapter;

    public CustomDialogFragmentHome(@NonNull Activity activity, int dialogLayout) {

        super(activity);
        this.activity =  activity;

        configureDialogWindow(dialogLayout);
        linkView();
        initAdapter();

        // Decorate window dialog


    }

    private void configureDialogWindow(int dialogLayout) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(dialogLayout);

        Window window = this.getWindow();
        if (window == null){
            return;
        }
        getScreenWidth(activity);
        window.setLayout((int) (getScreenWidth(activity) * .75),
                WindowManager.LayoutParams.WRAP_CONTENT);

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtributes = window.getAttributes();
        windowAtributes.gravity = Gravity.TOP|Gravity.RIGHT;
        windowAtributes.x = 30;
        windowAtributes.y = 160;
        window.setAttributes(windowAtributes);
    }

    public static int getScreenWidth(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }



    private void initAdapter() {

        dialogNotificationAdapter = new DialogNotificationAdapter(getContext(),R.layout.item_notification);
        rcvNotification.setAdapter(dialogNotificationAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcvNotification.setLayoutManager(linearLayoutManager);

        dialogNotificationAdapter.setData(getListNotifications());
        rcvNotification.setAdapter(dialogNotificationAdapter);



    }

    private List<Notification> getListNotifications() {
        List<Notification> list = new ArrayList<>();
        list.add(new Notification(R.drawable.img_newnotice,"The parking lot will be under " +
                "maintenance from 14, Jan to 20, Jan","13 Jan, 19:04"));
        list.add(new Notification(R.drawable.img_newnotice,"Due to the Christmas holiday, " +
                "the parking lot will not be open","23 Dec, 18:35"));
        list.add(new Notification(R.drawable.img_notice,"The parking lot will be under " +
                "maintenance from 11 Nov to 14 Nov","13 Jan, 19:04"));
        list.add(new Notification(R.drawable.img_nomoney_notice,"You currently do not have enough " +
                "money to pay, please top up","03 Nov, 17:05"));

        return list;
    }




    public void linkView() {
        btnSeeAll = findViewById(R.id.btnSeeAll);
        rcvNotification = findViewById(R.id.rcvNotification);
    }
}
