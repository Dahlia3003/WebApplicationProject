package rocket.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "LogOutServlet", value = "/views/logout")

public class LogOutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        doPost(req, rep);
    }
    protected  void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        HttpSession session = req.getSession();

        Cookie cookie = new Cookie("cusID", "");
        cookie.setMaxAge(0);
        rep.addCookie(cookie);
        session.invalidate();

        getServletContext().getRequestDispatcher("/homeservlet").forward(req,rep);
    }
}
