import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ReflectionTest {
	public static void test(String className) throws ClassNotFoundException {
		
		Class c = Class.forName(className); // 이 class가 class를 담고 있는 class이다
		
		Field[] fields = c.getDeclaredFields(); // 런타임시 클래스가 갖는 멤버변수(필드) 조회
	
		for(Field f : fields) {
			System.out.println(f.getName());
		}
		
		System.out.println("---------------");
		Method[] methods = c.getDeclaredMethods(); // 런타임 로드된 클래스의 메서드들 조회
	
		for(Method m : methods) {
			System.out.println(m.getName());
		}

		try {
			Object obj = c.getDeclaredConstructor().newInstance();
			System.out.println(obj); //obj.toString() 자동호출됨
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws ClassNotFoundException {

		Scanner sc = new Scanner(System.in);
		
		System.out.print("클래스이름을 입력하세요! ex)Java.util.Date:");
		
		String className = sc.nextLine();
		
		test(className);
		
	} // main

} // end class
