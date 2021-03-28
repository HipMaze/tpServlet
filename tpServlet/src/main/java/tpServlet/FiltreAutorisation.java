package tpServlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class FiltreAutorisation extends HttpServlet implements Filter{
	private FilterConfig filterConfig = null;
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		}
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		String nom = null;
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		Cookie[] cookies = hrequest.getCookies(); 
		
		if ("nom".equals(Identification.chercheNom(cookies,"nom"))){
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/sinscrire");
			    dispatcher.forward(request, response); 
			}  else {  
				chain.doFilter(request, response); 
			}
}
public void destroy() {
	this.filterConfig = null;
	}
}