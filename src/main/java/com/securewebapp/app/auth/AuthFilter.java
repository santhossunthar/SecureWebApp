package com.securewebapp.app.auth;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    public void init(FilterConfig filterConfig) {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getServletPath();

        if (isAuthenticated(request)) {
            chain.doFilter(request, response);
            return;
        }

        response.sendRedirect("/login");
    }

    public void destroy() {}

    private boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession(false) != null
                && request.getSession().getAttribute("userId") != null;
    }
}
