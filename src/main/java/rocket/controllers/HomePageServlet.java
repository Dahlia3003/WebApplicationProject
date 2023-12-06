package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import rocket.Util.CookieUtil;
import rocket.data.CustomerDB;
import rocket.data.ProductDB;
import rocket.models.Product;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="HomeServlet", value = "/homeservlet")

public class HomePageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException{
        doPost(req, rep);
    }
    protected  void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String customerID = CookieUtil.getCookieValue(cookies, "cusID");
        if (CustomerDB.getCustomerById(customerID)!=null)
        {
            req.setAttribute("Username", CustomerDB.getCustomerById(customerID).getCustomerName());
        }
        else
            req.setAttribute("Username", "");
        List<Product> plbyTag = new ArrayList<>();
        plbyTag = ProductDB.searchProductsByTag("Flashship mới ra mắt");
        req.setAttribute("productlist", plbyTag);

        List<Product> plbyTag2 = new ArrayList<>();
        plbyTag2 = ProductDB.searchProductsByTag("Pin trâu");
        req.setAttribute("productlist2", plbyTag2);

        System.out.println("Homeeeee");
        getServletContext().getRequestDispatcher("/views/HomePage.jsp").forward(req, rep);
    }
}
