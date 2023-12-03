package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", value="/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeButton = request.getParameter("typeButton");
        System.out.println(typeButton);
        if ("Login".equals(typeButton)) {

            response.sendRedirect(request.getContextPath()+"/views/LoginPage.jsp");
        }
        else if ("Register".equals(typeButton)) {

            response.sendRedirect(request.getContextPath()+"/views/RegisterPage.jsp");
        }
        else if ("CartEmpty".equals(typeButton)) {

            response.sendRedirect(request.getContextPath()+"/views/EmptyCart.jsp");
        }
        else if ("Cart".equals(typeButton)) {

            response.sendRedirect(request.getContextPath()+"/views/Cart.jsp");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
