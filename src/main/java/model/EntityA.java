package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.EntityB;

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

    @Enumerated(EnumType.STRING)
    private MyEnum myEnum;

    @ManyToOne
    private EntityB entityB;

    public EntityA(String name, MyEnum myEnum) {
        this.name = name;
        this.myEnum = myEnum;
    }

    public void setRefEntityB(EntityB entityB) {
        this.entityB = entityB;
    }
}
