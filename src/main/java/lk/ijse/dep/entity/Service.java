package lk.ijse.dep.entity;

import lombok.*;
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@AllArgsConstructor
public class Service {
    private String customerServiceId;
    private String orderId;
    private String contact_num;
    private String description;
}
