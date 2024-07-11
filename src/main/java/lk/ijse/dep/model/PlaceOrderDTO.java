package lk.ijse.dep.model;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PlaceOrderDTO {
    private OrderDTO order;
    private List<OrderDetailsDTO> odList;
}
