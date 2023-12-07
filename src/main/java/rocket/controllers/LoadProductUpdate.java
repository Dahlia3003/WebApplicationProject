package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.ProductDB;
import rocket.models.Product;

import java.io.IOException;

@WebServlet(value = "/productupdate")
public class LoadProductUpdate extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String brand = request.getParameter("brand");
        String line = request.getParameter("line");
        String name = request.getParameter("name");
        String vari = request.getParameter("vari");
        String image = request.getParameter("image");
        String avai = request.getParameter("avai");
        String price = request.getParameter("price");
        Boolean available;
        if (avai.equals("true")) {
            available=true;
        }
        else {
            available=false;
        }
        String speci = request.getParameter("speci");
        if (id!=null)
        if (action.equals("update"))
        {
            Product product = new Product(brand, line, name, speci, image, Integer.parseInt(price), vari, available);
            product.setProductId(id);
            ProductDB.editProduct(product);
        }
        else if (action.equals("add")){
            Product product = new Product(brand, line, name, speci, image, Integer.parseInt(price), vari, available);
            ProductDB.addProduct(product);
        }
    }
}
