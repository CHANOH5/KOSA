import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void write() {
		/*
		 * 스트림 : 바이트단위 출력스트림
		 * 필터스트림 : 데이터타입단위 출력스트림
		 * 목적지 : 파일
		 */
		
		String fileName = "a.dat";
		
		FileOutputStream fos = null;
		DataOutputStream dos = null;
				
		try {
			
			fos = new FileOutputStream(fileName);
		
			// 파일을 가공한 출력스트림인 데이터 타입별로 쓰게할 수 있는 스트림이 필요하니까
			dos = new DataOutputStream(fos);
			
			dos.writeInt(1);
			dos.writeFloat(2.3F);
			dos.writeBoolean(false);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // try-catch
			} // if
		} // try-catch-finally
		
	} // write()
	
	public static void read() {
		
		/*
		 * 스트림 : 바이트단위 입력스트림
		 * 필터스트림 : 데이터타입단위 출력스트림 (쓰기에 데이터단위 출력스트림이니까 읽기도 데이터타입단위)
		 * 자원 : 파일
		 */
		String fileName = "a.dat";
		
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			
			//읽기
			int i = dis.readInt();
			float f = dis.readFloat();
			boolean b = dis.readBoolean();
			
			System.out.println(i + ":" + f + ":" + b);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 연결 해제할때는 연결한것 거꾸로
			if(dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // try-catch
			}// if
		} // try-catch-finally
		
	} // read()
	
	public static void main(String[] args) {

		write();
	
		read();
		
	} // main

} // end class
