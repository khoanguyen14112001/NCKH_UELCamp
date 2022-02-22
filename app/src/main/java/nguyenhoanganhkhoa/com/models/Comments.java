package nguyenhoanganhkhoa.com.models;

import java.io.Serializable;
import java.util.List;

public class Comments implements Serializable {
    int commentThumbUser;
    String nameCommentUser;
    String dateComment;
    int commentStars;
    String commentsContent;
    boolean isGoodProduct = false;
    boolean isGoodSeller = false;
    boolean isGoodDelivery = false;
    boolean isGoodPackaging = false ;
    boolean isGoodValue = false;

    List<ImagesVideoEvaluate> listImage;

    public int getCommentThumbUser() {
        return commentThumbUser;
    }

    public void setCommentThumbUser(int commentThumbUser) {
        this.commentThumbUser = commentThumbUser;
    }

    public String getNameCommentUser() {
        return nameCommentUser;
    }

    public String getDateComment() {
        return dateComment;
    }

    public void setDateComment(String dateComment) {
        this.dateComment = dateComment;
    }

    public void setNameCommentUser(String nameCommentUser) {
        this.nameCommentUser = nameCommentUser;
    }

    public Comments(int commentThumbUser, String nameCommentUser, String dateComment, int commentStars, String commentsContent, List<ImagesVideoEvaluate> listImage) {
        this.commentThumbUser = commentThumbUser;
        this.nameCommentUser = nameCommentUser;
        this.dateComment = dateComment;
        this.commentStars = commentStars;
        this.commentsContent = commentsContent;
        this.listImage = listImage;
    }

    public int getCommentStars() {
        return commentStars;
    }

    public void setCommentStars(int commentStars) {
        this.commentStars = commentStars;
    }

    public String getCommentsContent() {
        return commentsContent;
    }

    public void setCommentsContent(String commentsContent) {
        this.commentsContent = commentsContent;
    }

    public boolean isGoodProduct() {
        return isGoodProduct;
    }

    public void setGoodProduct(boolean goodProduct) {
        isGoodProduct = goodProduct;
    }

    public boolean isGoodSeller() {
        return isGoodSeller;
    }

    public void setGoodSeller(boolean goodSeller) {
        isGoodSeller = goodSeller;
    }

    public boolean isGoodDelivery() {
        return isGoodDelivery;
    }

    public void setGoodDelivery(boolean goodDelivery) {
        isGoodDelivery = goodDelivery;
    }

    public boolean isGoodPackaging() {
        return isGoodPackaging;
    }

    public void setGoodPackaging(boolean goodPackaging) {
        isGoodPackaging = goodPackaging;
    }

    public boolean isGoodValue() {
        return isGoodValue;
    }

    public void setGoodValue(boolean goodValue) {
        isGoodValue = goodValue;
    }

    public List<ImagesVideoEvaluate> getListImage() {
        return listImage;
    }

    public void setListImage(List<ImagesVideoEvaluate> listImage) {
        this.listImage = listImage;
    }
}
