package dk.lyngby.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TotalPriceDTO {

    private double price;

    private int quantity;

    public TotalPriceDTO(double price, int quantity) {
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotalPriceOfAnOrder() {
        return price * quantity;
    }
}
