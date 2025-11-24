package Model.DAO;

import Model.Entity.Product;
import Connection.HibernateCon;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> selectProducts() {
        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Product selectProductById(int id) {
        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            return session.get(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteProduct(Product product) {
         // Soft delete by setting active to false, or hard delete?
         // Based on User entity having 'active' field, I'll assume soft delete preference or just update.
         // But for a true delete method:
        Transaction transaction = null;
        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
