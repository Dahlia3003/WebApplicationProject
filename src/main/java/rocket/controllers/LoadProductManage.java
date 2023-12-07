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
import java.util.Arrays;
import java.util.stream.Stream;

@WebServlet(value = "/productmanage")
public class LoadProductManage extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Product product=ProductDB.getProduct(id);
        String[] speci= ProductDB.getSpecifi(product.getProductDescription());
        String[] tag= ProductDB.getTag(product.getProductDescription());
        String[] pro={product.getProductId(), product.getBrand(), product.getLine(), product.getProductName(), product.getPrice().toString(), product.getVariation(),
                product.getProductImage(), product.getAvailable().toString(), speci[0], speci[1], speci[2], speci[3], speci[4], speci[5]};
        String[] all=  Stream.concat(Arrays.stream(pro), Arrays.stream(tag)).toArray(String[]::new);
        Gson gson = new Gson();
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(all));
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String brand = request.getParameter("brand");
        String line = request.getParameter("line");
        String name = request.getParameter("name");
        String vari = request.getParameter("vari");
        String image = request.getParameter("image");
        String avai = request.getParameter("avai");
        Boolean available;
        if (avai.equals("true")) {
            available=true;
        }
        else {
            available=false;
        }
        String speci = request.getParameter("speci");
        if (id!=null)
        {
            Product product = new Product(brand, line, name, speci, image, 0, vari, available);
            product.setProductId(id);
            ProductDB.editProduct(product);
        }
    }
}
