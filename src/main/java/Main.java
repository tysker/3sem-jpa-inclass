import dao.EventDAO;
import dao.FestivalDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Event;
import model.Festival;

import java.time.LocalDate;
import java.time.LocalTime;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        EventDAO eventDAO = EventDAO.getInstance(emf);
        Event event = new Event("Roskilde Festival");
        eventDAO.persistEvent(event);

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
