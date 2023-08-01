abstract class Shape {  // 컴파일시 class Shape extends Object로 바뀜
	
	// 필드
	protected double area;
	protected String st;

	// 생성자
	
	// 메서드
	public double getArea() {
		return area;
	}
	abstract void makeArea();  //하위 클래스에서 반드시 재정의 되어야함/.. {}xx
		
	public String toString() {
		st = "도형의 면적은" + area + "입니다";
		return st;
	}
}
class Triangle extends Shape {
	void makeArea() {			// abstract 메서드도 다연히 메서드이름,타입,매개변수가 똑같아야 한다
		
	}
}

class Circle extends Shape {
	
	private int radius;

	// 생성자
	public Circle() {};
	
	public  Circle(int radius) {
		this.radius = radius;
	}
//	public Circle(double area) {
//		this.Area = area;
//	}
	
	// 메서드
	public int getRadius() {
		return radius;
	}
	
	public void makeArea() {
		area = 3.14 * radius * radius;
	}
	
	public String toString() {
		return "반지름이" + radius + "인 도형의 면적은" + area + "입니다";
	}
	
	
	
} // Circle

class Rectangle extends Shape {

	private int width;
	private int height;
	
	
	//생성자
	public Rectangle() {
		
	}
	
	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	//메서드
	
	public void makeArea() {
		area = width * height;
	}
	
	public String toString() {
		return "가로" + width + ",세로" + height + "인 사각형 " + super.toString();
	}
	
} // Rectangle


public class ShapeTest {	// public class ShapeTest extends Object{}

	public static void main(String[] args) {
		
//		Circle c = new Circle(5);			// 반지름이 5인 원객체
//		System.out.println(c.getRadius());	// 5
//		
//		c.makeArea(); 	// 원의 면적을 계산한다.
//		System.out.println(c.getArea());
//		
//		Rectangle r = new Rectangle(3, 4); // 가로3, 세로4인 사각형 객체
//		r.makeArea();	// 사각형의 면적을 계산한다
//		System.out.println(r.getArea()); // 면적값 출력 12.0
		
		Shape[] shapes = new Shape[5];
		
		//upcasting
		shapes[0] = new Circle(5); // 반지름이 5인인 객체를 0인덱스에 대입
		shapes[1] = new Rectangle(3, 4);
		
		for(int i = 0; i < 2; i++) {
			// ========= 1 ============
			// shapes[i].makeArea(); -> 못쓰니까 자식타입으로 강제 형변환 하자
//			if(shapes[i] instanceof Circle) {
//				Circle c = (Circle)shapes[i]; // 부모타입으로 upcasting을 한것을 downcasting한 것
//				c.makeArea();
//				
//				System.out.println(c.getArea());
//			} else if(shapes[i] instanceof Rectangle) {
//				Rectangle r = (Rectangle)shapes[i];
//				r.makeArea();
//				
//				System.out.println(r.getArea());
//			} // if-else
			
			// ========= 2 =============
			shapes[i].makeArea();
			System.out.println(shapes[i].getArea());
			
			System.out.println(shapes[i].toString());
		
		} // for
	
	} // main

} // end class
