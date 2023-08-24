package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<EntityA> entityAList = new HashSet<>();

    public EntityB(String name) {
        this.name = name;
    }

    public void addRefEntityA(EntityA entityA) {
        entityAList.add(entityA);
    }

    @Override
    public String toString() {
        return "EntityB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
