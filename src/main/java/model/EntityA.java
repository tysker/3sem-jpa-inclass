package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "entitya")
public class EntityA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    private double price;

    @Enumerated(EnumType.STRING)
    private MyEnum myEnum;

    @OneToOne
    @MapsId
    private EntityB entityB;

    public EntityA(String name, double price, MyEnum myEnum) {
        this.name = name;
        this.price = price;
        this.myEnum = myEnum;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEntityB(EntityB entityB) {
        this.entityB = entityB;
    }
}
