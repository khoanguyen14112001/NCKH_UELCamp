package nguyenhoanganhkhoa.com.models;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class ImagesVideoEvaluate implements Serializable {

    Uri uri;
    Bitmap bitmap;
    int imageInt;

    public int getImageInt() {
        return imageInt;
    }

    public void setImageInt(int imageInt) {
        this.imageInt = imageInt;
    }

    public ImagesVideoEvaluate(int imageInt) {
        this.imageInt = imageInt;
    }

    boolean isImage = true;

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ImagesVideoEvaluate(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ImagesVideoEvaluate(Uri uri) {
        this.uri = uri;
    }
}
