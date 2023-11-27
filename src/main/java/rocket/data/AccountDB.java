package rocket.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import rocket.models.Account;

public class AccountDB {
    public static void registerUser(Account account) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(account);
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

    public static void updatePassword(Account account) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(account);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();

        }
    }
    public static boolean login(Account account){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            Account accountinDB = em.find(Account.class, account.getUserID());
            if (accountinDB.getPassword() == account.getPassword())
                return true;
            trans.commit();
        }
        catch (Exception e)
        {
            trans.rollback();
        }
        finally {
            em.close();
            return false;
        }
    }
}
