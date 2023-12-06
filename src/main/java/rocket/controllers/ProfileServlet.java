package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rocket.models.Customer;
import rocket.data.CustomerDB;

import java.io.IOException;

@WebServlet(name = "profileServlet", value = "/views/profile")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        // Check if the customer is already in the session, if not, create a new one
        Customer customer = (Customer) session.getAttribute("customer");

        // If the customer is not in the session, fetch it from the database or create a new one
        if (customer == null) {
            String customerId = (String) session.getAttribute("cusID");
            System.out.println("cus"+customerId);
            customer = CustomerDB.getCustomerById(customerId);

            // If the customer is still null, create a new one
            if (customer == null) {
                customer = new Customer();
            }
            session.setAttribute("customer", customer);
            customer = CustomerDB.getCustomerById(customer.getUserID());
            System.out.println("aaa"+customer.getEmailAddress());
        }
        customer = CustomerDB.getCustomerById(customer.getUserID());
        System.out.println("aaa"+customer.getEmailAddress());
        // Handle the actions based on the request parameters
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "Send mail":
                    handleupdateMail(request, customer);
                    break;
                case "Save":
                    handleUpdateProfile(request, customer);
                    break;
            }
        }
        System.out.println(customer.getDeliveryAddressDefault());

        request.setAttribute("customer", customer);
        Boolean isConfirmSession = false;
        if(session.getAttribute("isConfirmSession")!=null){
            isConfirmSession = (Boolean) session.getAttribute("isConfirmSession");
        }
        System.out.println(isConfirmSession);
        if(isConfirmSession){
            System.out.println("1");
            System.out.println(session.getAttribute("confirmationMessage"));
            session.setAttribute("isConfirmSession", false);
            request.getRequestDispatcher("/views/ChangeMail.jsp").forward(request, response);
        }
        else {
            System.out.println("2");
            request.getRequestDispatcher("/views/ProfilePage.jsp").forward(request, response);
            session.setAttribute("isConfirmSession", false);
        }
    }

    private void handleUpdateProfile(HttpServletRequest request, Customer customer) {
        customer.setCustomerName(request.getParameter("CustomerName"));
        customer.setPhoneNumber(request.getParameter("PhoneNum"));
        customer.setDeliveryAddressDefault(request.getParameter("Deli"));
        String mail = customer.getEmailAddress();
            try {
                CustomerDB.updateProfile(customer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
    }
    private void handleupdateMail(HttpServletRequest request, Customer customer) {
        customer.setEmailAddress(request.getParameter("MailChange"));
            try {
                CustomerDB.updateProfile(customer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
    }
}
