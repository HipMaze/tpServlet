package tpServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Identification {
	static String chercheNom (Cookie [] cookies, String nom)
	{
		if (cookies != null)
	        for (Cookie cookie : cookies) {
	            if(nom.equals(cookie.getName()))return cookie.getValue();
	        }
		return "inconnu";
	}
}