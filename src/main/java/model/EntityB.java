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

    @ManyToOne
    @JoinColumn(name = "a_id")
    private EntityA refEntityA;

    public EntityB(String name) {
        this.name = name;
    }

    public void setRefEntityA(EntityA refEntityA) {
        this.refEntityA = refEntityA;
    }
}
