package rocket.data;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import rocket.models.Cart;
import rocket.models.Customer;
import rocket.Util.DBUtil;

public class CustomerDB {

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
    public static Customer getProfile(String cusID)
    {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        try {
            return em.find(Customer.class, cusID);
        } finally {
            em.close();
        }
    }
}
