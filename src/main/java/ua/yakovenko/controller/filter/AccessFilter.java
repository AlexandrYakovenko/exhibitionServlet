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

        if (path.contains("admin")) {
            if (request.getSession().getAttribute("role") == Role.ADMIN ||
                    request.getSession().getAttribute("role") == Role.SUPER_ADMIN) {
                chain.doFilter(request, response);
            } else {
                request.setAttribute("error", true);
                request.setAttribute("message", "AccessDenied");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}