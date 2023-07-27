
public class First {

	static int smf; // static 필드 
	int mf; 		// non-static 필드
	
	public static void main(String[] args) {
		
		int lv; 	// local variable ( 지역변수 )
//		System.out.println(lv);		// 자동초기화 x
//		System.out.println(smf ); 	// 자동초기화 o
//		System.out.println(mf );	// non-static필드는 static 메서드인 메인 메서드에서 사용x
		
		// 인스턴스화 (객체 만들기)
		First one; // 참조형 지역변수
		one = new First(); // 인스턴스화 (객체 만들기)
		System.out.println(one.mf);
		

	} // main

} // end class