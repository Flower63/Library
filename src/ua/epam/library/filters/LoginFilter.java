package ua.epam.library.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filter to control access to /app/ folder.
 * Filter checks, if current session contains authorized user (reader)
 * 
 * @author Dennis
 *
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		
		if (session != null && session.getAttribute("reader") != null) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
