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
        HttpSession s = req.getSession();
        String customerID = CookieUtil.getCookieValue(cookies, "cusID");
        System.out.println("home"+customerID);
        if (CustomerDB.getCustomerById(customerID)!=null)
        {
            req.setAttribute("Username", CustomerDB.getCustomerById(customerID).getCustomerName());
            s.setAttribute("cusID", customerID);
        }
        else if (s.getAttribute("cusID")!=null)
        {
            req.setAttribute("Username", s.getAttribute("cusID").toString());
        }
        else {
            req.setAttribute("Username", "");
        }
        List<Product> plbyTag = new ArrayList<>();
        plbyTag = ProductDB.searchProductsByTag("Flagship");
        req.setAttribute("productlist", plbyTag);


        List<Product> plbyTag1 = new ArrayList<>();
        plbyTag1 = ProductDB.searchProductsByTag("Pin trâu");
        req.setAttribute("productlist1", plbyTag1);

        List<Product> plbyTag2 = new ArrayList<>();
        plbyTag2 = ProductDB.searchProductsByTag("Pin trâu");
        req.setAttribute("productlist2", plbyTag2);

        List<Product> plbyTag3 = new ArrayList<>();
        plbyTag3 = ProductDB.searchProductsByTag("Pin trâu");
        req.setAttribute("productlist3", plbyTag3);

        getServletContext().getRequestDispatcher("/views/HomePage.jsp").forward(req, rep);
    }
}
