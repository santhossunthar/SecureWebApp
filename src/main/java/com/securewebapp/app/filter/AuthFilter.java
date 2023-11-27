package com.securewebapp.app.filter;

import com.securewebapp.app.api.Endpoint;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    public void init(FilterConfig filterConfig) {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;

            if (isAuthenticated(request)) {
                chain.doFilter(request, response);
                return;
            }

            response.sendRedirect(Endpoint.login);
        } catch (ServletException | IOException ex) {
            ((HttpServletResponse) res).sendRedirect(Endpoint.root);
        }
    }

    public void destroy() {}

    private boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession(false) != null
                && request.getSession().getAttribute("userId") != null;
    }
}
