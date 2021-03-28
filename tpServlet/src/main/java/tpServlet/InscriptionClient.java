package tpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class InscriptionClient extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	
	private void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
	    Cookie[] cookies = req.getCookies();
	    if (cookies != null)
	        for (Cookie cookie : cookies) {
	            cookie.setValue("");
	            cookie.setMaxAge(0);
	            resp.addCookie(cookie);
	        }
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		String nomRecu=null, motPasseRecu=null;
		String nomCookie=null, motPasseCookie=null;
		Cookie c[] = null;
		//  initialisation cookies et param�tres         
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		nomRecu = request.getParameter("nom");
		motPasseRecu = request.getParameter("motdepasse");
		c = request.getCookies();
		if(c != null) {
			nomCookie = c[0].getName();
			motPasseCookie = c[0].getValue();
		}
		if (nomCookie==null && nomRecu==null){
			// Cas 1 :cas o� il n'y a ni de cookies ni de parametres
			out.println("<html>");
			out.println("<body>");
			out.println("<head>");
			out.println("<title>   inscription d'un client  </title>");
			out.println("</head>");
			out.println("<body bgcolor='white' >");
			out.println( nomRecu +" | "+ motPasseRecu +" | "+ nomCookie +" | "+ motPasseCookie );
			out.println("<h3>" + "Bonjour,  vous devez vous inscrire " + "</h3>");
			out.println("<h3>" + "Attention mettre  nom et le mot de passe avec plus de 3 caracteres" + "</h3>");
			out.print(" <form action='sinscrire' method='GET' > ");
			out.println("nom");
			out.println("<input type='text' size='20' name='nom'  >");
			out.println("<br>");
			out.println("mot de passe");
			out.println("<input type='password' size='20' name='motdepasse'> <br>");
			out.println("<input type='submit' value='inscription'>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");         
			} else  if (nomCookie==null && nomRecu!=null){
				Cookie co = new Cookie(nomRecu,motPasseRecu);
				response.addCookie(co);
				out.println("<html>");
				out.println("<body>");
				out.println("<head>");
				out.println("<title> case2 </title>");
				out.println("</head>");
				out.println("<body bgcolor='white' >");
				out.println( nomRecu +" | "+ motPasseRecu +" | "+ nomCookie +" | "+ motPasseCookie ); 
				out.print(" <form action='sinscrire' method='GET' > ");
				out.println("Cookies Saved");
				out.println("<input type='submit' value='next'>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				System.out.println(nomRecu);
				System.out.println(motPasseRecu);
				System.out.println(nomCookie);
				System.out.println(motPasseCookie);
			}else if (identique(nomRecu,nomCookie) && identique(motPasseRecu,motPasseCookie)){
				//  Cas 4 :cas o� le nom et le mot passe sont correctes, appel � la servlet achat
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/achat");
				eraseCookie(request,response);
			    dispatcher.forward(request, response);
			}else {
				response.addCookie(request.getCookies()[0]);
				out.println("<html>");
				out.println("<body>");
				out.println("<head>");
				out.println("<title>   inscription d'un client  </title>");
				out.println("</head>");
				out.println("<body bgcolor='white' >");
				out.println( nomRecu +" | "+ motPasseRecu +" | "+ nomCookie +" | "+ motPasseCookie );
				out.println("<h3>" + "Bonjour,  vous devez vous identifier : " + "</h3>");
				out.print(" <form action='sinscrire' method='GET' > ");
				out.println("nom");
				out.println("<input type='text' size='20' name='nom'  >");
				out.println("<br>");
				out.println("mot de passe");
				out.println("<input type='password' size='20' name='motdepasse'> <br>");
				out.println("<input type='submit' value='inscription'>");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");    
			}     
	}    
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException {
			doGet(request, response);
		}      
	boolean identique (String recu, String cookie) {         
			return ((recu != null) /*&& (recu.length() >3)*/ && (cookie != null)/* && (recu.equals(cookie) )*/);
		}
}
