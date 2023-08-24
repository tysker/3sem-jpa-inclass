import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.time.LocalTime;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        EventDAO eventDAO = EventDAO.getInstance(emf);

        Event event1 = new Event("Event 2");
        eventDAO.persistEvent(event1);

        event1.setEventName("Event 3");

        eventDAO.updateEvent(event1);


        FestivalDAO festivalDAO = FestivalDAO.getInstance(emf);
        Festival copenhell = new Festival(
                "Copenhell",
                LocalDate.of(2021, 6, 16),
                LocalTime.of(11, 0, 0),
                LocalTime.of(23, 0, 0)
        );

        festivalDAO.persistFestival(copenhell);


    }
}
