package rocket.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rocket.data.CustomerDB;
import rocket.data.OrderDB;
import rocket.data.ProductDB;
import rocket.models.Customer;
import rocket.models.Order;
import rocket.models.Product;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet ( value ="/manage")
public class ManageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        loadCustomer(request, response);
        loadOrder(request, response);
        List<Product> products= ProductDB.getAllProducts();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/Manager.jsp");
        dispatcher.forward(request, response);
    }

    private void loadCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customerList = CustomerDB.getCustomerList();
        request.setAttribute("customerList", customerList);
    }

    private void loadOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = OrderDB.getOrderList();
        request.setAttribute("orderList", orderList);
    }
}
