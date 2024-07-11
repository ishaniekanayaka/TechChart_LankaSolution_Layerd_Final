package lk.ijse.dep.model;

import lombok.*;
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@AllArgsConstructor
public class ServiceDTO {
    private String customerServiceId;
    private String orderId;
    private String contact_num;
    private String description;
}
