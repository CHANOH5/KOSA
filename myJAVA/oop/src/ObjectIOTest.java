import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.my.customer.dto.Customer;

public class ObjectIOTest {

	public static void write() {
		/*
		 *  스트림 : 바이트단위 출력스트림
		 *  필터스트림 : 객체단위 출력스트림
		 *  목적지 : 파일
		 */
		String fileName = "a.ser";
		
		ObjectOutputStream oos = null;
		
		try {
			
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(new Date()); // 날짜 타입의 객체 -> Serializable 인터페이스가 자동으로 구현된 클래스
			
			Customer c = new Customer("찬돌아이디", "찬돌비밀번호", "찬돌", "찬돌주소");
			oos.writeObject(c);  // 고객 객체 생성 -> Serializable 인터페이스가 x -> 구현해야한다
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // try-catch
			} // if 
		} // try-catch-finally
		
	} // write();
	
	public static void read() {
		
		String fileName = "a.ser";
		
		ObjectInputStream ois = null;
		
		try {
			
			// ois = new ObjectInputStream(fileName); // 이렇게 자원과 직접 만날 수 없다 
			ois = new ObjectInputStream(new FileInputStream(fileName));
			
			Object obj1 = ois.readObject();
			System.out.println(obj1.toString());
			
			// 두번째 객체인 Customer 객체 보기
			Object obj2 = ois.readObject();
			System.out.println(obj2); // obj2.toString() 자동호출됨
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // try-catch
			} // if
		} // try-catch-finally
		
	} // read()
	
	public static void main(String[] args) {
	
		write();
		
		read();

	} // main

} // end class
