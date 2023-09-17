package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "refEntityA", fetch = FetchType.LAZY,orphanRemoval = true)
    @ToString.Exclude
    private Set<EntityB> entityBList = new HashSet<>();

    public EntityA(String name, MyEnum myEnum) {
        this.name = name;
        this.myEnum = myEnum;
    }

    public void removeRefEntityB(EntityB entityB) {
        entityBList.remove(entityB);
    }
}
