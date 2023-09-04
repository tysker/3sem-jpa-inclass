package dk.lyngby.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderLineDTO {

    private double price;

    private int quantity;

    public OrderLineDTO(double price, int quantity) {
        this.price = price * quantity;
    }
}
