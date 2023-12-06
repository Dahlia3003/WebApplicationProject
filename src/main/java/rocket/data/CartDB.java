package rocket.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import rocket.models.Cart;
import rocket.models.CartLine;
import rocket.Util.DBUtil;
import rocket.models.Product;

import java.util.List;


public class CartDB {

    public static Cart getCartById(Integer cartID) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        try {
            return em.find(Cart.class, cartID);
        } finally {
            em.close();
        }
    }
    private static int findCartLineIndexByProduct(List<CartLine> cartList, Product targetProduct) {
        for (int i = 0; i < cartList.size(); i++) {
            CartLine cartLine = cartList.get(i);
            if (cartLine.getProduct().getProductId() == targetProduct.getProductId()) {
                return i;
            }
        }
        return -1;
    }
    public static void addCartItem(Integer cartID, CartLine cartline)
    {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Cart cart = em.find(Cart.class, cartID);
            if (cart != null) {
                List<CartLine> cartList = cart.getCartList();
                int index = findCartLineIndexByProduct(cartList,cartline.getProduct());
                System.out.println(index);
                if (index==-1)
                {
                    cartList.add(cartline);
                }
                else
                {
                    CartLine update =cartList.get(index);
                    update.setQuantity(update.getQuantity()+cartline.getQuantity());
                    update.setUniCost(update.calcPrice());
                    cartList.set(index,update);
                }
                cart.setCartList(cartList);
                em.merge(cart);
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


    public static void updateQuantity(Integer cartLineID, Integer quantity, Cart cart) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();

            // Find the CartLine in the Cart
            CartLine cartLineToUpdate = null;
            for (CartLine cartLine : cart.getCartList()) {
                if (cartLine.getId().equals(cartLineID)) {
                    cartLineToUpdate = cartLine;
                    break;
                }
            }

            // Update the quantity
            if (cartLineToUpdate != null) {
                cartLineToUpdate.setQuantity(quantity);
                cartLineToUpdate.setUniCost(cartLineToUpdate.calcPrice());
                em.merge(cartLineToUpdate);
            }

            // Commit the transaction
            transaction.commit();
            System.out.println("Transaction committed successfully");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    public static void removeCartItem(Integer cartLineID, Cart cart) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();

            // Find and remove the CartLine from the Cart
            CartLine cartLineToRemove = null;
            for (CartLine cartLine : cart.getCartList()) {
                if (cartLine.getId().equals(cartLineID)) {
                    cartLineToRemove = cartLine;
                    break;
                }
            }

            if (cartLineToRemove != null) {
                System.out.println("cid "+cartLineID);
                System.out.println("cid "+cart.getId());
                System.out.println("rid "+cartLineToRemove.getId());

                cart.getCartList().remove(cartLineToRemove);
                em.merge(cart);
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void updateVariation(String cartID, String cartlineID, String variation)
    {
        EntityManager em = DBUtil.getEmf().createEntityManager();
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
    public static List<CartLine> getCartLinesByCartID(String cartID) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        try {
            Cart cart = em.find(Cart.class, Integer.parseInt(cartID)); // Convert cartID to appropriate type
            if (cart != null) {
                List<CartLine> cartLines = cart.getCartList();

                return cartLines;
            } else {
                return null; // or an empty list depending on your use case
            }
        } finally {
            em.close();
        }
    }



}
