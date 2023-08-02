import java.util.Scanner;

public class ExceptionTest {
	
	public static void m(Object obj) {	// 오버로드된 메서드
		try {
			String str = obj.toString();
			
			System.out.println("객체 정보 : " + str);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("finally");
		}
	}
	
	public static void m(int num) {
		try {
			System.out.println("99를 " + num + "로 나눈 나머지 값 = " + 99%num );			
		} catch(Exception e) {
			String msg = e.getMessage();
			System.out.println(msg);
			System.out.println("0을 입력하셨습니다");
		}
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("0이 아닌 숫자값을 입력하세요.");
		
		int num;
		
		try {
			num = sc.nextInt();
//			m(num);
//			if(num == 0) {
//				System.out.println("0을 입력하셨습니다. 다시 입력하세요");
//				
//				num = sc.nextInt();
//			}
		} catch(Exception e) {
			System.out.println("숫자값을 입력하지 않았습니다. 1로 자동 설정됩니다.");
			num = 1;
		}
		
		m(num);
		
		Object o;
		o = new Object();
		m(o);
		
		o = null;
		m(o);
		
	} // main

} // end class
