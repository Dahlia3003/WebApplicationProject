package rocket.controllers;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rocket.data.KeyAutheDB;
import rocket.models.Customer;
import rocket.models.KeyAuthe;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

@WebServlet("/views/sendConfirmationMail")
public class SendConfirmationMailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Customer customer = (Customer) session.getAttribute("customer");
        String userEmail = customer.getEmailAddress();
        System.out.println(userEmail);

        // Tạo đường dẫn xác nhận ngẫu nhiên
        String confirmationLink = generateConfirmationLink();

        System.out.println("confirmationLink " + confirmationLink);
        // Lưu đường dẫn xác nhận trong session hoặc cơ sở dữ liệu
        System.out.println("cusid"+customer.getUserID());
        KeyAuthe keyAuthe =new KeyAuthe();
        keyAuthe.setKey(confirmationLink);
        keyAuthe.setCusId(customer.getUserID());
        keyAuthe.setMail((String) request.getParameter("MailChange"));
        System.out.println(request.getParameter("MailChange"));
        KeyAutheDB.addKey(keyAuthe);
        request.getSession().setAttribute("confirmationLink", confirmationLink);
        request.getSession().setAttribute("userEmail", userEmail);
        System.out.println(keyAuthe.getKey());

        // Gửi email với đường dẫn xác nhận
        sendConfirmationEmail(userEmail, confirmationLink);

        Boolean isConfirm = (Boolean) session.getAttribute("isConfirm");
        session.setAttribute("isConfirmSession", true);

        session.setAttribute("confirmationMessage", "Confirmation email sent successfully");
        request.setAttribute("redirectURL", request.getContextPath() + "/views/profile");
        response.sendRedirect(request.getContextPath() + "/views/profile");
    }

    private String generateConfirmationLink() {
        // Sử dụng UUID để tạo một đường dẫn xác nhận duy nhất
        return "http://localhost:8080/views/confirm?code=" + UUID.randomUUID().toString();
    }

    private void sendConfirmationEmail(String toEmail, String confirmationLink) {
        // Điều chỉnh cài đặt email của bạn ở đây
        String fromEmail = "duyvnlx3016@gmail.com"; // Replace with your email
        String appPassword = "cetd prqi badk wkit"; // Replace with your App Password

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
            message.setSubject("Confirmation Email");
            message.setText("Please click on the following link to confirm your email: " + confirmationLink);

            Transport.send(message);

            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}