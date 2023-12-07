package rocket.controllers;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.ProductDB;
import rocket.models.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "infoServlet", value = "/productinfo")
public class LoadProductInfo extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String[] speci=ProductDB.getSpecifi(ProductDB.getProduct(id).getProductDescription());
        String[] proinfo = java.util.Arrays.copyOf(speci, speci.length + 1);
        proinfo [proinfo.length - 1] = ProductDB.getProduct(id).getProductImage();
        Gson gson = new Gson();
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(proinfo));
    }
}