package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.CustomerDB;
import rocket.models.Customer;

import java.io.IOException;
@WebServlet(name="PasswordServlet", value="/passwordservlet")
public class PasswordChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        String pass = req.getParameter("password");
        String email = req.getParameter("email");
        System.out.println(email);
        Customer cus = CustomerDB.getCustomerByEmail(email);
        cus.setPassword(pass);
        CustomerDB.updateProfile(cus);
        getServletContext().getRequestDispatcher("/views/LoginPage.jsp").forward(req,rep);
    }
}
