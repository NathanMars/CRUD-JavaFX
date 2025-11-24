package Model.DAO;

import Model.Entity.Purchase;
import Model.Entity.User;
import Connection.HibernateCon;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {

    public List<Purchase> selectPurchases() {
        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            return session.createQuery("FROM Purchase", Purchase.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Purchase> selectPurchasesByUser(User user) {
        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            return session.createQuery("FROM Purchase WHERE user = :user", Purchase.class)
                    .setParameter("user", user)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean insertPurchase(Purchase purchase) {
        Transaction transaction = null;
        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(purchase);
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
