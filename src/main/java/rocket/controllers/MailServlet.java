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

@WebServlet(name="MailServlet", value ="/mailservlet")
public class MailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        System.out.println(action);
        String userEmail =  request.getParameter("email");
        // Tạo đường dẫn xác nhận ngẫu nhiên
        String confirmationLink = generateConfirmationLink(action, userEmail);
        // Lưu đường dẫn xác nhận trong session hoặc cơ sở dữ liệu
        KeyAuthe keyAuthe =new KeyAuthe();
        keyAuthe.setKey(confirmationLink);
        KeyAutheDB.addKey(keyAuthe);
        request.getSession().setAttribute("confirmationLink", confirmationLink);
        request.getSession().setAttribute("userEmail", userEmail);


        sendConfirmationEmail(userEmail, confirmationLink);

//        Boolean isConfirm = (Boolean) session.getAttribute("isConfirm");
//        session.setAttribute("isConfirmSession", true);
//
//        session.setAttribute("confirmationMessage", "Confirmation email sent successfully");
//        request.setAttribute("redirectURL", request.getContextPath() + "/views/profile");
        if (action.equals("register"))
        {
            request.getServletContext().getRequestDispatcher("/views/RegisterSuccess.jsp").forward(request,response);
        }
        else
        {
            request.setAttribute("isForgetSuccess", "1");
            request.getServletContext().getRequestDispatcher("/views/ForgotPasswordSendMail.jsp").forward(request,response);
        }
    }

    private String generateConfirmationLink(String action, String email) {
        return "http://localhost:8080/mailconfirm?action=" + action + "&email=" + email + "&code=" + UUID.randomUUID().toString();
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


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
