package rocket.controllers;

import com.google.gson.Gson;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rocket.data.CartDB;
import rocket.data.CustomerDB;
import rocket.data.OrderDB;
import rocket.models.Cart;
import rocket.models.CartLine;
import rocket.models.Customer;
import rocket.models.Order;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "checkoutServlet", value = "/views/checkout")
public class CheckoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String cusID = (String) session.getAttribute("cusID");
        Customer customer = CustomerDB.getProfile(cusID);
        request.setAttribute("message", "Bạn muốn thanh toán như thế nào?");
        request.setAttribute("customer", customer);
        int cost = CartDB.getCartById((Integer) CustomerDB.getCustomerById(cusID).getCart().getId()).calcTotal();
        request.setAttribute("cost", cost);
        System.out.println(cost);
        String url = "/views/Checkout.jsp";
        String paid = request.getParameter("paid");
        String method = request.getParameter("method");
        System.out.println(paid+method);
        if (paid!=null)
        {
            if (paid.equals("true") && cost != 0) {
                System.out.println("ok");
                Integer cartID = (Integer) CustomerDB.getCustomerById((String) session.getAttribute("cusID")).getCart().getId();
                System.out.println(cartID);
                Cart cart = CartDB.getCartById(cartID);
                List<CartLine> cartLines = new ArrayList<>(cart.getCartList());
                CartDB.removeAllCartItem(cart);
                Order order = new Order(cartLines, new Date(), customer, "Đang giao", method, 0);
                OrderDB.addOrder(order);
                sendOrderedEmail(customer.getEmailAddress(), order);
                request.setAttribute("message", "Xác nhận thanh toán thành công!");
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    private void sendOrderedEmail(String toEmail, Order order) {
        // Điều chỉnh cài đặt email của bạn ở đây
        String fromEmail = "duyvnlx3016@gmail.com";
        String appPassword = "cetd prqi badk wkit";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, appPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            try {
                message.setSubject(MimeUtility.encodeText("Bạn đã đặt một đơn hàng", "UTF-8", "B"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Error encoding subject", e);
            }
            Multipart multipart = new MimeMultipart();
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Bạn vừa đặt thành công một đơn hàng trên RocketStore!", "utf-8", "html");
            multipart.addBodyPart(textPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Email sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
