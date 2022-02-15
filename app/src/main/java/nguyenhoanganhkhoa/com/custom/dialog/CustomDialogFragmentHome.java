package nguyenhoanganhkhoa.com.custom.dialog;

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

import nguyenhoanganhkhoa.com.adapter.NotificationAdapter;
import nguyenhoanganhkhoa.com.models.Notification;
import nguyenhoanganhkhoa.com.myapplication.R;

public class CustomDialogFragmentHome extends Dialog {
    public Button btnSeeAll;
    Activity activity;
    RecyclerView rcvNotification;
    NotificationAdapter dialogNotificationAdapter;

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

        dialogNotificationAdapter = new NotificationAdapter(getContext(),R.layout.item_notification,1);
        rcvNotification.setAdapter(dialogNotificationAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcvNotification.setLayoutManager(linearLayoutManager);

        dialogNotificationAdapter.setData(getListNotifications());
        rcvNotification.setAdapter(dialogNotificationAdapter);



    }

    private List<Notification> getListNotifications() {
        List<Notification> list = new ArrayList<>();
        list.add(new Notification(NotificationAdapter.NOTIFICATION_FRIEND,
                "Tường Vy has accepted your friend request",
                "13 Jan, 19:04",
                true,
                true,
                NotificationAdapter.NOTIFICATION_GENDER_MALE
        ));

        list.add(new Notification(NotificationAdapter.NOTIFICATION_FRIEND,
                "Anh Khoa has accepted your friend request",
                "13 Jan, 19:04",
                false,
                true,
                NotificationAdapter.NOTIFICATION_GENDER_FEMALE
        ));

        list.add(new Notification(NotificationAdapter.NOTIFICATION_PARKING,
                "Due to the Christmas holiday, the parking lot will not be open",
                "13 Jan, 19:04",
                true
        ));

        list.add(new Notification(NotificationAdapter.NOTIFICATION_WALLET,
                "You currently do not have enough money to pay, please top up",
                "13 Jan, 19:04",
                false
        ));

        return list;
    }




    public void linkView() {
        btnSeeAll = findViewById(R.id.btnSeeAll);
        rcvNotification = findViewById(R.id.rcvNotification);
    }
}
