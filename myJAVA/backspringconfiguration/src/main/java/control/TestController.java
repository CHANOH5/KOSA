package control;

import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.product.dto.Product;

@Controller
public class TestController {

	@GetMapping("/a")
	public ModelAndView a(@RequestParam(name = "n") String name, // 요청전달데이터명: n ex) ? n=oh
						  @RequestParam(required=false, defaultValue="0") int sal) {
		System.out.println("a() 호출됨 : " + name + ", sal : " + sal);
	
		return null;
	} // a()
	
	@GetMapping("/b")
	// /b?cb=one&cb=two&cb=three 요청전달데이터의 값이 여러개인 경우
	public ModelAndView b(String[] cb) { // 배열로 전달데이터 값을 받으면 된다
		
//		for(String e : cb) {
//			System.out.println(e);
//		} // for 
		
		Stream<String> st = Stream.of(cb);
		System.out.println(st);
		
		return null;
	} // b()
	
	@GetMapping("/c")
	public String c(Model model) {
		model.addAttribute("msg", "찬돌짱");
		String viewname = "c.jsp";
		
		return viewname;
	} // c()
	
	@GetMapping("/d")
	// d?prodNo=C0001&prodName=아메리카노&prodPrice=1000
	public ModelAndView d(Product p) {
		System.out.println(p);
		
		return null;
	} // d()
	
	@GetMapping("/e")
	public ModelAndView e() {
		ModelAndView mnv = new ModelAndView();
//		mnv.setViewName("e.jsp");
		mnv.setViewName("e");
		mnv.addObject("msg", "하이~"); // Model의 속성 설정
		
		return mnv;
	}// e()
	
	@GetMapping("/f")
	public String f() {
		String viewName = "f";
		
		return viewName;
	} // f()
	
	@GetMapping("/g")
	public void g() {
		
	}
	
} // end class
