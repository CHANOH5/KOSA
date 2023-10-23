package control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		HttpStatus status = HttpStatus.NOT_FOUND; // 404 
//		HttpStatus status = HttpStatus.OK; 200 ok
//		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; 500 에러
		ResponseEntity<String> entity = new ResponseEntity<>(body, status);
		
		return entity;
		
		// m url을 요청했을 경우 강제로 응답 코드를 404로 응답하는 방법
	} // k()
	
	// 요청할때도 json형태로 요청한 데이터 형태를 @RequestBody 사용 -> dto형태로 변환
	@PostMapping("/p")
	@ResponseBody
	// ex) /p?prodNo=C0001&prodPrice=1000 요청전달데이터로 전달되게 하지 않고
	// ex) /p 로 전달, 요청시 JSON문자열형태로 요청전달데이터가 전달되면 @RequestBody로 데이터를 받아서 dto형태로 변환
	// Restful할때 사용
	public void p(@RequestBody Product data) {
		
		System.out.println(data);
		
	} // p()

} // end class
