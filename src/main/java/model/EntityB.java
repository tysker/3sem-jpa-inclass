package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "entityb")
public class EntityB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(mappedBy = "entityB")
    private EntityA entityA;

    public EntityB(String name) {
        this.name = name;
    }

    public void setEntityA(EntityA entityA) {
        this.entityA = entityA;
    }

    @Override
    public String toString() {
        return "EntityB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
