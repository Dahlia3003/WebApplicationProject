package rocket.data;

import jakarta.persistence.*;
import rocket.models.Account;
import rocket.models.Customer;
import rocket.Util.DBUtil;

public class CustomerDB {
    public static void registerUser(Customer cus) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(cus);
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
    public static void updateProfile(Customer cus)
    {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.merge(cus);
            trans.commit();
        }
        catch (Exception e) {
            trans.rollback();
        }
        finally {
            em.close();
        }
    }

    public static Customer getCustomerById(String customerId) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            // Sử dụng EntityManager để thực hiện truy vấn
            return em.find(Customer.class, customerId);
        }
        catch (Exception e) {
                trans.rollback();
            }
        finally {
                em.close();
            }
        return null;
    }

    public static Customer getCustomerByEmail(String email) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            String jpql = "SELECT c FROM Customer c WHERE c.emailAddress = :email";
            TypedQuery<Customer> query = em.createQuery(jpql, Customer.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }

        return null;
    }

    public static boolean isUsernameDuplicate(String username) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        String jpql = "SELECT COUNT(c) FROM Customer c WHERE c.userID = :username";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }

    public static boolean isEmailDuplicate(String email) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        String jpql = "SELECT COUNT(c) FROM Customer c WHERE c.emailAddress = :email";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }

}
