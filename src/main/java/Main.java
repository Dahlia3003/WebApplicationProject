import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import rocket.Util.DBUtil;
import rocket.models.*;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        CartLine cartLine = new CartLine();

        Order order = new Order();
        Product product1 = new Product("Apple", "Iphone 14", "Iphone 14 128GB Màu Vàng", "chip/rom/màn/cam/pin/connect|tag/tag/tag", "https://cdn.hoanghamobile.com/i/preview/Uploads/2023/03/08/14-yellow.png", 18590000, "128|yellow", true);
        Product product2 = new Product("Apple", "Iphone 14", "Iphone 14 128GB Màu Trắng", "chip/rom/màn/cam/pin/connect|tag/tag/tag", "https://cdn.hoanghamobile.com/i/preview/Uploads/2022/09/08/anh-chup-man-hinh-2022-09-08-luc-01-59-18-removebg-preview.png", 18590000, "128|white", true);
        Product product3 = new Product("Apple", "Iphone 14", "Iphone 14 128GB Màu Đỏ", "chip/rom/màn/cam/pin/connect|tag/tag/tag", "https://cdn.hoanghamobile.com/i/preview/Uploads/2022/09/08/anh-chup-man-hinh-2022-09-08-luc-01-59-53-removebg-preview.png", 18590000, "128|red", true);
        Product product4 = new Product("Apple", "Iphone 14", "Iphone 14 128GB Màu Tím", "chip/rom/màn/cam/pin/connect|tag/tag/tag", "https://cdn.hoanghamobile.com/i/preview/Uploads/2022/09/08/2222.png", 18590000, "128|purple", true);


        CartLine cartLine1 = new CartLine(product1, 2);
        CartLine cartLine2 = new CartLine(product2, 1);
        CartLine cartLine3 = new CartLine(product3, 3);
        CartLine cartLine4 = new CartLine(product4, 1);

        List<CartLine> cartLines= new ArrayList<>();
        cartLines.add(cartLine1);
        cartLines.add(cartLine2);
        cartLines.add(cartLine4);
        cartLines.add(cartLine3);
        Cart cart = new Cart(cartLines);

        Customer customer = new Customer("test", "123", new Date(), "Thi dz", "0123456789", "abc@xyz.com", "q9", cart);

        EntityManagerFactory emf = DBUtil.getEmf();
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);
        entityManager.persist(product4);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(cart);
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManager.close();
        emf.close();
    }
}