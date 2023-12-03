package rocket.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.CartDB;
import rocket.models.Cart;
import rocket.models.CartLine;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "cartServlet", value = "/views/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cartID = "6";  // Có thể lấy từ request.getParameter("cartID");

        List<CartLine> cartLines = CartDB.getCartLinesByCartID(cartID);


        if (cartLines != null) {
            Cart cart = new Cart();
            cart.setCartList(cartLines);

            request.setAttribute("cart", cart);
            System.out.println("cartServlet");
        } else {
            System.out.println("Null");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/Cart.jsp");
        dispatcher.forward(request, response);
    }


}
