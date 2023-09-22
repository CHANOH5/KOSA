package com.my.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")  // ("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String envFileName = "control.properties";	
	private Properties env;
	
    public DispatcherServlet() {
    	super();
    } // constructor

	@Override
	public void init() throws ServletException {
		super.init();
		
		env = new Properties(); // Properties객체 생성
		//servletContext 객체 찾기
		ServletContext sc = this.getServletContext();
		String realPath = sc.getRealPath("WEB-INF\\classes\\com\\my\\env\\" + envFileName);
		System.out.println("in DispathcerServlet의 init:() realPath = " + realPath);
		// control.properties파일의 내용을 Properties객체에 넣기
		try {
			env.load(new FileInputStream(realPath));
		} catch (IOException e) {
			e.printStackTrace();
		} //try-catch
	} // init

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("req.getServletPath() = " + req.getServletPath());
		
//		if(req.getServletPath().equals("/productjson")) {
//			ProductJsonController control = new ProductJsonController();
//			control.execute(req, res);
//		} else if (req.getServletPath().equals("/productlistjson")) {
//			ProductListJsonController control = new ProductListJsonController();
//			control.execute(req, res);
//		}
		
		String className = env.getProperty(req.getServletPath());
		
		// 이 클래스 이름에 해당하는 .class파일 찾아서 jvm으로 로드
		try {
			Class clazz = Class.forName(className);
			
			Controller controller = (Controller)clazz.getDeclaredConstructor().newInstance();
//			clazz.newInstance(); // 객체 생성 작업 -> 지금 로드된 클래스 타입의 객체 생성
			controller.execute(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // service

} // end class
