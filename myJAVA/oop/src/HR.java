class Employee{
	String no;
	String name;
	int salary;
	
	public void setNo(String no) {
		this.no = no;
	}
	
	public String getNo() {
		return no;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public int getSalary() {
		return salary;
	}
	
	void print() {
		
		System.out.println("사번 : " + this.no + ", 이름 : " + this.name + ", 월급 : " + this.salary);
		
	}
	
} // end class

public class HR {

	public static void main(String[] args) {
	
		Employee e1 = new Employee();
		String no = e1.no;
		System.out.println(no);
			
		e1.setNo("2301"); // 사번설정
		e1.setName("찬돌");
		e1.setSalary(1000);
		
		System.out.println("사원의 사번 : " + e1.getNo() +
							", 사원명 : " + e1.getName()+
							", 월급 : " + e1.getSalary());
		
		e1.print();
		
		Employee eTemp;
//		eTemp = null;
//		eTemp = new Employee();
		eTemp = e1;
		
		eTemp = e1;
		System.out.println(e1 == eTemp);
		eTemp.setSalary(2000);
		System.out.println(e1.getSalary());
		eTemp = null;  // 연결고리를 끊겠다!
		System.out.println(eTemp);
		System.out.println(e1.getSalary());
//		System.out.println(eTemp.getSalary()); // NullPointerException 발생 후 프로그램 종료 => THE END 메시지 출력되는지?
//		System.out.println("THE END"); // 널포인트발생해서 프로그램 종료됐기 때문에 출력안댐
		
		Employee[] employees;	// 배열 선언
		// 최대 20명이 저장될 수 있는 배열 생성
		employees = new Employee[20];
		
//		employees[0] = e1;
//		
//		Employee e2 = new Employee();
//		e2.setNo("2302");
//		e2.setName("찬돌2");
//		e2.setSalary(1000);
//		
//		employees[1] = e2;
//		
//		Employee e3 = new Employee();
//		e3.setNo("2303");
//		e3.setName("찬돌3");
//		e3.setSalary(1000);
//		
//		employees[2] = e3;
//		
//		Employee e4 = new Employee();
//		e4.setNo("2304");
//		e4.setName("찬돌4");
//		e4.setSalary(1000);
//		
//		employees[3] = e4;
		
		//반복문
		for(int i = 2; i <= 4; i++) {
			
			e1.setNo("230"+i);
			e1.setName("찬돌"+i);
			e1.setSalary(1000);

			employees[i-1] = e1;
	
			employees[i-1].print();
		}
		
	} //main
 
} // end class

