package rocket.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import rocket.models.Cart;
import rocket.models.CartLine;

import java.util.List;


public class CartDB {

    public static void addCartItem(String cartID, CartLine cartline)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Cart cart = em.find(Cart.class, cartID);
            if (cart != null) {
                List<CartLine> cartList = cart.getCartList();
                cartList.add(cartline);
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

    public static void removeCartItem(String cartID, CartLine cartline)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Cart cart = em.find(Cart.class, cartID);
            if (cart != null) {
                List<CartLine> cartList = cart.getCartList();
                cartList.remove(cartline);
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

    public static void updateQuantity(String cartID, String cartlineID, Long quantity)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Cart cart = em.find(Cart.class, cartID);
            if (cart != null)
            {
                List<CartLine> cartList = cart.getCartList();
                    for(CartLine cartline : cartList)
                    {
                        if (cartline.getId().equals(cartlineID))
                        {
                            cartline.setQuantity(quantity);
                        }
                    }
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

    public static void updateVariation(String cartID, String cartlineID, String variation)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Cart cart = em.find(Cart.class, cartID);
            if (cart != null)
            {
                List<CartLine> cartList = cart.getCartList();
                for(CartLine cartline : cartList)
                {
                    if (cartline.getId().equals(cartlineID))
                    {
                        cartline.getProduct().setVariation(variation);
                    }
                }
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
