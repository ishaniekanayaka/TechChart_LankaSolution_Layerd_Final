package lk.ijse.dep.entity;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Payment {
    private String paymentId;
    private String amount;
    private String paymentDate;
    private String method;

    private String orderId;
}
