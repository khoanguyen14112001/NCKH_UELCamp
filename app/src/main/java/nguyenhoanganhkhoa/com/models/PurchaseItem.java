package nguyenhoanganhkhoa.com.models;

import java.io.Serializable;
import java.util.List;

import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class PurchaseItem implements Serializable {

    String typePurchase;
    String purchaseID;
    int quantityItems;
    double totalPrice;
    String paymentMethod;
    double deliveryFee;
    double discount;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getDiscountDeliveryFee() {
        return discountDeliveryFee;
    }

    public void setDiscountDeliveryFee(double discountDeliveryFee) {
        this.discountDeliveryFee = discountDeliveryFee;
    }

    double discountDeliveryFee;
    List<DrinkInCart> listItems;

    public List<DrinkInCart> getListItems() {
        return listItems;
    }

    public String getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(String purchaseID) {
        this.purchaseID = purchaseID;
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

    public PurchaseItem(String typePurchase, String purchaseID, int quantityItems, double totalPrice, List<DrinkInCart> listItems) {
        this.listItems = listItems;
        this.purchaseID = purchaseID;
        this.typePurchase = typePurchase;
        this.quantityItems = quantityItems;
        this.totalPrice = totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public PurchaseItem(String typePurchase, String purchaseID, int quantityItems, double totalPrice, List<DrinkInCart> listItems, String paymentMethod, double discount , double deliveryFee, double discountDeliveryFee) {
        this.typePurchase = typePurchase;
        this.purchaseID = purchaseID;
        this.quantityItems = quantityItems;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.discount = discount;
        this.deliveryFee = deliveryFee;
        this.discountDeliveryFee = discountDeliveryFee;
        this.listItems = listItems;
    }

    public PurchaseItem(String typePurchase, String purchaseID, int quantityItems, double totalPrice, List<DrinkInCart> listItems, String paymentMethod) {
        this.typePurchase = typePurchase;
        this.purchaseID = purchaseID;
        this.quantityItems = quantityItems;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.listItems = listItems;
    }

    public PurchaseItem(String typePurchase, String purchaseID, int quantityItems, double totalPrice, List<DrinkInCart> listItems, double discount) {
        this.typePurchase = typePurchase;
        this.purchaseID = purchaseID;
        this.quantityItems = quantityItems;
        this.totalPrice = totalPrice;
        this.listItems = listItems;
        this.discount = discount;
    }
}
