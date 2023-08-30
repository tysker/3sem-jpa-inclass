package dk.lyngby;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.model.Event;
import dk.lyngby.model.Guest;
import dk.lyngby.model.Ticket;
import jakarta.persistence.EntityManagerFactory;

import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public static void main(String[] args) {
        persistNew();

    }

    private static void persistNew() {
        Guest steve = Guest.builder()
                .email("steve@mail.com")
                .name("Steve")
                .surname("Jobs")
                .telephoneNumber("12345678")
                .age(56)
                .build();

        Guest frank = Guest.builder()
                .email("frank@mail.com")
                .name("Frank")
                .surname("Hansen")
                .telephoneNumber("123456789")
                .age(25)
                .build();

        Event roskildeFestival = new Event(
                "Roskilde Festival",
                LocalDate.of(2021, 6, 26),
                LocalDate.of(2021, 7, 3));

        Event copenhellFestival = new Event(
                "Copenhell Festival",
                LocalDate.of(2021, 6, 26),
                LocalDate.of(2021, 7, 3));

        Ticket roskildeTicket = Ticket.builder()
                .price(750D)
                .quantity(2)
                .ticketType(Ticket.TicketType.VIP)
                .guest(steve)
                .event(roskildeFestival)
                .build();

        Ticket copenhellTicket = Ticket.builder()
                .price(550D)
                .quantity(1)
                .ticketType(Ticket.TicketType.VIP)
                .guest(steve)
                .event(copenhellFestival)
                .build();

        steve.addTicket(roskildeTicket);
        steve.addTicket(copenhellTicket);

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(steve);
            em.persist(frank);
            em.persist(roskildeFestival);
            em.persist(copenhellFestival);
            em.persist(roskildeTicket);
            em.persist(copenhellTicket);
            em.getTransaction().commit();
        }
    }
}
