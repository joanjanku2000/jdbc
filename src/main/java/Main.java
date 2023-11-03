import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static configuration.SessionConfiguration.createConfiguration;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = createConfiguration()
                .buildSessionFactory()) {
            EntityManager em =  sessionFactory.createEntityManager();
            Session session = sessionFactory.openSession();
            System.out.println(session.createQuery("select u from user u", User.class).getResultList());
            session.close();
        }
    }
}
