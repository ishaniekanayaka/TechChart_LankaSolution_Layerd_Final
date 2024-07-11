package lk.ijse.dep.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


public class OrderDetailsDTO {
    private String ItemId;
    private String OrderId;
    private int qty;
    private double unitPrice;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String itemId, String orderId, int qty, double unitPrice) {
        ItemId = itemId;
        OrderId = orderId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "ItemId='" + ItemId + '\'' +
                ", OrderId='" + OrderId + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
