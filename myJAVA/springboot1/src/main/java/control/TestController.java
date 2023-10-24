package control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@GetMapping("/a")
	public String a() {
		
		return "a.jsp"; // viewName -> MVC방식
		
	} // a

	@GetMapping("/b")
	@ResponseBody
	public String b() {
		
		return "WELCOME"; // 응답내용
		
	} // b
	
	@GetMapping("/c")
	@ResponseBody
	public Map<String, Object> c() {
		Map<String, Object> map = new HashMap<>();
		map.put("stauts", 1);
		map.put("msg", "JSON문자열 + devtools추가");
		
		return map;
	}
	
	@GetMapping("/d")
	@ResponseBody
	// http://localhost:8881/boot1/d?id=aaa&sal=123
	public void d(String id, int sal) {
		System.out.println(id + ":" + sal);
	}
	 
} // end class
