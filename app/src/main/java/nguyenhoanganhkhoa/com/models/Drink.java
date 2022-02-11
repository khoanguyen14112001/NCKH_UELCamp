package nguyenhoanganhkhoa.com.models;

import java.io.Serializable;

import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class Drink implements Serializable {
    private String drinkTitle;
    private String drinkName;
    private String drinkType;
    private int thumbDrink;
    private double drinkDiscount;
    private double drinkPrePrice;
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
