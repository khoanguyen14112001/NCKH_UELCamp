package nguyenhoanganhkhoa.com.models;

import java.io.Serializable;
import java.util.List;

import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class PurchaseItem implements Serializable {

    String typePurchase;
    int quantityItems;
    double totalPrice;
    List<DrinkInCart> listItems;

    public List<DrinkInCart> getListItems() {
        return listItems;
    }

    public void setListItems(List<DrinkInCart> listItems) {
        this.listItems = listItems;
    }


    ReusedConstraint reusedConstraint = new ReusedConstraint();
    public String getTotalPrice_toString(){
        return reusedConstraint.formatCurrency(getTotalPrice());
    }

    public String getQuantityItems_toString(){
        if(getQuantityItems()==1| getQuantityItems()==0){
            return getQuantityItems() + " item";
        }
        else{
            return getQuantityItems() + " items";
        }
    }

    public String getTypePurchase() {
        return typePurchase;
    }

    public void setTypePurchase(String typePurchase) {
        this.typePurchase = typePurchase;
    }

    public int getQuantityItems() {
        return quantityItems;
    }

    public void setQuantityItems(int quantityItems) {
        this.quantityItems = quantityItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PurchaseItem(String typePurchase, int quantityItems, double totalPrice, List<DrinkInCart> listItems) {
        this.listItems = listItems;
        this.typePurchase = typePurchase;
        this.quantityItems = quantityItems;
        this.totalPrice = totalPrice;
    }
}
