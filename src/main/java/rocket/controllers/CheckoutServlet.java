package rocket.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rocket.data.CartDB;
import rocket.data.CustomerDB;
import rocket.data.OrderDB;
import rocket.models.Cart;
import rocket.models.CartLine;
import rocket.models.Customer;
import rocket.models.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "checkoutServlet", value = "/views/checkout")
public class CheckoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cusID="test";
        Customer customer = CustomerDB.getProfile(cusID);
        request.setAttribute("message","Bạn muốn thanh toán như thế nào?");
        request.setAttribute("customer", customer);
        int cost=CartDB.getCartById(6).calcTotal();
        request.setAttribute("cost", cost);
        System.out.println(CartDB.getCartById(6).calcTotal());
        String url = "/views/Checkout.jsp";
        String paid = request.getParameter("paid");
        String method = request.getParameter("method");
        if (paid!=null)
        {
            HttpSession session = request.getSession(true);
            Integer cartID = (Integer) session.getAttribute("cart");
            cartID = 6;
            Cart cart = CartDB.getCartById(cartID);
            List<CartLine> cartLines = new ArrayList<>(cart.getCartList());
            CartDB.removeAllCartItem(cart);
            System.out.println(cartLines.size());
            Order order = new Order(cartLines, new Date(), customer, "Đang giao", method, 0);
            OrderDB.addOrder(order);
            request.setAttribute("message","Xác nhận thanh toán thành công!");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
