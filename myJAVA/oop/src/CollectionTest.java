import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.my.customer.dto.Customer;
import com.my.product.dto.Product;

public class CollectionTest {

	// 테스트 메서드 생성
	public static void test(Collection<String> c) {
		c.add("one"); // String타입이지만 부모타입인 Object타입으로 관리됨
//		c.add(new Integer(2));
		c.add("two");
		c.add("four");
		c.add("one");
		c.add("five");

		
		System.out.println("c 요소 갯수 : " + c.size());
		System.out.println("c : " + c ); // c.toString() 자동 호출
		
		c.remove("one");
		System.out.println("one객체 삭제됨 - one 객체 중 먼저 입력된 요소 삭제");
		
		System.out.println("----------- Iterator -----------");
		
//		Iterator it = c.iterator();
//		while(it.hasNext()) {// 방문할 요소가 있는가
//			Object e = it.next(); // 요소 방문			
//			System.out.println("저장된 요소 : " + e);
//		}
		for(Object e : c) {
			System.out.println("저장된 요소 : " + e);
		}
		
	} // test()
	
	public static void test(Map m) {
		m.put("one", new Date());
		m.put("two", new String("second"));
		m.put("one", Integer.valueOf(3));
		m.put("four", Float.valueOf(4.5F));
		m.put("five", Boolean.valueOf(false));
		m.put(new Product("C0001", "아메리카노,", 1000), "커피1");
		m.put(new Product("C0001", "라떼는 말이야,", 2000), "커피2");
		m.put(new Customer("id1", "p1"), "고객1");
		m.put(new Customer("id1", "p2"), "고객2");
		
		
		System.out.println("요소 갯수: " + m.size());
		System.out.println(m);
		
		Product p = new Product();
		p.setProdNo("C0001", null, 0);
		System.out.println("C0001 상품키의 값은 " + m.get(p)); //커피2

		
	}
	
	public static void main(String[] args) {
		
		Collection<String> c; // 인터페이스는 추상메서드를 갖고있기 때문에 new 키워드로 생성 불가능
								// 변수 c에는 String값만 들어갈 수 있음
		System.out.println("---------- ArrayList -----------");
		
		c = new ArrayList<>();
		
		test(c);
		
		System.out.println("---------- HashSet -----------");
		test(new HashSet<>()); // set의 특징 1. 중복x 2. 순차저장x
		
		System.out.println("---------- HashMap -----------");
		test(new HashMap());

	} // main

} // end class
