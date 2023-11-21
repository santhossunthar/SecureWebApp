package com.securewebapp.app.filter;

import com.securewebapp.app.api.Endpoint;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CSPFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;

            String POLICY = "default-src 'self';" +
                    "script-src 'self' 'unsafe-inline';" +
                    "style-src 'self' 'unsafe-inline';" +
                    "img-src 'self' https://s.gravatar.com/avatar/ https://cdn.auth0.com/avatars/ " +
                        "https://i1.wp.com/cdn.auth0.com/avatars/ https://lh3.googleusercontent.com/;" +
                    "font-src 'self';" +
                    "object-src 'none';" +
                    "frame-ancestors 'none';" +
                    "base-uri 'self';" +
                    "form-action 'self';" +
                    "block-all-mixed-content;";

            response.setHeader("Content-Security-Policy", POLICY);
            chain.doFilter(request, response);
        } catch (ServletException | IOException ex) {
            ((HttpServletResponse) res).sendRedirect(Endpoint.root);
        }
    }

    @Override
    public void destroy() {}
}
