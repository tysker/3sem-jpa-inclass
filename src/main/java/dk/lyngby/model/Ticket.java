package dk.lyngby.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@Table(name = "ticket")
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Event event;

    @Builder
    public Ticket(double price, int quantity, TicketType ticketType, Guest guest, Event event) {
        this.price = price;
        this.quantity = quantity;
        this.ticketType = ticketType;
        this.guest = guest;
        this.event = event;
    }


    public enum TicketType {
        VIP, STANDARD
    }
}
