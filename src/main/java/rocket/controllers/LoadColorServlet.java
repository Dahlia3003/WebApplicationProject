package rocket.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.ProductDB;
import rocket.models.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "colorServlet", value = "/views/color")
public class LoadColorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String line = req.getParameter("line");
        String variRom = req.getParameter("variRom");
        List<Product> color = ProductDB.getProductVarColor(line, variRom);
        System.out.println(line+variRom+color.size());
        Gson gson = new Gson();
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(color));
    }
}
