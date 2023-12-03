package rocket.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rocket.Util.DBUtil;
import rocket.models.Cart;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "cartServlet", value = "/cart")
public class CartServlet {
    // Trong phương thức doGet của CartServlet
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.getEmf().createEntityManager();
        try {
            // Bắt đầu giao dịch
            em.getTransaction().begin();

            // Truy vấn cơ sở dữ liệu để lấy danh sách Cart và CartLine
            Query query = em.createQuery("SELECT c FROM Cart c WHERE c.id = :cartId", Cart.class);
            query.setParameter("cartId", "1");
            Cart cart = (Cart) query.getSingleResult();
            // Kết thúc giao dịch
            em.getTransaction().commit();

            // Đặt danh sách Cart và CartLine vào request attribute
            request.setAttribute("cart", cart);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có bất kỳ lỗi nào xảy ra
            em.getTransaction().rollback();
            e.printStackTrace(); // Thay bằng xử lý lỗi phù hợp trong ứng dụng của bạn
        } finally {
            // Đóng EntityManager sau khi hoàn thành
            em.close();
        }

        // Chuyển hướng đến trang Cart.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/Cart.jsp");
        dispatcher.forward(request, response);
    }
}

