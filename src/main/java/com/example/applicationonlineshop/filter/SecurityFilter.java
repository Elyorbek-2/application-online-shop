package com.example.applicationonlineshop.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

@WebFilter(value = "/*")
public class SecurityFilter implements Filter {
    private static final List<String> WHITE_LIST = List.of(
            "/",
            "/createBook",
            "/deleteBook",
            "/updateBook"
    );
    //    private static final Predicate<String> isOpen=(uri)->WHITE_LIST.contains(uri);

    private static final Predicate<String> isOpen = WHITE_LIST::contains;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
        } else {
            chain.doFilter(request, response);
        }*/
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        System.out.println(req.getRequestURI());
        String requestURI = req.getRequestURI();
        if (isOpen.test(requestURI)) {
            res.sendRedirect("/login");
        } else
            chain.doFilter(req, res);
    }

}
