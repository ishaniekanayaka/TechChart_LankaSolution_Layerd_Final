package lk.ijse.dep.entity;

import lombok.*;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor

public class Item {
    private String itemId;
    private String i_name;
    private int qtyAvailable;
    private double itemPrice;


}
