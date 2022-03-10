package nguyenhoanganhkhoa.com.adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.models.Drink;
import nguyenhoanganhkhoa.com.models.DrinkOption;
import nguyenhoanganhkhoa.com.models.ImagesVideoEvaluate;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class EvaluateAdapter extends RecyclerView.Adapter<EvaluateAdapter.ViewHolder> {

    private Context context;
    private List<Drink> mDrink;


    private List<ImagesVideoEvaluate> imagesList = new ArrayList<>();

    public void addImageEvaluate(ImagesVideoEvaluate images){
        imagesList.add(images);
        notifyDataSetChanged();
    }

    public List<ImagesVideoEvaluate> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<ImagesVideoEvaluate> imagesList) {
        this.imagesList = imagesList;
    }

    public EvaluateAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<Drink> list){
        this.mDrink = list;
        notifyDataSetChanged();
    }


    ReusedConstraint reusedConstraint = new ReusedConstraint();
    @NonNull
    @Override
    public EvaluateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_evaluate,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvaluateAdapter.ViewHolder holder, int position) {
        Drink drink = mDrink.get(position);
        if(drink ==null)
        {
            return;
        }

        ImageView[] arrImv = {holder.imvStar0,holder.imvStar1, holder.imvStar2, holder.imvStar3, holder.imvStar4};
        TextView[] arrTxt = {holder.textView4,holder.textView5, holder.textView6, holder.textView7, holder.textView8};

        holder.imvThumbDrink.setImageResource(drink.getThumbDrink());
        holder.txtNameDrink.setText(drink.getDrinkName());
        holder.txtPriceDrink.setText(reusedConstraint.formatCurrency(drink.getPriceAfterDiscount()));

        for(int i = 0; i< arrImv.length;i++){
            int finalI = i;
            arrImv[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setStarOnClick(finalI, arrImv);
                }
            });
        }

        for(int i = 0; i< arrTxt.length;i++){
            int finalI = i;
            boolean[] arrIsSelected = {false, false, false, false, false};
            arrTxt[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setStatusEvaluateText(finalI,arrTxt,arrIsSelected);
                }
            });
        }

        holder.layout_open_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.openCamera(true);
            }
        });

        holder.layout_open_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.openCamera(false);
            }
        });

        ImagesVideoEvaluateAdapter adapter = new ImagesVideoEvaluateAdapter(context);

        if(imagesList!=null){

            int quantityImages = 0;
            int quantityVids = 0;
            adapter.setData(imagesList);
            holder.rcvImageVidEvaluate.setLayoutManager(new GridLayoutManager(context,4,RecyclerView.VERTICAL,false));
            holder.rcvImageVidEvaluate.setAdapter(adapter);

            adapter.setCallBack(new ImagesVideoEvaluateAdapter.MyCallBack() {
                @Override
                public void notifyDataChanged() {
                    notifyDataSetChanged();
                }
            });

            for (int i =0; i<getImagesList().size();i++){
                if(getImagesList().get(i).isImage()){
                    quantityImages++;
                }
            }

            for (int i =0; i<getImagesList().size();i++){
                if(!getImagesList().get(i).isImage()){
                    quantityVids++;
                }
            }

            holder.txtQuantityImages.setText(context.getString(R.string.add_pictures)  + " (" + quantityImages +"/5)");
            holder.txtQuantityVid.setText(context.getString(R.string.add_pictures)  + " (" + quantityVids +"/1)");

        }
        else{
            holder.txtQuantityImages.setText(context.getString(R.string.add_pictures)  + " (" + 0 +"/5)");
            holder.txtQuantityVid.setText(context.getString(R.string.add_video)  + " (" + 0 +"/1)");
        }



    }


    EvaluateAdapter.MyCallBack callBack;
    public interface MyCallBack {
        void openCamera(boolean isImage);
    }

    public void setCallBack(EvaluateAdapter.MyCallBack callBack) {
        this.callBack = callBack;
    }






    private void setStatusEvaluateText(int position, TextView[] arrTxt, boolean[] arrIsSelected){
        if(arrIsSelected[position]){
            arrTxt[position].setBackground(context.getDrawable(R.drawable.edt_custom_focus)) ;

            arrIsSelected[position] = false;
        }
        else{
            arrTxt[position].setBackground(context.getDrawable(R.drawable.button_login)) ;
            arrIsSelected[position] = true;

        }

    }

    private void setStarOnClick(int position, ImageView[] arrImv){
        for (int i =0 ; i<=position;i++){
            arrImv[i].setImageResource(R.drawable.ic_star_yellow);
        }
        for(int k = arrImv.length-1; k>position;k--){
            arrImv[k].setImageResource(R.drawable.ic_star);
        }
    }

    @Override
    public int getItemCount() {
        if(mDrink !=null){
            return mDrink.size();
        }
        else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameDrink, txtPriceDrink, textView5, textView4, textView7, textView6, textView8, txtQuantityImages, txtQuantityVid;
        LinearLayout layout_open_camera, layout_open_video;
        ImageView imvThumbDrink;
        ImageView imvStar0, imvStar1, imvStar2, imvStar3, imvStar4;
        RecyclerView rcvImageVidEvaluate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameDrink = itemView.findViewById(R.id.txtNameDrink);
            txtPriceDrink = itemView.findViewById(R.id.txtPriceDrink);
            textView5 = itemView.findViewById(R.id.textView5);
            textView4 = itemView.findViewById(R.id.textView4);
            textView7 = itemView.findViewById(R.id.textView7);
            textView6 = itemView.findViewById(R.id.textView6);
            textView8 = itemView.findViewById(R.id.textView8);

            imvStar0 = itemView.findViewById(R.id.imvStar0);
            imvStar1 = itemView.findViewById(R.id.imvStar1);
            imvStar2 = itemView.findViewById(R.id.imvStar2);
            imvStar3 = itemView.findViewById(R.id.imvStar3);
            imvStar4 = itemView.findViewById(R.id.imvStar4);


            layout_open_camera = itemView.findViewById(R.id.layout_open_camera);
            layout_open_video = itemView.findViewById(R.id.layout_open_video);
            imvThumbDrink = itemView.findViewById(R.id.imvThumbDrink);

            rcvImageVidEvaluate = itemView.findViewById(R.id.rcvImageVidEvaluate);
            txtQuantityImages = itemView.findViewById(R.id.txtQuantityImages);
            txtQuantityVid = itemView.findViewById(R.id.txtQuantityVid);
        }
    }
}
