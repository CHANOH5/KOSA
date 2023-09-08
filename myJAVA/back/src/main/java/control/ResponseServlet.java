package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/res")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// 응답형식 설정 "application/json", "text/plain"
		res.setContentType("text/html; charset=utf-8");
		
		// 응답용 출력스트림 얻기
		PrintWriter out = res.getWriter();
		
		// OutputStream은 바이트단위로 출력
		// Writer는 문자단위로 출력
		out.print("<h1>찬돌짱</h1>");
		out.print("<table>");
		out.print("<tr>");
		for(int i = 1; i<=5; i++) {
			out.print("<td>"); out.print(i); out.print("</td>");
		}
		out.print("</tr>");
		out.print("</table>");
	} // doGet

} // endclass
