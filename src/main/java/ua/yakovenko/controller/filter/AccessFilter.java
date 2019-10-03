package ua.yakovenko.controller.filter;


import ua.yakovenko.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request,
                         HttpServletResponse response,
                         FilterChain chain
    ) throws IOException, ServletException {
        String path = request.getRequestURI();

        if (path.contains("user")) {
            if (request.getSession().getAttribute("role") == Role.ADMIN ||
                    request.getSession().getAttribute("role") == Role.USER) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect("/exhibition/index");
            }
        } else if (path.contains("admin")) {
            if (request.getSession().getAttribute("role") == Role.ADMIN ) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect("/exhibition/index");
            }
        }  else if (path.contains("super_admin")) {
            if (request.getSession().getAttribute("role") == Role.SUPER_ADMIN) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect("/exhibition/index");
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}