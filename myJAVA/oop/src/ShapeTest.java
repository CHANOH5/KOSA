class Shape {  // 컴파일시 class Shape extends Object로 바뀜
	// 필드
	protected double area;

	// 생성자
	
	// 메서드
	public double getArea() {
		return area;
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
	
} // Rectangle


public class ShapeTest {	// public class ShapeTest extends Object{}

	public static void main(String[] args) {
		
		Circle c = new Circle(5);			// 반지름이 5인 원객체
		System.out.println(c.getRadius());	// 5
		
		c.makeArea(); 	// 원의 면적을 계산한다.
		System.out.println(c.getArea());
		
		Rectangle r = new Rectangle(3, 4); // 가로3, 세로4인 사각형 객체
		r.makeArea();	// 사각형의 면적을 계산한다
		System.out.println(r.getArea()); // 면적값 출력 12.0

	} // main

} // end class
