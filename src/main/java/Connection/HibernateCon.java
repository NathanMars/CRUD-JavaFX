package Connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateCon {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return (new Configuration()).configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Falha na criação de uma conexão com o banco de dados.");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
