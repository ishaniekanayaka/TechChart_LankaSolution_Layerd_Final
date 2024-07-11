package lk.ijse.dep.entity;

import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@AllArgsConstructor

public class Supplier {
    private String supplierId;
    private String s_name;
    private String product;
    private String contact_num;
    private String itemId;
}
