package lk.ijse.dep.model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTm {
    private String ItemId;
    private String itemName;
    private double UnitPrice;
    private int QTY;
    private double TotalAmount;
    private JFXButton Action;

}
