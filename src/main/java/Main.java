import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import rocket.Util.DBUtil;
import rocket.models.*;

public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart();
        CartLine cartLine = new CartLine();
        Customer customer = new Customer();
        Order order = new Order();
        Product product = new Product();

        customer.setUserID("1");

        EntityManagerFactory emf = DBUtil.getEmf();
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(cart);
        entityManager.persist(cartLine);
        entityManager.persist(customer);
        entityManager.persist(order);
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        entityManager.close();
        emf.close();
    }
}