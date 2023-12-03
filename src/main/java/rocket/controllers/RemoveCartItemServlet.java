package rocket.controllers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.CartDB;
import rocket.models.Cart;
import rocket.models.CartLine;

import java.io.IOException;

@WebServlet(name = "RemoveCartItemServlet", value = "/views/removeCartItem")
public class RemoveCartItemServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cartlineIDParam = request.getParameter("cartlineID");
        String ID = request.getParameter("cartID");
        System.out.println("cartlineID" + cartlineIDParam);
        System.out.println("ID" + ID);

        // Extract cartID and cartLineID from request parameters
        Integer cartID = Integer.parseInt(request.getParameter("cartID"));
        Integer cartLineID = Integer.parseInt(request.getParameter("cartLineID"));

        // Retrieve the Cart object
        Cart cart = CartDB.getCartById(cartID);

        if (cart != null) {
            // Find the CartLine with the specified ID in the Cart's cartList
            CartLine cartLineToRemove = null;
            for (CartLine cartLine : cart.getCartList()) {
                if (cartLine.getId().equals(cartLineID)) {
                    cartLineToRemove = cartLine;
                    break;
                }
            }

            // Call the removeCartItem method
            CartDB.removeCartItem(cartID, cartLineToRemove);
        }

        // Redirect back to the Cart.jsp after removing
        response.sendRedirect(request.getContextPath() + "/views/cart");
    }
}