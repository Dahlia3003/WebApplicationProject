package rocket.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import rocket.models.Cart;
import rocket.models.CartLine;
import rocket.Util.DBUtil;

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

    public static void addCartItem(String cartID, CartLine cartline)
    {
        EntityManager em = DBUtil.getEmf().createEntityManager();
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

    public static void removeCartItem(Integer cartID, CartLine cartLineToRemove) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();

            // Retrieve the Cart by its ID
            Cart cart = em.find(Cart.class, cartID);

            if (cart != null && cartLineToRemove != null) {
                // Remove the specified CartLine from the Cart's cartList
                cart.getCartList().remove(cartLineToRemove);
            }

            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace(); // Handle or log the exception appropriately
        } finally {
            em.close();
        }
    }

    public static void updateQuantity(Integer cartlineID, Integer quantity) {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();

            // Find the CartLine directly by cartlineID
            CartLine cartline = em.find(CartLine.class, cartlineID);

            if (cartline != null) {
                System.out.println("Updating quantity for CartLine ID: " + cartlineID + " to " + quantity);
                cartline.setQuantity(quantity);
                // Recalculate unitCost based on the updated quantity
                cartline.setUniCost(cartline.calcPrice());
            }

            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace(); // Print the exception stack trace for debugging
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
