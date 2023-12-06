package rocket.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import rocket.models.Product;
import rocket.Util.DBUtil;

import java.util.*;


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
    public static Product getProductfromName(String productName){
        EntityManager em = DBUtil.getEmf().createEntityManager();
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.productName=:name", Product.class);
        query.setParameter("name", productName);
        return query.getSingleResult();
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
    public static String getVarRom(Product product) {
        if (product != null && product.getVariation() != null && product.getVariation().contains("|")) {
            return product.getVariation().split("\\|")[0];
        }
        return null;
    }
    public static List<String> getProductVarRom(String line) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.line=:line", Product.class);
        query.setParameter("line", line);
        List<Product> vari = query.getResultList();
        List<String> rom = new ArrayList<>();
        Set<String> uniqueValues = new HashSet<>();
        for (Product product : vari) {
            uniqueValues.add(getVarRom(product));
        }
        rom.addAll(uniqueValues);
        Collections.sort(rom, Comparator.comparingInt(Integer::parseInt));
        em.close();
        return rom;
    }
    public static List<Product> getProductVarColor(String line, String rom) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.line=:line", Product.class);
        query.setParameter("line", line);
        List<Product> vari = query.getResultList();
        List<Product> color = new ArrayList<>();
        for (Product product : vari) {
            if (product.getVariation().contains(rom))
                color.add(product);
        }
        em.close();
        return color;
    }
    public static String[] getSpecifi(String description){
        String[] parts = description.split("\\|");
        String[] subParts = parts[0].split("/");
        return subParts;
    }
    public static List<String> getProductImage(String line, String rom) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.line=:line", Product.class);
        query.setParameter("line", line);
        List<Product> products = query.getResultList();
        List<String> imagelink = new ArrayList<>();
        for (Product product : products) {
            if (rom!=null) {
                if (product.getVariation().contains(rom))
                    imagelink.add(product.getProductImage());
            }
            else
            {
                imagelink.add(product.getProductImage());
            }
        }
        em.close();
        return imagelink;
    }
}
