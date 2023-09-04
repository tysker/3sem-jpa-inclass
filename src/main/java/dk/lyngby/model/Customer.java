package dk.lyngby.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Table(name = "customers")
@Entity
@NamedQuery(name = "Customer.deleteAllRows", query = "DELETE from Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false, updatable = false)
    private int customerId;

    @Column(name = "customer_name", nullable = false, length = 50)
    private String name;

    @Column(name = "customer_email", unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @OrderBy("date DESC")
    private Set<Order> orders = new HashSet<>();

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addOrder(Order order) {
        if(order != null) {
            order.setCustomer(this);
            orders.add(order);
        }

    }
}
