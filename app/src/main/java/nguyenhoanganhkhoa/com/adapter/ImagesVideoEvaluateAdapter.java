package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.media.MediaPlayer;
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

        if(item.isImage()){
            holder.imvImages.setImageBitmap(item.getBitmap());
        }
        else{
            holder.videoView.setVideoURI(item.getUri());
            MediaController mediaController = new MediaController(context);
            holder.videoView.setMediaController(mediaController);
            mediaController.setAnchorView(holder.videoView);
        }
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
            return mList.size();
        }
        else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvImages, imvDelete;
        ConstraintLayout layout_image;
        VideoView videoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvImages = itemView.findViewById(R.id.imvImages);
            imvDelete = itemView.findViewById(R.id.imvDelete);
            layout_image = itemView.findViewById(R.id.layout_image);
            videoView = itemView.findViewById(R.id.videoView);
        }
    }
}
