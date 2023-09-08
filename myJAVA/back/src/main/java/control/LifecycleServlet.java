package control;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lifecycle")
public class LifecycleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public LifecycleServlet() {
    	
        super();
        System.out.println(">>>>>>>>>> LifecycleServlet() invoked.");
    } // LifecycleServlet

	public void init(ServletConfig config) throws ServletException {

		System.out.println(">>>>>>>>>> init() invoked.");
		
		super.init(config); // 부모에서 제공하는 init메서드를 호출
//		ServletContext sc = this.getServletContext(); // 지금 연결된 ServletContext타입의 객체를 반환해줌
//		System.out.println(sc.getRealPath("a.jpg")); // 지금 웹프로젝트의 a.jpg파일의 실제 경로
	} // init

	public void destroy() {

		System.out.println(">>>>>>>>>> destroy() invoked.");
	} // destroy

//	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		System.out.println(">>>>>>>>>> service() invoked.");
//		String idValue = req.getParameter("id");
//		String pwdValue = req.getParameter("pwd");
//		
//		System.out.println("요청 전달데이터 id = " + idValue + ", 요청 전달데이터 pwd" + pwdValue);
//				
//	} // service

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println(">>>>>>>>>> doGet() invoked.");
	} // doGet

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println(">>>>>>>>>> doPost() invoked.");
	} // doPost

} // end class
