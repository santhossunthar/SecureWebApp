package com.securewebapp.app.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CSPFilter implements Filter {
    public final String POLICY = "default-src 'self'; img-src 'self' https://s.gravatar.com/avatar/ " +
            "https://cdn.auth0.com/avatars/ https://i1.wp.com/cdn.auth0.com/avatars;";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        if (resp instanceof HttpServletResponse) {
            ((HttpServletResponse) resp).setHeader("Content-Security-Policy", POLICY);
            chain.doFilter(req, resp);
            return;
        }

        ((HttpServletResponse) resp).sendRedirect("/");
    }

    @Override
    public void destroy() {
    }
}
