package rocket.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import rocket.models.Customer;
import rocket.models.Order;
import rocket.Util.DBUtil;

import java.util.Collections;
import java.util.List;

public class OrderDB {
    public static void addOrder(Order order) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(order);
            trans.commit();
        }
        catch (Exception e)
        {
            trans.rollback();
        }
        finally
        {
            em.close();
        }
    }
    public static void updateOrder(Order order) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(order);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static List<Order> getOrderList() {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        try {
            // Use the correct entity name in the query
            TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o", Order.class);
            return query.getResultList();
        } catch (Exception e) {
            // Handle any exceptions (e.g., log or rethrow)
            e.printStackTrace();
            return Collections.emptyList(); // Return an empty list or handle appropriately
        } finally {
            em.close();
        }
    }

    public static Order getOrderById(int orderId) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        try {
            // Use EntityManager to perform the query
            return em.find(Order.class, orderId);
        } catch (Exception e) {
            // Handle the exception (e.g., log it)
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }
}
