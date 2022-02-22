package nguyenhoanganhkhoa.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nguyenhoanganhkhoa.com.models.Address;
import nguyenhoanganhkhoa.com.models.Comments;
import nguyenhoanganhkhoa.com.models.ImagesVideoEvaluate;
import nguyenhoanganhkhoa.com.myapplication.R;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private Context context;
    private List<Comments> mComments;



    public CommentsAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<Comments> list){
        this.mComments = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_comment,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, int position) {
        Comments comments = mComments.get(position);
        if(comments ==null)
        {
            return;
        }

        holder.imvAvatar.setImageResource(comments.getCommentThumbUser());
        holder.txtNameUser.setText(comments.getNameCommentUser());
        holder.txtComment.setText(comments.getCommentsContent());

        holder.ratingBar.setRating(comments.getCommentStars());

        TextView[] arrText = {holder.txtGoodProduct, holder.txtGoodSeller, holder.txtGoodDelivery, holder.txtGoodPackage, holder.txtGoodValue};
        boolean[] arrValue = {comments.isGoodProduct(), comments.isGoodSeller(), comments.isGoodDelivery(),comments.isGoodPackaging(), comments.isGoodValue()};

        for(int i =0; i< arrText.length;i++){
            if(arrValue[i]){
                arrText[i].setVisibility(View.VISIBLE);
            }
            else{
                arrText[i].setVisibility(View.GONE);
            }
        }
        holder.txtDateComment.setText(comments.getDateComment());

        ImagesVideoEvaluateAdapter imageAdapter = new ImagesVideoEvaluateAdapter(context);




        imageAdapter.setData(comments.getListImage());
        imageAdapter.setScreen(ImagesVideoEvaluateAdapter.PRODUCT_SCREEN);

        if(comments.getListImage()!=null){
            if(comments.getListImage().size()>4){
                imageAdapter.setExpanded(false);
                holder.cvImageRemain.setVisibility(View.VISIBLE);
                holder.txtNumImagesRemain.setText("+" + (comments.getListImage().size() - 4));
            }
            else{
                imageAdapter.setExpanded(true);
                holder.cvImageRemain.setVisibility(View.GONE);
            }

            holder.cvImageRemain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageAdapter.setExpanded(true);
                    holder.cvImageRemain.setVisibility(View.GONE);
                    Log.d("TAG", "cvClicked: " + comments.getListImage().size());

                    holder.rcvImages.setLayoutManager(new GridLayoutManager(context,4,RecyclerView.VERTICAL,false));
                    holder.rcvImages.setAdapter(imageAdapter);
                }
            });
        }

        else{
            imageAdapter.setExpanded(true);
            holder.cvImageRemain.setVisibility(View.GONE);
        }



        holder.rcvImages.setLayoutManager(new GridLayoutManager(context,4,RecyclerView.VERTICAL,false));
        holder.rcvImages.setAdapter(imageAdapter);
    }





    @Override
    public int getItemCount() {
        if(mComments !=null){
            return mComments.size();
        }
        else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imvAvatar;
        TextView txtNameUser, txtComment, txtDateComment, txtNumImagesRemain;
        TextView txtGoodProduct, txtGoodSeller, txtGoodDelivery, txtGoodPackage, txtGoodValue;
        RatingBar ratingBar;
        RecyclerView rcvImages;

        CardView cvImageRemain;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvAvatar = itemView.findViewById(R.id.imvAvatar);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            txtNameUser = itemView.findViewById(R.id.txtNameUser);
            txtComment = itemView.findViewById(R.id.txtComment);
            txtDateComment = itemView.findViewById(R.id.txtDateComment);

            txtGoodProduct = itemView.findViewById(R.id.txtGoodProduct);
            txtGoodDelivery = itemView.findViewById(R.id.txtGoodDelivery);
            txtGoodPackage = itemView.findViewById(R.id.txtGoodPackage);
            txtGoodSeller = itemView.findViewById(R.id.txtGoodSeller);
            txtGoodValue = itemView.findViewById(R.id.txtGoodValue);

            rcvImages = itemView.findViewById(R.id.rcvImages);
            cvImageRemain = itemView.findViewById(R.id.cvImageRemain);
            txtNumImagesRemain = itemView.findViewById(R.id.txtNumImagesRemain);

        }
    }
}
