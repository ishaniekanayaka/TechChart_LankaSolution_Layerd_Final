package lk.ijse.dep.model.tm;

import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@AllArgsConstructor

public class ServiceTm {
    private String customerServiceId;
    private String OrderId;
    private String contact_num;
    private String description;

}
