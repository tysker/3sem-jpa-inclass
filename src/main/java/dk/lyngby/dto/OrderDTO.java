package dk.lyngby.dto;

import lombok.ToString;

import java.time.LocalDate;

@ToString
public class OrderDTO {

    private int id;

    private LocalDate date;

    public OrderDTO(int id, LocalDate date) {
        this.id = id;
        this.date = date;
    }
}
