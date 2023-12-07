package rocket.data;


import jakarta.persistence.*;
import rocket.models.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import rocket.models.Admin;
import rocket.models.Cart;

import rocket.models.Customer;
import rocket.Util.DBUtil;

import java.util.List;

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

    public static void updateCustomer(Customer customer) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            System.out.println(customer.getCustomerName());
            trans.begin();
            em.merge(customer);
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

    public static Customer getProfile(String cusID)
    {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        try {
            return em.find(Customer.class, cusID);
        } finally {
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

    public static List<Customer> getCustomerList() {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static void deleteAccount(String userId) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();

            // Tìm kiếm khách hàng dựa trên userId
            Customer customer = em.find(Customer.class, userId);

            if (customer != null) {
                // Xóa khách hàng
                em.remove(customer);
                trans.commit();
            } else {
                // Không tìm thấy khách hàng
                trans.rollback();
            }
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
