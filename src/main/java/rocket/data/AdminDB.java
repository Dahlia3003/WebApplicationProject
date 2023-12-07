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

public class AdminDB {
    public static Admin getAdminById(String customerId) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            // Sử dụng EntityManager để thực hiện truy vấn
            return em.find(Admin.class, customerId);
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
