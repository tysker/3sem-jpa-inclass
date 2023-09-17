package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "entityb")
public class EntityB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    private EntityA refEntityA;

    public EntityB(String name) {
        this.name = name;
    }

    public void setRefEntityA(EntityA entityA) {
        this.refEntityA = entityA;
        entityA.getEntityBList().add(this);
    }
    
}
