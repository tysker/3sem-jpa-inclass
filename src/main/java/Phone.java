import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "phone")
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number", unique = true, nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PhoneType type;

    public Phone(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }

    public enum PhoneType {
        HOME,
        WORK,
        MOBILE
    }
}
