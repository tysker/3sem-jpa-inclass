import jakarta.persistence.EntityManagerFactory;

public class Populate {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();


        Student student1 = new Student("John", "Hansen", "test1@mail.com", 25);
        Student student2 = new Student("Jane", "Hansen", "test2@mail.com", 26);
        Student student3 = new Student("Patrick", "Hansen", "test3@mail.com", 90);
        Student student4 = new Student("Michelle", "Jensen", "test4@mail.com", 10);
        Student student5 = new Student("Benjamin", "Jensen", "test5@mail.com", 55);
        Student student6 = new Student("Martin", "Jensen", "test6@mail.com", 46);
        Student student7 = new Student("Carl", "Jensen", "test7@mail.com", 18);
        Student student8 = new Student("Susan", "Schmidt", "test8@mail.com", 27);
        Student student9 = new Student("Anna", "Dunmur", "test9@mail.com", 60);
        Student student10 = new Student("Lisbeth", "Rex", "test10@mail.com", 71);

        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(student1);
            em.persist(student2);
            em.persist(student3);
            em.persist(student4);
            em.persist(student5);
            em.persist(student6);
            em.persist(student7);
            em.persist(student8);
            em.persist(student9);
            em.persist(student10);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        emf.close();
    }



}
