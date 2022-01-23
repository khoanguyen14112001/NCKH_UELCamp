package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.customdialog.CustomDialog;
import nguyenhoanganhkhoa.com.customdialog.CustomDialogNotify;
import nguyenhoanganhkhoa.com.models.Notification;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.AllNotificationScreen;
import nguyenhoanganhkhoa.com.myapplication.home.UnreadFNoticeFragment;

public class DialogNotificationAdapter extends RecyclerView.Adapter<DialogNotificationAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<Notification> mNotification;
    private List<Notification> mNotificationOld;


    private int layoutItem;

    public DialogNotificationAdapter(Context context, int layoutItem) {

        this.context = context;
        this.layoutItem = layoutItem;
    }

    public void setData(List<Notification> list){
        this.mNotification = list;
        if(layoutItem == R.layout.item_notification_all_bold){
            this.mNotificationOld = list;
        }
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public DialogNotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layoutItem,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DialogNotificationAdapter.ViewHolder holder, int position) {
        Notification notification = mNotification.get(position);
        if(notification ==null)
        {
            return;
        }
        holder.txtNotificationDate.setText(notification.getNotificationDate());
        holder.txtNotificationContent.setText(notification.getNotificationContent());
        holder.imvThumbNotification.setImageResource(notification.getNotificationThumb());

        changeReadImage(holder);
        addEvents(holder, position);

    }


    private void addEvents(DialogNotificationAdapter.ViewHolder holder, int position) {

        if(layoutItem==R.layout.item_notification)
        {
            holder.layout_item_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeNotice(holder);
                }
            });
        }
        if(layoutItem==R.layout.item_notification_all_bold)
        {
            holder.layout_item_notification_all_bold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeNotice(holder);
                    displayFullNotification(position);
                }
            });
            holder.imbMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context wrapper = new ContextThemeWrapper(context,R.style.PopUpCustomStyle);
                    PopupMenu popupMenu = new PopupMenu(wrapper,holder.imbMore);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_edit_notification,popupMenu.getMenu());

                    if(changeReadImage(holder)){
                        popupMenu.getMenu().findItem(R.id.mnMarkAsRead).setTitle("Mark as unread");
                    }
                    else{
                        popupMenu.getMenu().findItem(R.id.mnMarkAsRead).setTitle("Mark as read");
                    }
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if(menuItem.getItemId()==R.id.mnMarkAsRead){
                                if(menuItem.getTitle()=="Mark as read"){
                                    changeNotice(holder);
                                }
                                else{
                                    changeNoticeToUnread(holder);
                                }
                            }
                            if(menuItem.getItemId()==R.id.mnDelete){
                                deleteItem(position);
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }
            });
        }
    }

    private void displayFullNotification(int position) {
        CustomDialogNotify dialog = new CustomDialogNotify(context,R.layout.custom_dialog_display_notify);
        dialog.txtText.setText(mNotification.get(position).getNotificationContent());
        dialog.txtDate.setText(mNotification.get(position).getNotificationDate());
        dialog.btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void deleteItem (int position){
        try {
            mNotification.remove(position);
            notifyItemRemoved(position);
            // dòng này để load lại dữ liệu sau khi đã xóa lên
            notifyItemRangeChanged(position, mNotification.size());
        }
        catch (Exception e){
            Log.d("Error", "Fail to delete item in Notification Adapter: " + e);
            Toast.makeText(context, "Delete notification fail!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean changeReadImage(DialogNotificationAdapter.ViewHolder holder) {
        Drawable.ConstantState drawable = holder.imvThumbNotification.getDrawable().getConstantState();
        Drawable.ConstantState drawable1 = context.getResources().getDrawable(R.drawable.img_notice).getConstantState();
        Drawable.ConstantState drawable2 = context.getResources().getDrawable(R.drawable.img_nomoney_notice).getConstantState();
        if(drawable==drawable1 || drawable==drawable2)
        {
            Typeface typeface = context.getResources().getFont(R.font.be_vietnam_light);
            holder.txtNotificationContent.setTypeface(typeface);
            return true;
        }
        else{
            Typeface typeface = context.getResources().getFont(R.font.be_vietnam_bold);
            holder.txtNotificationContent.setTypeface(typeface);
            return false;
        }
    }

    private void changeNotice(DialogNotificationAdapter.ViewHolder holder) {
        Typeface typeface = context.getResources().getFont(R.font.be_vietnam_light);
        holder.txtNotificationContent.setTypeface(typeface);

        Drawable.ConstantState drawable = holder.imvThumbNotification.getDrawable().getConstantState();
        Drawable.ConstantState drawable1 = context.getResources().getDrawable(R.drawable.img_newnotice).getConstantState();
        Drawable.ConstantState drawable2 = context.getResources().getDrawable(R.drawable.ic_img_nomoney_notice_new).getConstantState();

        if(drawable == drawable1)
        {
            holder.imvThumbNotification.setImageResource(R.drawable.img_notice);
        }
        if(drawable == drawable2)
        {
            holder.imvThumbNotification.setImageResource(R.drawable.img_nomoney_notice);
        }

    }

    private void changeNoticeToUnread(DialogNotificationAdapter.ViewHolder holder) {
        Typeface typeface = context.getResources().getFont(R.font.be_vietnam_bold);
        holder.txtNotificationContent.setTypeface(typeface);

        Drawable.ConstantState drawable = holder.imvThumbNotification.getDrawable().getConstantState();
        Drawable.ConstantState drawable1 = context.getResources().getDrawable(R.drawable.img_notice).getConstantState();
        Drawable.ConstantState drawable2 = context.getResources().getDrawable(R.drawable.img_nomoney_notice).getConstantState();

        if(drawable == drawable1)
        {
            holder.imvThumbNotification.setImageResource(R.drawable.img_newnotice);
        }
        if(drawable == drawable2)
        {
            holder.imvThumbNotification.setImageResource(R.drawable.ic_img_nomoney_notice_new);
        }

    }

    @Override
    public int getItemCount() {
        if(mNotification !=null)
            return mNotification.size();
        else
            return 0;
    }



    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                if(layoutItem == R.layout.item_notification_all_bold){
                    String textSearch = charSequence.toString();
                    if(textSearch.isEmpty()){
                        mNotification = mNotificationOld;
                    }
                    else{
                        List<Notification> list = new ArrayList<>();
                        for(Notification notification: mNotificationOld){
                            if(notification.getNotificationContent().toLowerCase()
                                    .contains(textSearch.toLowerCase().trim())){
                                list.add(notification);
                            }
                        }
                        mNotification = list;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mNotification;
                    return filterResults;

                }
                return null;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if(layoutItem ==R.layout.item_notification_all_bold){
                    mNotification = (List<Notification>) filterResults.values;
                    notifyDataSetChanged();
                }
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNotificationDate,txtNotificationContent;
        ImageView imvThumbNotification;
        ConstraintLayout layout_item_notification,layout_item_notification_all_bold;
        ImageButton imbMore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNotificationDate = itemView.findViewById(R.id.txtNotificationDate);
            txtNotificationContent = itemView.findViewById(R.id.txtNotificationContent);
            imvThumbNotification = itemView.findViewById(R.id.imvThumbNotification);
            layout_item_notification = itemView.findViewById(R.id.layout_item_notification);
            layout_item_notification_all_bold = itemView.findViewById(R.id.layout_item_notification_all_bold);
            imbMore = itemView.findViewById(R.id.imbMore);
        }
    }
}
