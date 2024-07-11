package lk.ijse.dep.model.tm;

import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@AllArgsConstructor
@Getter

public class SupplierTm {
    private String supplierId;
    private String s_name;
    private String product;
    private String contact_num;
}
