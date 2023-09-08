package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/move")
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// sendRedirect 메서드사용
//		res.sendRedirect("http://www.naver.com");
		
		RequestDispatcher rd = req.getRequestDispatcher("req"); // ""에 /url을 적어주면 된다
		rd.forward(req, res);
		
	} // doGet
	
} // end class
