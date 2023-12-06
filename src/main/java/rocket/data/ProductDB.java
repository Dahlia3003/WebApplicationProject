package rocket.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import rocket.models.Product;
import rocket.Util.DBUtil;

import java.util.List;


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

    public static List<Product> searchProductsByTag(String tag) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        String queryString = "SELECT p FROM Product p WHERE p.productDescription LIKE :tag";
        TypedQuery<Product> query = em.createQuery(queryString, Product.class);
        query.setParameter("tag", "%" + tag + "%");
        return query.getResultList();
    }

    public static List<Product> searchProductsByBrand(String brand) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        String queryString = "SELECT p FROM Product p WHERE p.brand LIKE :brand";
        TypedQuery<Product> query = em.createQuery(queryString, Product.class);
        query.setParameter("brand", "%" + brand + "%");
        return query.getResultList();
    }
}
