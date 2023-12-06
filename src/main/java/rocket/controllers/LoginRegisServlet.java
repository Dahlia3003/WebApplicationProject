package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.CartDB;
import rocket.data.CustomerDB;
import rocket.models.Cart;
import rocket.models.Customer;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "LoginRegisterServlet", value = "/loginregister")
public class LoginRegisServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        String action = req.getParameter("action");
        req.setAttribute("action", action);
        if ("register".equals(action)) {
            register(req, rep);
        }
        if ("forgetpassword".equals(action)){
            forgetpass(req,rep);
        }
    }
    private void forgetpass(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (CustomerDB.isEmailDuplicate(email)) {
            req.setAttribute("email",email);
            getServletContext().getRequestDispatcher("/mailservlet").forward(req, rep);
        }
        else
        {
            req.setAttribute("isForgetSuccess", "1");
            getServletContext().getRequestDispatcher("/views/ForgotPasswordSendMail.jsp").forward(req, rep);
        }
    }
    private void register(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String deliveryAddress = req.getParameter("deliveryAddress");
        String phone = req.getParameter("phone");
        String username = req.getParameter("username");
        String password = req.getParameter("password");



        if (CustomerDB.isUsernameDuplicate(username))
        {
            req.setAttribute("isRegisterSuccess", "0");
            req.setAttribute("errorMessage", "Username already exists. Please choose a different username.");
            setAttributesAndForward(req, rep, fullName, email, deliveryAddress, phone, username, password);
        } else if (CustomerDB.isEmailDuplicate(email)) {
            req.setAttribute("isRegisterSuccess", "0");
            req.setAttribute("errorMessage", "Email already exists. Please choose a different email.");
            setAttributesAndForward(req, rep, fullName, email, deliveryAddress, phone, username, password);
        }
        else
        {
            String hashEmail = email + "/not";
            req.setAttribute("isRegisterSuccess", "1");
            Cart cart = new Cart();
            CartDB.addCart(cart);
            Customer customer = new Customer(username, password, new Date(), fullName, phone, hashEmail, deliveryAddress, cart);
            CustomerDB.registerUser(customer);
            req.setAttribute("email",email);
            getServletContext().getRequestDispatcher("/mailservlet").forward(req, rep);
        }
    }
    private void setAttributesAndForward(HttpServletRequest req, HttpServletResponse rep,
                                         String fullName, String email, String deliveryAddress,
                                         String phone, String username, String password) throws ServletException, IOException {
        req.setAttribute("fullName", fullName);
        req.setAttribute("email", email);
        req.setAttribute("deliveryAddress", deliveryAddress);
        req.setAttribute("phone", phone);
        req.setAttribute("username", username);
        req.setAttribute("password", password);
        getServletContext().getRequestDispatcher("/views/LoginPage.jsp").forward(req, rep);
    }
}
