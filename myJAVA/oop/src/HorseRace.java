import java.awt.Canvas;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;

class Horse extends Canvas implements Runnable {
	private String name;
	private int x;			
	private int y;
	Horse(String name){
		this.name = name;
		x = 50;
		y = 10;
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawString(name, x, y);
		
	} // paint()
	
	@Override
	public void run() {
		
		for(int i=0; i < 20; i++) {
			System.out.println(name);
			this.setX(this.getX()+10);
			
			this.repaint();
			
			long millis = (long)(Math.random() * 500 + 1); // 1.0 <= r < 1001.0
			
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // for

	} // run()
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	
	
}
public class HorseRace {
	private JFrame f;					// 프레임
	private JButton btReady, btStart;	// 버튼
	private Horse[] horses;				// 
	public HorseRace(){
		f = new JFrame("달려");
		f.setLayout(new GridLayout(4,1));
		Container c = f.getContentPane();	// 프레임뒷판 -> 화면에 보일 준비
		horses = new Horse[3];
		for(int i=0; i<horses.length; i++) {
			horses[i]= new Horse((i+1)+"번 셍나");
			c.add(horses[i]);	// 추가된(add) Horse의 paint()메서드가 자동 호출됨
		}
		Panel p = new Panel();
		btReady = new JButton("준비");
		btStart = new JButton("달려");
		p.add(btReady);p.add(btStart);
		c.add(p);
		
		btReady.addActionListener((e)-> {
			for(Horse h : horses) {
				h.setX(0);
				h.repaint();
			} // for
		});
		
		btStart.addActionListener((e)-> {
			for(Horse h : horses) {
				// 이렇게하면 말들이 항상 똑같이 달리기 때문에 1번 말이 항상 1등하게 됨.. 재미없음
//				for(int i=0; i < 20; i++) {
//					h.setX(h.getX()+10);
//					h.repaint();
//				} // Inner for
				
				// Thread를 이용해서 말들이 다툼을 하도록 구현
				Thread t = new Thread(h);
				t.start();
				
			} // for
		}); 
		
		
		f.setSize(500, 200);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new HorseRace();
	}

}
