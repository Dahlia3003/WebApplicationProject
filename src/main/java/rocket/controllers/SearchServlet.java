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

@WebServlet(name="SearchServlet", value = "/searchservlet")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        doPost(req, rep);
    }
    protected  void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        String brand = req.getParameter("brand").toString();
        List<Product> plbyBrand = new ArrayList<>();
        plbyBrand = ProductDB.searchProductsByBrand(brand);
        for(Product p : plbyBrand){
            System.out.println(p.getBrand());

        }
        System.out.println("asdd");
        req.setAttribute("productlist", plbyBrand);
        req.getServletContext().getRequestDispatcher("/views/ProductPage.jsp").forward(req, rep);
    }
}
