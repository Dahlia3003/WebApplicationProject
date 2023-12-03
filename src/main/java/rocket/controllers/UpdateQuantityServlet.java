package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.CartDB;

import java.io.IOException;

@WebServlet(name = "updateQuantityServlet", value = "/views/updateQuantity")
public class UpdateQuantityServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cartlineIDParam = request.getParameter("cartlineID");
        String quantityParam = request.getParameter("quantity");
        System.out.println("cartlineID" + cartlineIDParam);
        System.out.println("quantity" + quantityParam);

        // Check if parameters are not empty before parsing
        if (!cartlineIDParam.isEmpty() && !quantityParam.isEmpty()) {
            try {
                Integer cartlineID = Integer.parseInt(cartlineIDParam);
                Integer quantity = Integer.parseInt(quantityParam);

                // Call your updateQuantity method from CartDB
                CartDB.updateQuantity(cartlineID, quantity);

                // Redirect back to the previous URL
                String referer = request.getHeader("referer");
                response.sendRedirect(referer != null ? referer : request.getContextPath() + "/views/cart");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the exception as needed
            }
        } else {
            // Handle the case where one or more parameters are empty
            // You may want to redirect to an error page or display an error message
        }
    }
}

