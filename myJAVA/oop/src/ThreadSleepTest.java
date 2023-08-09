import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.SimpleAttributeSet;

public class ThreadSleepTest {

	public static void main(String[] args) {
	
		System.out.println(Thread.currentThread().getName());
		SimpleDateFormat sdf = new SimpleDateFormat("a HH:mm:ss");

		new Thread(()-> {
			for(int i=0; i<10; i++) {
				Date dt = new Date();
				System.out.println(sdf.format(dt));
				
				try {
					Thread.sleep(1000); // 1초 잠시 중지 됐다가 다시 객체 생성해서 출력
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			} // for
		}).start();
		
	} // main

} // end class
