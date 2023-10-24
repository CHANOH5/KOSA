package backspringconfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.customer.dao.CustomerRepository;
import com.my.customer.dto.Customer;
import com.my.exception.FindException;

@ExtendWith(SpringExtension.class) //스프링용 단위테스트
// ApplicationContext(스프링 컨테이너) 이 클래스를 이용해서 tomcat없이 단위테스트 사용
@ContextConfiguration(classes = {config.MyApplicationContext.class}) 
class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository repository;
	
	@Test
	void test() {
		int i = 10;	// 실제 처리결과 데이터
		int expectedI = 10; // 성공 예상 데이터
	
		assertTrue( i == expectedI );
		
		String msg = "hello"; // 실제 처리결과 데이터
		String expectedMsg = "Hi"; // 성공 예상 데이터
		
		assertEquals(expectedMsg, msg);
		
	} // test

	@Test
	void testInsert() {
		
		
	} // testInsert
	
	@Test
	@DisplayName("아이디로 고객검색 테스트")
	void testSelectById() throws FindException {
		
		String id = "B";
		
		Customer c = repository.selectById(id);
		String expectedPwd = "b";
		
		String expectedName = "B";
		
		assertEquals(expectedPwd, c.getPwd());
		assertEquals(expectedName, c.getName());
		
	} // testSelectById

} // end class
