package rocket.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import rocket.models.Product;
import rocket.Util.DBUtil;


public class ProductDB {
    public static Product getProduct(String productId){
        EntityManager em = DBUtil.getEmf().createEntityManager();
        try{
            Product p = em.find(Product.class, productId);
            return p ;
        }
        finally{
            em.close();
        }
    }

    public static void addProduct(Product newProduct) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(newProduct);
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

    public static void editProduct(Product updatedProduct) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(updatedProduct);
            trans.commit();
        }
        catch (Exception e)
        {
            trans.rollback();
        }
        finally{
                em.close();

        }
    }

    public static void hideProduct(String productId) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Product product = em.find(Product.class, productId);
            if (product != null) {
                product.setAvailable(false);
            }
            trans.commit();
        }
        catch (Exception e)
        {
            trans.rollback();
        }
        finally {
            em.close();
        }
    }
}
