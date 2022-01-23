package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import nguyenhoanganhkhoa.com.models.Images;
import nguyenhoanganhkhoa.com.models.Member;
import nguyenhoanganhkhoa.com.myapplication.R;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder>  {

    private List<Images> mListImages;
    int layout;
    Context context;

    public ImagesAdapter(List<Images> mListImages, int layout, Context context) {
        this.mListImages = mListImages;
        this.layout = layout;
        this.context = context;
    }

    @NonNull
    @Override
    public ImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layout,parent,false);
        return new ImagesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesAdapter.ViewHolder holder, int position) {
        Images images = mListImages.get(position);
        String urlUEL = "https://www.uel.edu.vn/";
        if(images ==null){
            return;
        }

        if(layout==R.layout.item_news){
            try {
                Glide.with(context).load(images.getLinkImages()).into(holder.imvNews);
            }
            catch (Exception e){
                Log.d("Error", "Fail to glide images to NewsScreen: " + e);
            }
            holder.layout_news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openURL(urlUEL);
                }
            });
        }
        else if(layout==R.layout.item_ads){
            Glide.with(context).load(images.getLinkImages()).into(holder.imvAds);
            holder.layout_ads.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openURL(images.getUrlImages());
                }
            });
        }

    }


    private void openURL(String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent= new Intent(Intent.ACTION_VIEW,uri);
            context.startActivity(intent);
        }
        catch (Exception e){
            Log.d("Error", "Fail to openURL: " + e);
            Toast.makeText(context, "Fail to open URL, please try again later!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mListImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvNews,imvAds;
        LinearLayout layout_ads,layout_news;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvNews = itemView.findViewById(R.id.imvNews);
            imvAds = itemView.findViewById(R.id.imvAds);
            layout_ads = itemView.findViewById(R.id.layout_ads);
            layout_news = itemView.findViewById(R.id.layout_news);
        }
    }
}
