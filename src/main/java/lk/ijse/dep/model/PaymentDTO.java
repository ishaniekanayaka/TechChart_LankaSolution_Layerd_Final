package lk.ijse.dep.model;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PaymentDTO {
    private String paymentId;
    private String amount;
    private String paymentDate;
    private String method;

    private String orderId;
}
