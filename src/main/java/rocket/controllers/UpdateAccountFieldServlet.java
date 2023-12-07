package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.CustomerDB;
import rocket.models.Customer;

import java.io.IOException;

@WebServlet("/updateAccountField")
public class UpdateAccountFieldServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String field = request.getParameter("field");
        String value = request.getParameter("value");

        System.out.println("uid"+userId);
        // Lấy đối tượng Customer từ cơ sở dữ liệu bằng JPA
        Customer customer = CustomerDB.getCustomerById(userId);

        // Cập nhật giá trị và lưu vào cơ sở dữ liệu
        if (customer != null) {
            if ("userId".equals(field)) {
                // Nếu field là userId, không cập nhật vì userId là khóa chính và không thể thay đổi
            } else if ("password".equals(field)) {
                customer.setPassword(value);
            } else if ("customerName".equals(field)) {
                customer.setCustomerName(value);
                System.out.println(value);
            } else if ("phoneNumber".equals(field)) {
                customer.setPhoneNumber(value);
            } else if ("emailAddress".equals(field)) {
                customer.setEmailAddress(value);
            } else if ("deliveryAddressDefault".equals(field)) {
                customer.setDeliveryAddressDefault(value);
            }


            CustomerDB.updateCustomer(customer);
        }

        // Phản hồi cho client (nếu cần)
        response.getWriter().write("Account field updated successfully");
    }
}
