
class Share {

	private int i;
	
	public void Push() {
		
		for(int i = 0; i<100; i++) {	
			 // i 변수값 보존을 위한 동기화! -> 이 작업 할동안은 스레드를 뺏기지 않음
			synchronized(this) { // 잠금장치
				this.notify();	// 이 공유객체를 사용하는 wait된 스레드를 깨운다
				System.out.print("Before push i = " + this.i);
				this.i++;
				System.out.println(", After push i = " + this.i);
//			System.out.println("s의 Push메서드 호출");
			}
			
		} // for
		
	} // push()
	
	public void Pop() {
		
		for(int i = 0; i<100; i++) {
			
			synchronized(this) {
				
				if(this.i == 0) {
					try {
						this.wait(); // 이 공유객체를 사용하는 현재 스레드를 일시중지한다
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.print("Before pop i = " + this.i);
				this.i--;
				System.out.println(", After pop i = " + this.i);
			}
			
		} // for
		
	} // Pop()
	
} // share

class Push extends Thread {
	
	private Share s;

	Push(Share s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		
		s.Push();
		
	} // run()
	
} // push

class Pop extends Thread {
	
	private Share s;
	
	Pop(Share s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		
		s.Pop();
		
	} // run()
	
} // pop
public class ThreadShareTest {

	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread().getName());
		
		Share s = new Share();
		Push pu = new Push(s); 
		Pop po = new Pop(s);

		pu.start();
		po.start();

		
	} // main

} // end class
