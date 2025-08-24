package DAO;

import Model.Admin;
import Connection.HibernateCon;
import org.hibernate.Session;


public class AdminDAO {
     public Admin authenticate(String username, String password) {
        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(Admin.class);
            var root = query.from(Admin.class);


            query.select(root).where(
                    builder.and(
                            builder.equal(root.get("username"), username),
                            builder.equal(root.get("password"), password)
                    )
            );

            Admin admin = session.createQuery(query).uniqueResult();


            if (admin != null) {
                return admin;
            } else {
                System.err.println("Usuário ou senha inválidos");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
