package rocket.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.CustomerDB;
import rocket.models.Customer;

import java.io.IOException;
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
        String url = "/views/Checkout.jsp";
        String paid = request.getParameter("paid");
        if (paid!=null)
        {
            request.setAttribute("message","Xác nhận thanh toán thành công!");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
