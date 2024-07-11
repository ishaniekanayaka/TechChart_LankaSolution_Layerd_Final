package lk.ijse.dep.model;

import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@AllArgsConstructor

public class SupplierDTO {
    private String supplierId;
    private String s_name;
    private String product;
    private String contact_num;
    private String itemId;
}
