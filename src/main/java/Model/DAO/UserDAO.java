package Model.DAO;

import Model.Entity.User;
import Connection.HibernateCon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
     public User authenticate(String username, String password) {
        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(User.class);
            var root = query.from(User.class);


            query.select(root).where(
                    builder.and(
                            builder.equal(root.get("username"), username),
                            builder.equal(root.get("password"), password)
                    )
            );

            User user = session.createQuery(query).uniqueResult();

            //if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            if (user != null) {
                return user;
            } else {
                System.err.println("Usuário ou senha inválidos");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> selectUsers() {
        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public User insertUser(String name, String email, String username, String password, String type, boolean active, LocalDate birthdate, String cpf) {
        Transaction transaction = null;
        User user = new User();

        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            //user.setName(name);
            //user.setEmail(email);
            user.setUsername(username);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPassword(hashedPassword);
            //user.setType(TypeManager.valueOf(type.toUpperCase()));
            //user.setActive(active);
            //user.setBirthDate(birthdate);
            //user.setCpf(cpf);
            //user.setCreation(LocalDateTime.now());

            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            user = null;
        }

        return user;
    }
}
