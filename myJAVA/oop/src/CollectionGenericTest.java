import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class CollectionGenericTest {

	// 테스트 메서드 생성
	public static void test(Collection c) {
		c.add("one"); // String타입이지만 부모타입인 Object타입으로 관리됨
//		c.add(new Integer(2));
		c.add(Integer.valueOf(2));
		c.add(Float.valueOf(3.0F));
		c.add("one");
		c.add(Boolean.valueOf(false));
		
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
	
	public static void main(String[] args) {
		
		Collection c; // 인터페이스는 추상메서드를 갖고있기 때문에 new 키워드로 생성 불가능
		
		System.out.println("---------- ArrayList -----------");
		
		c = new ArrayList();
		
		test(c);
		
		System.out.println("---------- HashSet -----------");
		test(new HashSet()); // set의 특징 1. 중복x 2. 순차저장x

	} // main

} // end class
