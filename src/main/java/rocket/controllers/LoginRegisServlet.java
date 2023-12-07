package rocket.controllers;

import jakarta.mail.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import rocket.data.AdminDB;
import rocket.data.CartDB;
import rocket.data.CustomerDB;
import rocket.models.Admin;
import rocket.models.Cart;
import rocket.models.Customer;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

@WebServlet(name = "LoginRegisterServlet", value = "/loginregister")
public class LoginRegisServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        req.setAttribute("action", action);
        if ("register".equals(action)) {
            register(req, rep);
        }
        if ("forgetpassword".equals(action)){
            forgetpass(req,rep);
        }
        if ("login".equals(action)){
            login(req, rep);
        }
        System.out.println("Im here");
    }
    private void login(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        String text = req.getParameter("logintext");
        String pass = req.getParameter("password");
        String remember = "";
        if( req.getParameter("remember")!=null){
            remember =req.getParameter("remember");
        }
        System.out.println(remember);
        Customer cus1 = CustomerDB.getCustomerByEmail(text);
        Customer cus2 = CustomerDB.getCustomerById(text);
        Admin admin = AdminDB.getAdminById(text);
        HttpSession s = req.getSession();

        if (cus1!=null && cus1.getPassword().equals(pass))
        {
            Cookie nameCookie = new Cookie("cusID", cus1.getUserID());
            s.setAttribute("cusID", cus1.getUserID());
            if (remember.equals("true"))
            {
                nameCookie.setMaxAge(60 * 60 * 24); // Cookie expires in 1 day (adjust as needed)
                rep.addCookie(nameCookie);
                s.setAttribute("cusID", nameCookie);
            }
            else
            {
                s.setAttribute("cusID", nameCookie);
            }

            rep.sendRedirect(req.getContextPath() + "/homeservlet");
        }
        else if (cus2!=null && cus2.getPassword().equals(pass) )
        {
            if (!cus2.getEmailAddress().contains("/not"))
            {
                Cookie nameCookie = new Cookie("cusID", cus2.getUserID());
                s.setAttribute("cusID", cus2.getUserID());
                if (remember.equals("true"))
                {
                    nameCookie.setMaxAge(60 * 60 * 24); // Cookie expires in 1 day (adjust as needed)
                    rep.addCookie(nameCookie);
                    s.setAttribute("cusID", nameCookie);
                }
                else
                {
                    s.setAttribute("cusID", nameCookie);
                }
            }
            else
            {
                req.setAttribute("isLoginSuccess", "0");
                getServletContext().getRequestDispatcher("/views/LoginPage.jsp").forward(req, rep);
            }
        }
        else if (admin!=null && admin.getPassword().equals(pass) )
        {
            rep.sendRedirect(req.getContextPath() + "/manage");
        }
        else {
            req.setAttribute("isLoginSuccess", "0");
            getServletContext().getRequestDispatcher("/views/LoginPage.jsp").forward(req, rep);
        }
    }
    private void forgetpass(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (CustomerDB.isEmailDuplicate(email)) {
            req.setAttribute("email",email);
            req.setAttribute("isForgetSuccess", "1");
            getServletContext().getRequestDispatcher("/mailservlet").forward(req, rep);
        }
        else
        {
            req.setAttribute("isForgetSuccess", "0");
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
