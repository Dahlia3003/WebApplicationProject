package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.CustomerDB;
import rocket.data.KeyAutheDB;
import rocket.models.Customer;
import rocket.models.KeyAuthe;

import java.io.IOException;
import java.security.Key;

@WebServlet("/views/confirm")
public class ConfirmationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userConfirmationCode = "http://localhost:8080/views/confirm?code="+request.getParameter("code");
        KeyAuthe keyAuthe = new KeyAuthe();
        Customer customer = new Customer();
        System.out.println("usCode  "+userConfirmationCode);

        if (KeyAutheDB.getKeyByValue(userConfirmationCode)!=null) {
            keyAuthe = (KeyAuthe) KeyAutheDB.getKeyByValue(userConfirmationCode);
            // Xác nhận thành công, thực hiện các hành động cần thiết
            System.out.println("Confirmation successful! Done Done");
            customer = CustomerDB.getCustomerById(keyAuthe.getCusId());
            customer.setEmailAddress(keyAuthe.getMail());
            handleupdateMail(customer);
            // Xóa đường dẫn xác nhận khỏi session hoặc cơ sở dữ liệu
            KeyAutheDB.removeKey(keyAuthe);

            request.getSession().removeAttribute("confirmationLink");

            response.getWriter().write("Confirmation successful!");
        } else {
            response.getWriter().write("Invalid confirmation code");
        }
    }

    private void handleupdateMail(Customer customer) {
        try {
            System.out.println("cusidup"+customer.getUserID());
            System.out.println("adup"+customer.getEmailAddress());
            CustomerDB.updateProfile(customer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
