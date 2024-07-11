package lk.ijse.dep.model;

import lombok.*;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor

public class ItemDTO {
    private String itemId;
    private String i_name;
    private int qtyAvailable;
    private double itemPrice;


}
