package nguyenhoanganhkhoa.com.models;

import java.io.Serializable;
import java.util.List;

import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class Drink implements Serializable {
    ReusedConstraint reusedConstraint = new ReusedConstraint();
    private String drinkTitle;
    private String drinkName;
    private String drinkType;
    private int thumbDrink;
    private double drinkDiscount;
    private double drinkPrePrice;
    private String drinkDes;
    private double drinkEvaluateStar;
    private int drinkSoldQuantity;

    private List<Comments> commentsList;

    public Drink() {
    }

    public float getAverageRate(){
        if(getCommentsList()!=null){
            float totalScore = 0;
            float averageScore;
            for(int i =0;i<getCommentsList().size();i++){
                totalScore += getCommentsList().get(i).getCommentStars();
            }
            averageScore = totalScore/getCommentsList().size();
            return averageScore;
        }
        else{
            return 0;
        }

    }
    public String getAverageRate_toString(){
        return reusedConstraint.formatFloat(getAverageRate());
    }

    public String commentQuantity(){
        if(getCommentsList()!=null){
            if(getCommentsList().size()==1){
                return "("+getCommentsList().size() +" review)";
            }
            else{
                return "("+getCommentsList().size() +" reviews)";
            }
        }
        else{
            return "(0 review)";
        }
    }



    public String getDrinkDes() {
        return drinkDes;
    }

    public void setDrinkDes(String drinkDes) {
        this.drinkDes = drinkDes;
    }

    public double getDrinkEvaluateStar() {
        return drinkEvaluateStar;
    }

    public void setDrinkEvaluateStar(double drinkEvaluateStar) {
        this.drinkEvaluateStar = drinkEvaluateStar;
    }

    public int getDrinkSoldQuantity() {
        return drinkSoldQuantity;
    }

    public void setDrinkSoldQuantity(int drinkSoldQuantity) {
        this.drinkSoldQuantity = drinkSoldQuantity;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    private boolean isFavoriteDrink = false;


    public String getDrinkTitle() {
        return drinkTitle;
    }

    public void setDrinkTitle(String drinkTitle) {
        this.drinkTitle = drinkTitle;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public int getThumbDrink() {
        return thumbDrink;
    }

    public void setThumbDrink(int thumbDrink) {
        this.thumbDrink = thumbDrink;
    }

    public double getDrinkDiscount() {
        return drinkDiscount;
    }

    public void setDrinkDiscount(double drinkDiscount) {
        this.drinkDiscount = drinkDiscount;
    }

    public double getDrinkPrePrice() {
        return drinkPrePrice;
    }

    public void setDrinkPrePrice(double drinkPrePrice) {
        this.drinkPrePrice = drinkPrePrice;
    }

    public boolean isFavoriteDrink() {
        return isFavoriteDrink;
    }

    public void setFavoriteDrink(boolean favoriteDrink) {
        isFavoriteDrink = favoriteDrink;
    }

    public Drink(String drinkName, int thumbDrink, double drinkDiscount, double drinkPrePrice) {
        this.drinkName = drinkName;
        this.thumbDrink = thumbDrink;
        this.drinkDiscount = drinkDiscount;
        this.drinkPrePrice = drinkPrePrice;
    }

    public Drink(String drinkTitle, String drinkName, String drinkType, int thumbDrink, double drinkDiscount, double drinkPrePrice) {
        this.drinkTitle = drinkTitle;
        this.drinkName = drinkName;
        this.drinkType = drinkType;
        this.thumbDrink = thumbDrink;
        this.drinkDiscount = drinkDiscount;
        this.drinkPrePrice = drinkPrePrice;
    }

    public Drink(String drinkTitle, String drinkName, String drinkType, int thumbDrink, double drinkDiscount, double drinkPrePrice, boolean isFavoriteDrink) {
        this.drinkTitle = drinkTitle;
        this.drinkName = drinkName;
        this.drinkType = drinkType;
        this.thumbDrink = thumbDrink;
        this.drinkDiscount = drinkDiscount;
        this.drinkPrePrice = drinkPrePrice;
        this.isFavoriteDrink = isFavoriteDrink;
    }

    public double getPriceAfterDiscount(){
        return drinkPrePrice - drinkPrePrice * drinkDiscount;
    }

}
