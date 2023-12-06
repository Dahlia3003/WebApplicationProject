package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rocket.data.CustomerDB;
import rocket.data.KeyAutheDB;
import rocket.models.Customer;
import rocket.models.KeyAuthe;

import java.io.IOException;
@WebServlet(name="MailConfirmServlet", value = "/mailconfirm")
public class MailConfirmServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String fullURL = request.getRequestURL().append('?').append(queryString).toString();

        System.out.println(fullURL);
        if (KeyAutheDB.keyExists(fullURL))
        {
            if(action.equals("register")) {
                String hascode = "/not";
                String hashemail = email + hascode;
                Customer cus = CustomerDB.getCustomerByEmail(hashemail);
                cus.setEmailAddress(email);
                CustomerDB.updateProfile(cus);
                KeyAutheDB.removeKey(KeyAutheDB.findKey(fullURL));
                getServletContext().getRequestDispatcher("/views/ConfirmRedirect.jsp").forward(request,response);
            }
            if (action.equals("forgetpassword")){
                KeyAutheDB.removeKey(KeyAutheDB.findKey(fullURL));
                getServletContext().getRequestDispatcher("/views/ForgotPassword.jsp").forward(request,response);
            }
        }
        else
        {
            getServletContext().getRequestDispatcher("/views/LinkExpired.jsp").forward(request,response);
        }
    }
}
