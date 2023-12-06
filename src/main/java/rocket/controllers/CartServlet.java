package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rocket.data.CartDB;
import rocket.data.CustomerDB;
import rocket.models.Cart;

import java.io.IOException;

@WebServlet(name = "cartServlet", value = "/views/cart")
public class CartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        // Check if the cart is already in the session, if not, create a new one
        Cart cart = (Cart) session.getAttribute("cart");
        String cusID = (String) session.getAttribute("cusID");
        // If the cart is not in the session, fetch it from the database
        if (cart == null) {
            System.out.println(session.getAttribute("cusID"));// Replace with the actual cart ID you want to fetch
            cart = CustomerDB.getCustomerById(cusID).getCart();

            // If the cart is still null, create a new one
            if (cart == null) {
                cart = new Cart();
            }

            session.setAttribute("cart", cart);
        }
        cart = CustomerDB.getCustomerById(cusID).getCart();

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
            }
        }

        // Forward to the JSP with the updated cart
        request.setAttribute("cart", cart);
        if(session.getAttribute("cusID")==null){
            request.getRequestDispatcher("/views/LoginPage.jsp").forward(request, response);
        } else if (cart.getCartList().size()==0) {
            request.getRequestDispatcher("/views/EmptyCart.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("/views/Cart.jsp").forward(request, response);
        }
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
                System.out.println("cartlineid "+cartLineID);System.out.println("quan "+quantity);

                if (quantity < 1) {
                    // If quantity is less than 1, redirect to the remove action
                    handleRemoveCartItem(request, cart);
                    return; // Exit the method to avoid further processing
                }
                else{
                    CartDB.updateQuantity(cartLineID, quantity, cart);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the exception as needed
            }
        }
    }
}
