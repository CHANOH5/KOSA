class ParentConstructor {
	ParentConstructor() {
		System.out.println("ParentConscturcotr()생성자가 호출됨");
	}
	ParentConstructor(String name) {
		System.out.println("ParentConscturcotr("+ name +")생성자가 호출됨");
	}
}

class ChildConstructor extends ParentConstructor {
	ChildConstructor() {
		System.out.println("ChildConstructor()생성자가 호출됨");
	}
	ChildConstructor(int age) {
		super("오찬석"); // ParentConscturcotr(오찬석)생성자가 호출됨
		System.out.println("ChildConstructor("+ age +")생성자가 호출됨");
	}
}

public class InheritanceConstructor {

	public static void main(String[] args) {
		
		ParentConstructor p = new ParentConstructor();
		
		// 하위 클래스의 생성자 호출시 상위 클래스의 생성자 자동 호출
		ChildConstructor c = new ChildConstructor(); 	// ParentConscturcotr
														// ChildConstructor()생성자가 호출됨
	
		ChildConstructor c1 = new ChildConstructor(10); // ParentConscturcotr()생성자가 호출됨
														// ChildConstructor(10)생성자가 호출됨
	
		
	
	} // main

} // end class
