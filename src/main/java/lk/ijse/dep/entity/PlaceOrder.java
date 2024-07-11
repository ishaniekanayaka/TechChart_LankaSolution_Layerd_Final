package lk.ijse.dep.entity;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PlaceOrder {
    private Order order;
    private List<OrderDetails> odList;
}
