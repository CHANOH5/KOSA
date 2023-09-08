package control;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

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
	
		Enumeration<String> em = req.getHeaderNames();
		while(em.hasMoreElements()) {
			
			String name= em.nextElement(); // 요청 헤더 이름
			String value = req.getHeader(name);
			
			System.out.println(name + ":" + value);
			
		} // while
		
		// Attribute는 key, value값으로 저장되고 Object타입이다
		req.setAttribute("msg", "요청속성1");
		req.setAttribute("cnt", 1);
		req.setAttribute("now", new Date());
		
		// Attribute는 Obejct타입이므로 String타입으로 강제형변환 필요!
		String msg = (String)req.getAttribute("msg");
		int cnt = (Integer)req.getAttribute("cnt");
		req.removeAttribute("now");
		
	} // doGet

} // end class
