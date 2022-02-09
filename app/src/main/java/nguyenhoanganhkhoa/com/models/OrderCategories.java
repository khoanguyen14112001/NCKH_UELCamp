package nguyenhoanganhkhoa.com.models;

public class OrderCategories {
    private String orderCateType;
    private int orderCateThumb;

    public String getOrderCateType() {
        return orderCateType;
    }

    public void setOrderCateType(String orderCateType) {
        this.orderCateType = orderCateType;
    }

    public int getOrderCateThumb() {
        return orderCateThumb;
    }

    public void setOrderCateThumb(int orderCateThumb) {
        this.orderCateThumb = orderCateThumb;
    }

    public OrderCategories(String orderCateType, int orderCateThumb) {
        this.orderCateType = orderCateType;
        this.orderCateThumb = orderCateThumb;
    }
}
