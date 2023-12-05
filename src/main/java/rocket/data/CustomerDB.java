package rocket.data;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import rocket.models.Customer;
import rocket.Util.DBUtil;

public class CustomerDB {

    public static void updateProfile(Customer cus)
    {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            System.out.println(cus.getDeliveryAddressDefault());
            System.out.println(cus.getCustomerName());
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
}
