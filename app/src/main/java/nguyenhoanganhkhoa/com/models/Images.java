package nguyenhoanganhkhoa.com.models;

public class Images {
    String linkImages;
    String urlImages;

    public String getUrlImages() {
        return urlImages;
    }

    public void setUrlImages(String urlImages) {
        this.urlImages = urlImages;
    }

    public Images(String linkImages, String urlImages) {
        this.linkImages = linkImages;
        this.urlImages = urlImages;
    }

    public String getLinkImages() {
        return linkImages;
    }

    public void setLinkImages(String linkImages) {
        this.linkImages = linkImages;
    }

    public Images(String linkImages) {
        this.linkImages = linkImages;
    }
}