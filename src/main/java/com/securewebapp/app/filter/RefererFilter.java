package com.securewebapp.app.filter;

import com.securewebapp.app.api.ApplicationURL;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RefererFilter implements Filter {
    private static final String applicationURL = ApplicationURL.getURL();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String referer = request.getHeader("Referer");

        if (isValidReferer(referer)) {
            chain.doFilter(req, res);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Referer");
        }
    }

    private boolean isValidReferer(String referer) {
        return referer != null
                && referer.startsWith(applicationURL);
    }

    @Override
    public void destroy() {}
}
