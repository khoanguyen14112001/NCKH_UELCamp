package nguyenhoanganhkhoa.com.models;

import android.graphics.Bitmap;
import android.net.Uri;

public class ImagesVideoEvaluate {

    Uri uri;
    Bitmap bitmap;
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
