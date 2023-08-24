import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class StudentDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public void persistStudent(Student std) {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(std);
            em.getTransaction().commit();
        }
    }

    public Student updateStudent(Student std) {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Student updatedStudent = em.merge(std);
            em.getTransaction().commit();
            return updatedStudent;
        }
    }

    public double getAvgAge() {
        try(var em = emf.createEntityManager()) {
            return em.createQuery("SELECT AVG(s.age) FROM Student s", Double.class).getSingleResult();
        }
    }

    public double getAvgOfAllStudentsWithLastNameHansen() {
        try(var em = emf.createEntityManager()) {
            return em.createQuery("SELECT AVG(s.age) FROM Student s WHERE s.lastName = 'Hansen'", Double.class).getSingleResult();
        }
    }

    public long numberOfStudentsWithLastNameJensen() {
        try(var em = emf.createEntityManager()) {
            return em.createQuery("SELECT COUNT(s) FROM Student s WHERE s.lastName = 'Jensen'", Long.class).getSingleResult();
        }
    }

    public int getOldestStudent() {
        try(var em = emf.createEntityManager()) {
            return em.createQuery("SELECT MAX(s.age) FROM Student s", Integer.class).getSingleResult();
        }
    }

    public int getYoungestStudent() {
        try(var em = emf.createEntityManager()) {
            return em.createQuery("SELECT MIN(s.age) FROM Student s", Integer.class).getSingleResult();
        }
    }

    public long getTheSumOfAllStudentsAge() {
        try(var em = emf.createEntityManager()) {
            return em.createQuery("SELECT SUM(s.age) FROM Student s", Long.class).getSingleResult();
        }
    }
}
