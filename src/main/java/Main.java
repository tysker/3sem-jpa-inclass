import jakarta.persistence.EntityManagerFactory;
import model.EntityA;
import model.EntityB;
import model.MyEnum;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        myFirstPersist(emf);

        try(var em = emf.createEntityManager()) {
            var entityA = em.find(EntityA.class, 1);

        }


    }

    private static void myFirstPersist(EntityManagerFactory emf) {
        try (var em = emf.createEntityManager()) {

            EntityB entityB = new EntityB("testStringB");
            EntityA entityA = new EntityA("testStringA", MyEnum.ConstA);
            entityA.setRefEntityB(entityB);

            EntityA entityA2 = new EntityA("testStringA2", MyEnum.ConstB);
            entityA2.setRefEntityB(entityB);


            em.getTransaction().begin();
            em.persist(entityA);
            em.persist(entityA2);
            em.persist(entityB);
            em.getTransaction().commit();
        }
    }


}
