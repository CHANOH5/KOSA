
class Caption implements Runnable {
	@Override
	public void run() {
		for(int i=0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName());
			System.out.println("  Caption"+i);
		} //for
	} // run()
	
} // Caption

class Sound extends Thread {
	
	@Override
	public void run() {
		for(int i=0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName());
			System.out.println("sound"+i);
		} //for
	} // run()
	
} // Sound 

public class ThreadTest {
	
	public static void main(String[] args) {
		
		// 지금 실행중인 스레드 이름 확인! (메인 이름을 찾는것이 아님)
		System.out.println(Thread.currentThread().getName());
		
		Sound s = new Sound(); 
//		s.run(); // 스레드의 start메서드를 사용하지 않고 직접 호출하게 되면?
				 // 스레드가 작동하고 있는게 아님..!!!
		
		// caption은 스레드로부터 상속받아 쓴 클래스가 아니기 때문에 바로 Thread의 메서드를 쓸 수 없음
		// 그래서 Runnable Interface를 참조한 클래스를 Thread 객체의 인자값으로 넣어주고 객체를 생성해서 사용!
		// 재사용 성 높은 스레드일 경우 클래스이름을 만든다
//		Caption c = new Caption();
//		Thread t = new Thread();
//		
//		t.start();
		
		// 재사용성이 높은 클래스가 아니라면 익명클래스로 만든다
//		Thread t = new Thread( new Runnable() { // 인터페이스를 구현한 하위클래스
//			@Override
//			public void run() {
//				for(int i=0; i < 100; i++) {
//					System.out.println(Thread.currentThread().getName());
//					System.out.println("  Caption"+i);
//				} //for
//			} // run()
//		});
//		t.start();
		
		// 람다식 적용
		Thread t = new Thread(()->{
				for(int i=0; i < 100; i++) {
				System.out.println(Thread.currentThread().getName());
				System.out.println("  Caption"+i);
			} //for
		});
		t.start();
		
		s.start();
		
		for(int i=0; i < 100; i++) {
			System.out.println(" 동영상" + i);
		}
		
		// 이 문자열을 출력시키는 일
		System.out.println("THE END");
		
	} // main

} // end class
