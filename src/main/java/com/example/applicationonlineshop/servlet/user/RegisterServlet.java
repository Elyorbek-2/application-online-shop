package com.example.applicationonlineshop.servlet.user;

import com.example.applicationonlineshop.dao.AuthUserDAO;
import com.example.applicationonlineshop.entity.AuthUser;
import com.example.applicationonlineshop.enums.Role;
import com.example.applicationonlineshop.enums.Status;
import com.example.applicationonlineshop.utils.PasswordUtil;
import com.example.applicationonlineshop.utils.SendingEmailExample;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {

    private final AuthUserDAO authUserDAO = new AuthUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var map = new HashMap<String, String>();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");
        Optional<AuthUser> optionalAuthUser = authUserDAO.findByEmail(email);
        if (!optionalAuthUser.isEmpty()) {
            map.put("email_error", "Email is invalid");
        }
        if (password == null)
            map.put("password_error", "Password is invalid");
        if (!Objects.equals(password, confirm_password)) {
            map.put("password_error", "Password is invalid");
        }
        if (!map.isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/login.jsp");
            map.forEach(req::setAttribute);
            dispatcher.forward(req, resp);
        }

        if (!optionalAuthUser.isPresent()) {

            String activationCode = UUID.randomUUID().toString();
            AuthUser user = AuthUser
                    .childBuilderName()
                    .email(email)
                    .password(PasswordUtil.encode(password))
                    .role(Role.ROLE_USER)
                    .phonenumber("123")
                    .status(Status.BLOCKED)
                    .activationCode(activationCode)
                    .build();
            authUserDAO.save(user);
            String link = "localhost:8080/activated?email=%s&activationCode=%s".formatted(email, activationCode);
            boolean result = SendingEmailExample.sendMessage(email, link);
            if (result) {
                resp.getWriter().write("Verify your account");
            }else
                resp.getWriter().write("Account not found");
        }
        /*<h1><a href='/localhost:8080/activated?email=farruxmashrapov92@gmail.com&activationCode=04fc0dcd-b033-43b7-9f9e-6dcef09e4e38'>Click to confirm your account</a></h1>*/
    }
}
