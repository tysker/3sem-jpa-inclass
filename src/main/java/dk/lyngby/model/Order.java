package dk.lyngby.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
@Table(name = "orders")
@Entity
@NamedQuery(name = "Order.deleteAllRows", query = "DELETE from Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Temporal(TemporalType.DATE)
    @Column(name = "order_date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @PrePersist
    void prePersist() {
        date = LocalDate.now();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
