import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "age", nullable = false)
    private int age;

    @PrePersist
    public void prePersist() {
        setEmail(this.email);
    }

    @PreUpdate
    public void preUpdate() {
        setEmail(this.email);
    }

    public Student(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.setEmail(email);
        this.age = age;
    }

    private boolean verifyEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public void setEmail(String email) {
        if(verifyEmail(email)) this.email = email;
        else throw new IllegalArgumentException("Email is not valid");
    }

    public void setId(int id) {
        this.id = id;
    }
}
