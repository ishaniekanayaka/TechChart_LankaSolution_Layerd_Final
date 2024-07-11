package lk.ijse.dep.model.tm;


import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode

public class DeliveryTm {
    private String deliveryId;
    private String deliveryDate;
    private String status;

}
