package com.my.board.control;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.board.dto.Board;

@RestController
// validate 어노테이션을 썼다면 클래스 앞에 @Validated 어노테이션 선언해주자
@Validated 
public class ValidTestController {
	
	// 요청전달데이터 유효성 검사할때는 @Validated어노테이션 상단에 써주어야 함
	@GetMapping(value = "/a", produces = "text/html;charset=utf-8")
	public String a(String no, int sal) {
		
		if( no == null ) { 
			return "no값을 반드시 입력하세요";
		} else if (no.length() < 3) {
			return "no는 3자리 이상으로 입력하세요";
		}
		
		return "성공";
		
	} // a
	
	@GetMapping(value = "/b", produces = "text/html;charset=utf-8")
	public String b(@NotNull(message="no값을 반드시 입력하세요")
					@Size(min = 3, message="no는 3자리 이상으로 입력하세요") String no,
			  		int sal) {

		return "성공";
	} // b
	
	@GetMapping(value = "/c", produces = "text/html;charset=utf-8")
	// 요청전달데이터들이 command객체의 값으로 자동 세팅이됨(handlerAdapter가 수행)
	public String c(@Validated Board b) {
		
		return "성공";
	} // c
	
	// command객체의 유효성 검사할때는 상단에 @Validated 필요없음
	@GetMapping(value = "/d", produces = "text/html;charset=utf-8")
	// 요청전달데이터들이 command객체의 값으로 자동 세팅이됨(handlerAdapter가 수행)
	public String d(@Validated @RequestBody Board b) {
		
		return "성공";
	} // c
	
	
	// pathvariable 어노테이션 유효성검사
	@GetMapping("value=/e/{no}")
	public String e(@Size(min = 3, message="no는 3자리 이상으로 입력하세요")
					String no) {
		return "성공";
	}

} // end class
