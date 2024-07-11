package lk.ijse.dep.model;

public class DeliveryDTO {
    private String deliveryId;
    private String deliveryDate;
    private String status;

    public DeliveryDTO() {
    }

    public DeliveryDTO(String deliveryId, String deliveryDate, String status) {
        this.deliveryId = deliveryId;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId='" + deliveryId + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
