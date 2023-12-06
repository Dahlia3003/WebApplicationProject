package rocket.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import rocket.Util.DBUtil;
import rocket.models.KeyAuthe;
import jakarta.persistence.TypedQuery;
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

    public static void removeAllKeys() {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            Query query = em.createQuery("DELETE FROM KeyAuthe");
            query.executeUpdate();
            System.out.println("All keys deleted successfully");
            trans.commit();
        } catch (Exception e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace(); // Handle the exception according to your needs
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public static KeyAuthe getKeyByValue(String key) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        try {
            // Sử dụng TypedQuery để lấy ra đối tượng KeyAuthe thích hợp
            TypedQuery<KeyAuthe> query = em.createQuery("SELECT k FROM KeyAuthe k WHERE k.key = :key", KeyAuthe.class)
                    .setParameter("key", key);

            // Sử dụng getResultList thay vì getSingleResult để tránh NoResultException
            // Trong trường hợp không có kết quả, trả về null
            return query.getResultList().stream().findFirst().orElse(null);
        } catch (Exception e) {
            return null; // Không có kết quả, trả về null
        } finally {
            em.close();
        }
    }
}
