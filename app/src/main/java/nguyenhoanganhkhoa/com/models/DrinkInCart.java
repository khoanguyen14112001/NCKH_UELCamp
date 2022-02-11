package nguyenhoanganhkhoa.com.models;

import java.io.Serializable;

public class DrinkInCart extends Drink implements Serializable  {

    private int iceLevel;
    private int sugarLevel;
    private String size;
    private int quantityDrink;
    private boolean isSelected = false;

    public DrinkInCart(String drinkName, int thumbDrink, double drinkDiscount, double drinkPrePrice) {
        super(drinkName, thumbDrink, drinkDiscount, drinkPrePrice);
    }

    public int getIceLevel() {
        return iceLevel;
    }

    public void setIceLevel(int iceLevel) {
        this.iceLevel = iceLevel;
    }

    public int getSugarLevel() {
        return sugarLevel;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setSugarLevel(int sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantityDrink() {
        return quantityDrink;
    }

    public void setQuantityDrink(int quantityDrink) {
        this.quantityDrink = quantityDrink;
    }

    public String toIcePercentString() {
        return getIceLevel() + "% ice";
    }

    public String toSugarPercentString() {
        return getSugarLevel() + "% sugar";
    }

    public double getTotalPrice(){
        return getPriceAfterDiscount()*quantityDrink;
    }
}


