package nguyenhoanganhkhoa.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.ImagesVideoEvaluate;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.quancafe.EvaluateSLSpaceScreen;

public class ImagesVideoEvaluateAdapter extends RecyclerView.Adapter<ImagesVideoEvaluateAdapter.ViewHolder> {

    private Context context;
    private List<ImagesVideoEvaluate> mList;

    private int screen = EVALUATE_SCREEN;

    private boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public static final int EVALUATE_SCREEN = 10;
    public static final int PRODUCT_SCREEN = 20;

    private static final int VIEW_IMAGE = 1;
    private static final int VIEW_VIDEO = 2;


    public ImagesVideoEvaluateAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {
        if(mList.get(position).isImage()){
            return VIEW_IMAGE;
        }
        else{
            return VIEW_VIDEO;
        }
    }

    public void setData(List<ImagesVideoEvaluate> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImagesVideoEvaluateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if(viewType == VIEW_IMAGE){
            view = inflater.inflate(R.layout.item_evaluate_image,parent,false);
        }
        else{
            view = inflater.inflate(R.layout.item_evaluate_video,parent,false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesVideoEvaluateAdapter.ViewHolder holder, int position) {
        ImagesVideoEvaluate item = mList.get(position);
        if(item ==null)
        {
            return;
        }

        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                callBack.notifyDataChanged();
            }
        });

        Activity activity = (Activity)context;
        if(getScreen()==PRODUCT_SCREEN){
            if(getWindowWidth(activity)<850){
                holder.con1.setMinWidth(changeToPX(activity, 60));
                holder.con1.setMaxWidth(changeToPX(activity, 60));
                holder.con1.setMinHeight(changeToPX(activity, 60));
                holder.con1.setMaxHeight(changeToPX(activity, 60));
            }
            Log.d("TAG", "onBindViewHolder: " + getWindowWidth(activity));
            holder.imvDelete.setVisibility(View.GONE);
        }



        if(item.isImage()){
            if(item.getBitmap()==null){
                holder.imvImages.setImageResource(item.getImageInt());
            }
            else{
                holder.imvImages.setImageBitmap(item.getBitmap());
            }
        }
        else{
            holder.videoView.setVideoURI(item.getUri());
            MediaController mediaController = new MediaController(context);
            holder.videoView.setMediaController(mediaController);
            mediaController.setAnchorView(holder.videoView);
        }
    }

    private int changeToPX(Activity activity, int dp){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float logicalDensity = metrics.density;
        return (int) Math.ceil(dp * logicalDensity);
    }

    private int getWindowWidth(Activity activity) {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }




    ImagesVideoEvaluateAdapter.MyCallBack callBack;
    public interface MyCallBack {
        void notifyDataChanged();
    }

    public void setCallBack(ImagesVideoEvaluateAdapter.MyCallBack callBack) {
        this.callBack = callBack;
    }
    @Override
    public int getItemCount() {
        if(mList !=null){
            if(isExpanded){
                return mList.size();
            }
            else{
                return 4;
            }
        }
        else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvImages, imvDelete;
        ConstraintLayout layout_image, con1;
        VideoView videoView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvImages = itemView.findViewById(R.id.imvImages);
            imvDelete = itemView.findViewById(R.id.imvDelete);
            layout_image = itemView.findViewById(R.id.layout_image);
            videoView = itemView.findViewById(R.id.videoView);
            con1 = itemView.findViewById(R.id.con1);
        }
    }
}
