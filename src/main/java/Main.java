import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.lyngby.config.HibernateConfig;
import org.lyngby.dao.DriverDAOImpl;
import org.lyngby.dao.WasteTruckDAOImpl;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();
//
//        DriverDAOImpl driverDAO = DriverDAOImpl.getInstance(emf);
//        WasteTruckDAOImpl wasteTruckDAO = WasteTruckDAOImpl.getInstance(emf);
//        //driverDAO.findAllDriversEmployedAtTheSameYear("2023").forEach(System.out::println);
//        wasteTruckDAO.joinToTables().forEach(System.out::println);


    }
}
