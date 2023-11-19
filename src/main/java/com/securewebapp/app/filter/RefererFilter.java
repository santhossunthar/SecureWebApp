package com.securewebapp.app.filter;

import com.securewebapp.app.api.ApplicationURL;
import com.securewebapp.app.api.Endpoint;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RefererFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        try{
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            String referer = request.getHeader("Referer");

            if (isValidReferer(referer)) {
                chain.doFilter(req, res);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Referer");
            }
        } catch (ServletException | IOException ex){
            ((HttpServletResponse) res).sendRedirect(Endpoint.root);
        }
    }

    private boolean isValidReferer(String referer) {
        return referer != null
                && referer.startsWith(ApplicationURL.getURL());
    }

    @Override
    public void destroy() {}
}
