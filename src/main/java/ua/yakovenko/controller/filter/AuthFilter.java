package ua.yakovenko.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter extends BaseFilter {
    @Override
    public void doFilter(HttpServletRequest request,
                         HttpServletResponse response,
                         FilterChain chain
    ) throws IOException, ServletException {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();

        chain.doFilter(request, response);
    }
}
