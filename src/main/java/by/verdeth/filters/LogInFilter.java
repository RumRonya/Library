package by.verdeth.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/index","/books","/about","/authors"})
public class LogInFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;


        HttpSession session =  httpRequest.getSession(false);
        if (session == null || session.getAttribute("nameUser")==null)
        {
            httpResponse.sendRedirect("/login");
        }
        else
        {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
