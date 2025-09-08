package Model.DAO;

import Model.Entity.Client;
import Connection.HibernateCon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class ClientDAO {

    public List<Client> selectClients() {
        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            return session.createQuery("FROM Client", Client.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Client insertClient(String name, String cpf, String email, String phone, String address
            , LocalDate birthDate, boolean active) {

        Transaction transaction = null;
        Client Client = new Client();

        try (Session session = HibernateCon.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Client.setName(name);
            Client.setEmail(email);
            Client.setPhone(phone);
            Client.setCpf(cpf);
            Client.setBirthdate(birthDate);
            Client.setActive(active);
            Client.setAddress(address);

            session.persist(Client);
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
            Client = null;
        }

        return Client;
    }
}
