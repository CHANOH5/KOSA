package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/score")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int totalScore; 
	int totalCnt = 0;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 요청전달데이터 얻기
		String star = req.getParameter("star");
		
		// 계산
		totalScore += Integer.parseInt(star);
		totalCnt++;
		
		float avgScore = (float)totalScore/totalCnt;
		
		// 요청 속성설정, request에 값을 저장
		req.setAttribute("totalScore", totalScore); // 총점
		req.setAttribute("totalCnt", totalCnt); // 총참여자수
		req.setAttribute("avgScore", avgScore); // 평점
		
		// jsp(View)로 이동
		RequestDispatcher rd = req.getRequestDispatcher("scoreresult.jsp");
		rd.forward(req, res);
		
	} // doGet

} // end class
