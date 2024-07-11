package lk.ijse.dep.entity;


public class Order {
    private String orderId;
    private String Date;
    private String customerId;
    private String Total;


    public Order() {
    }

    public Order(String orderId, String date, String customerId, String total) {
        this.orderId = orderId;
        Date = date;
        this.customerId = customerId;
        Total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", Date='" + Date + '\'' +
                ", customerId='" + customerId + '\'' +
                ", Total='" + Total + '\'' +
                '}';
    }
}
