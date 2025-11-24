package Connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateCon {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();

            // Run Flyway migration
            org.flywaydb.core.Flyway flyway = org.flywaydb.core.Flyway.configure()
                    .dataSource(
                            configuration.getProperty("hibernate.connection.url"),
                            configuration.getProperty("hibernate.connection.username"),
                            configuration.getProperty("hibernate.connection.password"))
                    .load();
            flyway.migrate();

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Falha na criação de uma conexão com o banco de dados.");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
