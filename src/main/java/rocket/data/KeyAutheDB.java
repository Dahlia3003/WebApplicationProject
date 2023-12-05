package rocket.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import rocket.Util.DBUtil;
import rocket.models.KeyAuthe;
import rocket.models.Order;

import java.security.Key;

public class KeyAutheDB {
    public static void addKey(KeyAuthe key) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(key);
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

    public static void removeKey(KeyAuthe key) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(key);
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

    public static boolean keyExists(String key) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        try {
            // Kiểm tra xem có tồn tại KeyAuthe với key cụ thể không
            Long count = em.createQuery("SELECT COUNT(k) FROM KeyAuthe k WHERE k.key = :key", Long.class)
                    .setParameter("key", key)
                    .getSingleResult();
            System.out.println(count);
            return count > 0;
        } finally {
            em.close();
        }
    }
}
