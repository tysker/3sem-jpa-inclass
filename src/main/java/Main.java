import jakarta.persistence.EntityManagerFactory;
import model.EntityA;
import model.EntityB;
import model.MyEnum;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        myFirstPersist(emf);

        try (var em = emf.createEntityManager()) {
            EntityA entityA = em.find(EntityA.class, 1);
            System.out.println(entityA.getEntityBList());
        }
    }

    private static void myFirstPersist(EntityManagerFactory emf) {
        try (var em = emf.createEntityManager()) {

            EntityB entityB = new EntityB("testStringB");
            EntityB entityB2 = new EntityB("testStringB2");
            EntityB entityB3 = new EntityB("testStringB3");

            EntityA entityA = new EntityA("testStringA", MyEnum.ConstA);

            entityA.addRefEntityB(entityB);
            entityA.addRefEntityB(entityB2);
            entityA.addRefEntityB(entityB3);

            entityB.setRefEntityA(entityA);
            entityB2.setRefEntityA(entityA);
            entityB3.setRefEntityA(entityA);

            em.getTransaction().begin();
            em.persist(entityA);
            em.persist(entityB);
            em.persist(entityB2);
            em.persist(entityB3);
            em.getTransaction().commit();
        }
    }


}
