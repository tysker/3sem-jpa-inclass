import jakarta.persistence.EntityManagerFactory;
import model.EntityA;
import model.EntityB;
import model.MyEnum;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        //myFirstPersist(emf);
        Set<EntityB> entityAList;
        try (var em = emf.createEntityManager()) {
            EntityA entityA = em.find(EntityA.class, 1);
            entityAList = entityA.getEntityBList();
        }
       entityAList.forEach(b -> System.out.println(b));

        try (var em = emf.createEntityManager()) {
            EntityB entityB = em.find(EntityB.class, 2);
            System.out.println(entityB.getEntityAList());
        }
    }

    private static void myFirstPersist(EntityManagerFactory emf) {
        try (var em = emf.createEntityManager()) {

            EntityA entityA = new EntityA("testStringA", MyEnum.ConstA);
            EntityA entityA1 = new EntityA("testStringA1", MyEnum.ConstB);
            EntityA entityA2 = new EntityA("testStringA2", MyEnum.ConstC);

            EntityB entityB = new EntityB("testStringB");
            EntityB entityB2 = new EntityB("testStringB2");
            EntityB entityB3 = new EntityB("testStringB3");


            entityA.addRefEntityB(entityB);
            entityA.addRefEntityB(entityB2);
            entityA.addRefEntityB(entityB3);

            entityA1.addRefEntityB(entityB);
            entityA1.addRefEntityB(entityB2);

            entityA2.addRefEntityB(entityB);
            entityA2.addRefEntityB(entityB3);

            entityB2.addRefEntityA(entityA);
            entityB2.addRefEntityA(entityA1);
            entityB2.addRefEntityA(entityA2);


            em.getTransaction().begin();
            em.persist(entityA);
            em.persist(entityA1);
            em.persist(entityA2);
            em.persist(entityB);
            em.persist(entityB2);
            em.persist(entityB3);
            em.getTransaction().commit();
        }
    }


}
