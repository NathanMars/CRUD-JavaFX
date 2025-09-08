package DAO;

import Model.User;
import Connection.HibernateCon;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;


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
}
