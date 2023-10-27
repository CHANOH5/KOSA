package control;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class) //스프링용 단위테스트
@ContextConfiguration(classes = {config.MyApplicationContext.class,
								 com.my.board.config.MyMVCContext.class})
@WebAppConfiguration // WebApplicationContext 생성
class CustomerControllerTest {

	@Autowired
	WebApplicationContext ctx;
	
	MockMvc mockMvc; // 모의(가짜)객체
	
	//mockMvc 테스트
	@Test
	void test() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		assertNotNull(mockMvc);
	} // test
	
//	@Autowired
//	CustomerController controller;
	
	@Test
	@DisplayName("아이디 중복 확인")
	void testIddupchk() throws Exception {
		
		String id = "B";
		
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		String url ="/iddupchk?id=" + id;
		
		MockHttpServletRequestBuilder msrb;
		msrb = MockMvcRequestBuilders.get(url); //요청
		ResultActions actions = mockMvc.perform(msrb); //응답
		actions.andExpect(MockMvcResultMatchers.status().isOk());//응답상태코드가 200으로 예상

	} // testIddupchk

}
