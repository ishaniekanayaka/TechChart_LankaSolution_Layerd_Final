package lk.ijse.dep.model.tm;

import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode

public class PaymentTm {
    private String PaymentId;
    private String Amount;
    private String Date;
    private String Method;
   // private String OrderId;

}
