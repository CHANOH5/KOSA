import lombok.extern.log4j.Log4j2;

class Copy implements Cloneable{ // Cloneable은 clone제약을 걸기 위한 인터페이스
	
	int i;
	int[] arr = {1, 2, 3};
	
	Object copy() {
		
		Object obj = null;
		
		try {
			obj = this.clone(); // 객체 복제
			// deep copy
			int[] arrCopy = (int[])arr.clone(); // 배열 복제
			Copy copy = (Copy)obj; // 배열 대입
			copy.arr = arrCopy;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return obj;
		
	} // copy
} // end class

public class CloneTest {

	public static void main(String[] args) {
		
		Copy origin = new Copy();		// 원본객체
		origin.i = 9;   
		
		origin.arr[0] = 9;

		Object obj = origin.copy();
		
		Copy copy = (Copy)obj; 			// 원래 타입인 Copy타입으로 형변환

		// shallow copy
		
		System.out.println("복제본 객체의 i = " + copy.i); //9
		System.out.println("복재본 객체의 arr[0] = " + copy.arr[0]); // 9
		
		System.out.println("== shallow copy, deep copy ==");
		
		copy.i = 7;
		copy.arr[0] = 7;
		
		System.out.println("복제본 객체의 i = " + copy.i); //9
		System.out.println("복재본 객체의 arr[0] = " + copy.arr[0]); // 9
		
		
		System.out.println("원본 객체의 i = " + origin.i); //9
		System.out.println("원본 객체의 arr[0] = " + origin.arr[0]); // 7 -> shallow copy
																	 // 9 -> deep copy
		// depp copy

	} // main
	
} // end class
