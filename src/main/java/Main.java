import jakarta.persistence.EntityManagerFactory;
import model.EntityA;
import model.EntityB;
import model.MyEnum;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        myFirstPersist(emf);

//        try(var em = emf.createEntityManager()) {
//            EntityA entityA = em.find(EntityA.class, 1);
//
//            em.detach(entityA);
//            entityA.setPrice(100.0D);
//
//        }
    }

    private static void myFirstPersist(EntityManagerFactory emf) {
        EntityA entityA = new EntityA("A", 55.58D, MyEnum.ConstA);
        EntityB entityB = new EntityB("B");
        entityA.setEntityB(entityB);
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entityA);
            em.persist(entityB);
            em.getTransaction().commit();
        }
    }


}
