package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.Util.CookieUtil;
import rocket.data.ProductDB;
import rocket.models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="ProductServlet", value = "/productservlet")
public class ProductServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        doPost(req, rep);
    }
    protected  void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        Integer pagenum = 0;
        Integer productperpage = 2;
        String type = req.getParameter("type");
        String value = req.getParameter("value");
        req.setAttribute("type", type);
        req.setAttribute("value", value);
        req.setAttribute("productperpage", productperpage);

        if (type.equals("brand")){
            List<Product> plbyBrand = new ArrayList<>();
            plbyBrand = ProductDB.searchProductsByBrand(value);
            req.setAttribute("productlist", plbyBrand);
            req.setAttribute("totalpages", plbyBrand.size() / productperpage );
        }
        else if (type.equals("search"))
        {
            List<Product> plbyALL = new ArrayList<>();
            plbyALL= ProductDB.searchProductsByEverything(value);
            req.setAttribute("productlist", plbyALL);
            req.setAttribute("totalpages", plbyALL.size() / productperpage );
        }
        else if (type.equals("tag"))
        {
            List<Product> plbyBrand = new ArrayList<>();
            plbyBrand = ProductDB.searchProductsByTag(value);
            req.setAttribute("productlist", plbyBrand);
            req.setAttribute("totalpages", plbyBrand.size() / productperpage );
        }


        if (req.getParameter("page") != null)
        {
            pagenum = Integer.parseInt(req.getParameter("page").toString());
            req.setAttribute("pagenum", pagenum);
        }
        else{
            req.setAttribute("pagenum", "0");
        }

        req.getServletContext().getRequestDispatcher("/views/ProductPage.jsp").forward(req, rep);
    }
}
