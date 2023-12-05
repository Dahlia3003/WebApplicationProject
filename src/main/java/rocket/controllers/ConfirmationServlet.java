package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/views/confirm")
public class ConfirmationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userConfirmationCode = "http://localhost:8080/testmail_war_exploded/confirm?code="+request.getParameter("code");
        String storedConfirmationCode = (String) request.getSession().getAttribute("confirmationLink");

        System.out.println("stCode  "+storedConfirmationCode);
        System.out.println("usCode  "+userConfirmationCode);

        if (userConfirmationCode.equals(storedConfirmationCode)) {
            // Xác nhận thành công, thực hiện các hành động cần thiết
            System.out.println("Confirmation successful! Done Done");

            // Xóa đường dẫn xác nhận khỏi session hoặc cơ sở dữ liệu
            request.getSession().removeAttribute("confirmationLink");

            response.getWriter().write("Confirmation successful!");
        } else {
            response.getWriter().write("Invalid confirmation code");
        }
    }
}