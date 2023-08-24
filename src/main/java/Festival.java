import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@NamedQuery(name = "Festival.deleteAllRows", query = "DELETE from Festival")
@Getter
@NoArgsConstructor
@Entity
@Table(name = "festival")
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "eventName", unique = true, nullable = false)
    private String eventName;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Temporal(TemporalType.TIME)
    private LocalTime start;

    @Temporal(TemporalType.TIME)
    private LocalTime ends;

    @Temporal(TemporalType.TIMESTAMP)
    private Date eventCreated;

    public Festival(String eventName, LocalDate date, LocalTime start, LocalTime ends) {
        this.eventName = eventName;
        this.date = date;
        this.start = start;
        this.ends = ends;
        this.eventCreated = new Date();
    }
}
