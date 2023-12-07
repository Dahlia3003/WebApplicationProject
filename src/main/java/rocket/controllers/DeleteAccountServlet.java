package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.CustomerDB;

import java.io.IOException;

@WebServlet("/deleteAccount")
public class DeleteAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        if (userId != null && !userId.isEmpty()) {
            // Gọi phương thức xóa tài khoản từ CustomerDB
            CustomerDB.deleteAccount(userId);

            // Gửi phản hồi thành công
            response.getWriter().write("Account deleted successfully");
        } else {
            // Gửi phản hồi lỗi
            response.getWriter().write("Error deleting account");
        }
    }
}
