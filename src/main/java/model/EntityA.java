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

    @Enumerated(EnumType.STRING)
    private MyEnum myEnum;

    @OneToOne
    //@JoinColumn(name = "entityb_test_id") // optional
    private EntityB entityB;

    public EntityA(String name, MyEnum myEnum) {
        this.name = name;
        this.myEnum = myEnum;
    }

    public void setEntityB(EntityB entityB) {
        this.entityB = entityB;
    }
}
