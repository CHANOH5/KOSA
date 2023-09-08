package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/req")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
//		String tValue = req.getParameter("t");
//		String cValue = req.getParameter("c");
//		System.out.println(tValue + ":" + cValue);
		
		String cValues[] = req.getParameterValues("c");
		
		if(cValues != null) {
			
			for(int i = 0; i < cValues.length; i++) {
				System.out.println(cValues[i]);
			} // for 
			
		} // if
		
		// web Context에 대한 경로
		System.out.println("req.getContextPath() : " + req.getContextPath()); // /back
		
		System.out.println("req.getRequestURL() : " + req.getRequestURL()); // http://localhost:8888/back/req
		System.out.println("req.getRequestURI() : " + req.getRequestURI()); // /back/req
		System.out.println("req.getServletPath() : " + req.getServletPath()); // /req
	} // doGet

} // end class
