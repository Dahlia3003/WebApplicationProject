package rocket.controllers;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rocket.data.CartDB;
import rocket.data.ProductDB;
import rocket.models.CartLine;
import rocket.models.Product;

import java.io.IOException;

@WebServlet(name = "loadCartServlet", value = "/views/addcart")
public class LoadToCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer cardID = 6;
        String id = req.getParameter("id");
        Product select_pro = ProductDB.getProduct(id);
        CartLine cartLine = new CartLine(select_pro,1);
        CartDB.addCartItem(cardID, cartLine);
    }
}
