package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.data.OrderDB;
import rocket.models.Order;

import java.io.IOException;

@WebServlet("/updateOrderStatus")
public class UpdateOrderStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdString = request.getParameter("orderId");
        String newStatus = request.getParameter("newStatus");

        // Chuyển đổi orderId thành kiểu integer
        int orderId = Integer.parseInt(orderIdString);

        // Lấy đối tượng Order từ cơ sở dữ liệu bằng JPA
        Order order = OrderDB.getOrderById(orderId);

        // Cập nhật trạng thái và lưu vào cơ sở dữ liệu
        if (order != null) {
            order.setStatus(newStatus);
            OrderDB.updateOrder(order);
        }

        // Phản hồi cho client (nếu cần)
        response.getWriter().write("Status updated successfully");
    }
}
