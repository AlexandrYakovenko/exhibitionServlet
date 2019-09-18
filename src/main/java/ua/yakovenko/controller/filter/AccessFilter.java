package ua.yakovenko.controller.filter;


import ua.yakovenko.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();
        if (path.contains("admin")) {
            if (request.getSession().getAttribute("role") == Role.ADMIN) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                request.setAttribute("error", true);
                request.setAttribute("message", "AccessDenied");
//              response.sendRedirect("/index.jsp");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}