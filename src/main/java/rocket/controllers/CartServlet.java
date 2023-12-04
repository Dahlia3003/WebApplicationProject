package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rocket.data.CartDB;
import rocket.models.Cart;

import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/views/CartServlet")
public class CartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        // Check if the cart is already in the session, if not, create a new one
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // Handle the actions based on the request parameters
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "remove":
                    handleRemoveCartItem(request, cart);
                    break;
                case "update":
                    handleUpdateQuantity(request, cart);
                    break;
                // Add more actions as needed
            }
        }

        // Forward to the JSP with the updated cart
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }

    private void handleRemoveCartItem(HttpServletRequest request, Cart cart) {
        String cartLineIDParam = request.getParameter("cartlineID");
        if (cartLineIDParam != null && !cartLineIDParam.isEmpty()) {
            try {
                Integer cartLineID = Integer.parseInt(cartLineIDParam);
                CartDB.removeCartItem(cartLineID, cart);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the exception as needed
            }
        }
    }

    private void handleUpdateQuantity(HttpServletRequest request, Cart cart) {
        String cartLineIDParam = request.getParameter("cartlineID");
        String quantityParam = request.getParameter("quantity");

        if (cartLineIDParam != null && !cartLineIDParam.isEmpty() && quantityParam != null && !quantityParam.isEmpty()) {
            try {
                Integer cartLineID = Integer.parseInt(cartLineIDParam);
                Integer quantity = Integer.parseInt(quantityParam);
                CartDB.updateQuantity(cartLineID, quantity, cart);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the exception as needed
            }
        }
    }
}
