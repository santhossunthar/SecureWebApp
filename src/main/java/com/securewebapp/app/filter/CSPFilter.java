package com.securewebapp.app.filter;

import com.securewebapp.app.api.Endpoint;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CSPFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;

            String POLICY = "default-src 'self'; img-src 'self' https://s.gravatar.com/avatar/ " +
                    "https://cdn.auth0.com/avatars/ https://i1.wp.com/cdn.auth0.com/avatars;";
            response.setHeader("Content-Security-Policy", POLICY);
            chain.doFilter(request, response);
        } catch (ServletException | IOException ex) {
            ((HttpServletResponse) res).sendRedirect(Endpoint.root);
        }
    }

    @Override
    public void destroy() {}
}
