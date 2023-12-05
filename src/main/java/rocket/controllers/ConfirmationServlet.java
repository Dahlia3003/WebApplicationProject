package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.KeyAutheDB;
import rocket.models.KeyAuthe;

import java.io.IOException;
import java.security.Key;

@WebServlet("/views/confirm")
public class ConfirmationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userConfirmationCode = "http://localhost:8080/views/confirm?code="+request.getParameter("code");
        KeyAuthe keyAuthe = new KeyAuthe();
        keyAuthe.setKey(userConfirmationCode);

        System.out.println("usCode  "+userConfirmationCode);

        if (KeyAutheDB.keyExists(userConfirmationCode)) {
            // Xác nhận thành công, thực hiện các hành động cần thiết
            System.out.println("Confirmation successful! Done Done");

            // Xóa đường dẫn xác nhận khỏi session hoặc cơ sở dữ liệu
            KeyAutheDB.removeKey(keyAuthe);
            request.getSession().removeAttribute("confirmationLink");

            response.getWriter().write("Confirmation successful!");
        } else {
            response.getWriter().write("Invalid confirmation code");
        }
    }
}