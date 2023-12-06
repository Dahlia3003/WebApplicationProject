package rocket.controllers;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.postgresql.util.LruCache;
import rocket.data.CartDB;
import rocket.data.ProductDB;
import rocket.models.Cart;
import rocket.models.CartLine;
import rocket.models.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "detailServlet", value = "/views/detail")
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productID = "1";  // Có thể lấy từ request.getParameter("productID");
        Integer cardID = 6;
        Product product = ProductDB.getProduct(productID);
        //List<String> imagelink = ProductDB.getProductImage(product.getLine(),null);
        List<String> rom = ProductDB.getProductVarRom(product.getLine());
        String action = request.getParameter("action");
        String line = request.getParameter("line");
        String variRom = request.getParameter("variRom");
        String id = request.getParameter("id");
        List<String> imagelink = ProductDB.getProductImage(product.getLine(),variRom);
        List<Product> color = ProductDB.getProductVarColor(line, variRom);
        String[] speci=null;
        Product select_pro = new Product();
        if (id!=null)
        {
            select_pro = ProductDB.getProduct(id);
            request.setAttribute("id",select_pro.getProductId());
            speci = ProductDB.getSpecifi(select_pro.getProductDescription());
            imagelink.clear();
            imagelink.add(select_pro.getProductImage());
        }
        if (product != null) {
            request.setAttribute("product", product);
            request.setAttribute("imagelink", imagelink);
            request.setAttribute("rom", rom);
            request.setAttribute("color", color);
            request.setAttribute("variRom", variRom);
            request.setAttribute("speci", speci);
        }
        String url = "/views/DetailPage.jsp";
        if (action!=null)
        {
            if (action.equals("add_cart"))
            {
                CartLine cartLine = new CartLine(select_pro,1);
                CartDB.addCartItem(cardID, cartLine);
            }
            else
            if (action.equals("buy"))
            {
                CartLine cartLine = new CartLine(select_pro,1);
                CartDB.addCartItem(cardID, cartLine);
                HttpSession session = request.getSession(true);
                session.setAttribute("cart",CartDB.getCartById(cardID));
                url="cart";
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}