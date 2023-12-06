package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        List<Product> plbyTag = new ArrayList<>();
        plbyTag = ProductDB.searchProductsByTag("Flashship mới ra mắt");
        req.setAttribute("productlist", plbyTag);

        List<Product> plbyTag2 = new ArrayList<>();
        plbyTag2 = ProductDB.searchProductsByTag("Pin trâu");
        req.setAttribute("productlist2", plbyTag2);


        getServletContext().getRequestDispatcher("/views/HomePage.jsp").forward(req, rep);
    }
}
