package control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.product.dto.Product;

@Controller
public class TestJsonController {

	@GetMapping(value = "/h", produces ="text/html;charset=UTF-8")
	@ResponseBody
	public String h() {
		return "응답내용입니다";
	} // h()

	@GetMapping(value = "/i", produces ="application/json;charset=UTF-8")
	@ResponseBody
	public String i() {
		String jsonStr = "{";
		jsonStr += "\"status\": 0";
		jsonStr += "}";
		
		return jsonStr;
	} // i()
	
	@GetMapping(value = "/j", produces ="application/json;charset=UTF-8")
	@ResponseBody
	public Map j() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", 1);
		map.put("msg", "JSON");
		
		return map;
	} // j()
	
	@GetMapping(value = "/k")
	@ResponseBody
	public Product k() {
		Product product = new Product();
		product.setProdNo("C0001");
		product.setProdName("아메~");
	
		return product;
	} // k()
	
	
	@GetMapping(value = "/m",  produces ="text/html;charset=UTF-8") 
	@ResponseBody
	public ResponseEntity<String> m() {
		String body = "응답내용입니다";
		HttpStatus status = HttpStatus.NOT_FOUND;
		ResponseEntity<String> entity = new ResponseEntity<>(body, status);
		
		return entity;
		
		// m url을 요청했을 경우 강제로 응답 코드를 404로 응답하는 방법
	} // k()

} // end class
