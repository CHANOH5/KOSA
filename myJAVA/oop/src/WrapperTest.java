
public class WrapperTest {

	public static void main(String[] args) {
		
		int i = 100;
		Object obj;

		obj = Integer.valueOf(i);	// 기본형 -> 참조형 -> Obejct타입으로 upcasting
		// Boxing : 기본형 -> 참조형 부분까지가 Boxing
		
		obj = i;	// 기본형을 참조형으로 자동형변환 못하는데 어떻게 된거임..?
					// AutoBoxing된 것이다
					// 컴파일시에 Integer.valueOf(i);로 코드가 바뀜

											// // downCasting
//		int j = ((Integer)obj).intValue();	// 참조형 -> 기본형으로 UnBoxing -> .intValue()
											
		int j = (Integer)obj; // downCasting만 해놓으면 자동으로 AutoUnboixng됨
		
	} // main

} // end class
