package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet ( value ="/manage")
public class ManageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println(action);
        if (action.equals("register"))
        {
            request.getServletContext().getRequestDispatcher("/views/RegisterSuccess.jsp").forward(request,response);
        }
        else
        {
            request.getServletContext().getRequestDispatcher("/views/ForgotPasswordSendMail.jsp").forward(request,response);
        }
    }
}
