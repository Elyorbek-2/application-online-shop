package com.example.applicationonlineshop.servlet.user;

import com.example.applicationonlineshop.dao.AuthUserDAO;
import com.example.applicationonlineshop.entity.AuthUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/activated")
public class ActivateUser extends HttpServlet {
    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String activationCode = req.getParameter("activationCode");
        Optional<AuthUser> optionalAuthUser = authUserDAO.emailAndActivationCode(email, activationCode);
        if (!optionalAuthUser.isPresent()) {
            req.setAttribute("error_message", "Email not activated 🤢🤢🤢🤢🤢🤢");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user/activate.jsp");
            requestDispatcher.forward(req, resp);
        }
        boolean result = authUserDAO.editStatusUser(email);
        if (result) {
            req.setAttribute("error_message", "Accaunt verifying 👍👍👍👍👍👍");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user/activate.jsp");
            requestDispatcher.forward(req, resp);
        }else {
            resp.getWriter().write("O'zgartira olmadik");
        }


    }
}
