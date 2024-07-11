package lk.ijse.dep.model.tm;

import lombok.*;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor

public class ItemTm {
    private String itemId;
    private String i_name;
    private int qtyAvailable;
    private double itemPrice;
}
