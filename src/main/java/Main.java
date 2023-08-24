import jakarta.persistence.EntityManagerFactory;
import model.EntityA;
import model.EntityB;
import model.MyEnum;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        myFirstPersist(emf);
        getBothTable(emf);

         // pagination
        pagination(emf);

        try(var em = emf.createEntityManager()) {
            EntityB entityB = em.find(EntityB.class, 1);
            System.out.println(entityB.getEntityA());
            System.out.println(entityB);
        }

        try(var em = emf.createEntityManager()) {
            List<EntityA> list = em.createQuery("select a from EntityA a", EntityA.class).getResultList();
            list.forEach(System.out::println);
        }


    }

    private static void pagination(EntityManagerFactory emf) {
        try (var em = emf.createEntityManager()) {
            em.createQuery("select a from EntityA a", EntityA.class)
                    .setFirstResult(0)
                    .setMaxResults(3)
                    .getResultList()
                    .forEach(System.out::println);
        }
    }

    private static void getBothTable(EntityManagerFactory emf) {
        try (var em = emf.createEntityManager()) {
            em.createQuery("select a from EntityA a", EntityA.class).getResultList().forEach(System.out::println);
            em.createQuery("select b from EntityB b", EntityB.class).getResultList().forEach(System.out::println);
        }
    }

    private static void myFirstPersist(EntityManagerFactory emf) {
        EntityA entityA1 = new EntityA("A1", MyEnum.ConstA);
        EntityA entityA2 = new EntityA("A2", MyEnum.ConstB);
        EntityA entityA3 = new EntityA("A3", MyEnum.ConstC);
        EntityA entityA4 = new EntityA("A4", MyEnum.ConstA);
        EntityA entityA5 = new EntityA("A5", MyEnum.ConstB);
        EntityA entityA6 = new EntityA("A6", MyEnum.ConstC);

        EntityB entityB1 = new EntityB("AB1");
        EntityB entityB2 = new EntityB("AB2");
        EntityB entityB3 = new EntityB("AB3");
        EntityB entityB4 = new EntityB("AB4");
        EntityB entityB5 = new EntityB("AB5");
        EntityB entityB6 = new EntityB("AB6");
        entityA1.setEntityB(entityB1);
        entityA2.setEntityB(entityB2);
        entityA3.setEntityB(entityB3);
        entityA4.setEntityB(entityB4);
        entityA5.setEntityB(entityB5);
        entityA6.setEntityB(entityB6);


        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entityA1);
            em.persist(entityA2);
            em.persist(entityA3);
            em.persist(entityA4);
            em.persist(entityA5);
            em.persist(entityA6);
            em.persist(entityB1);
            em.persist(entityB2);
            em.persist(entityB3);
            em.persist(entityB4);
            em.persist(entityB5);
            em.persist(entityB6);

            em.getTransaction().commit();
        }
    }


}
