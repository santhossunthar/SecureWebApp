package com.securewebapp.app.filter;

import com.securewebapp.app.api.ApplicationURL;
import com.securewebapp.app.api.Endpoint;
import com.securewebapp.app.auth.AuthConfig;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RefererFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AuthConfig.class.getName());

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            String referer = request.getHeader("Referer");

            if (isValidReferer(request, referer)) {
                chain.doFilter(req, res);
            } else {
                logger.log(Level.SEVERE, "An error occurred while validating referer header");
                ((HttpServletResponse) res).sendRedirect(Endpoint.root);
            }
        } catch (ServletException | IOException ex) {
            logger.log(Level.SEVERE, "An error occurred: " + ex.getMessage(), ex);
            ((HttpServletResponse) res).sendRedirect(Endpoint.root);
        }
    }

    private boolean isValidReferer(HttpServletRequest request, String referer) {
        if (referer != null && referer.startsWith(ApplicationURL.getURL())) {
            return true;
        } else return request.getRequestURL().toString().startsWith(ApplicationURL.getURL());
    }

    @Override
    public void destroy() {
    }
}
