import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOTest {

	public static void read() {

		/*
		 * 스트림 종류: 바이트단위 입력스트림
		 * 자원 : 파일
		 * 
		 */
		String fileName = "D:\\a.txt.txt"; // D드라이브에 있는 a.txt파일
		
		/*
		try {
			
			FileInputStream fis = new FileInputStream(fileName);	// 파일 자원과의 연결(객체 선언이 연결하는 것 )
			int readValue = -1;
			
			while((readValue = fis.read()) != -1) {
				System.out.print((char)readValue);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // 멀티 catch를 사용할 때 부모 Exception이 아래에 위치하도록
		 */
		
		// 문자 단위의 입력스트림
		// 자원 : 파일
		
		FileReader fr = null;
		
		try {
			
			fr = new FileReader(fileName); // 자원과의 연결
			int readValue = -1;
			
			while((readValue = fr.read()) != -1) {
				System.out.print((char)readValue);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fr != null) {
				try {
					if(fr != null)
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	public static void write() {
		/*
		 * 스트림 종류 : 바이트 단위 출력스트림
		 * 목적지 : 파일 
		 */
		
//		String fileName = "D:\\b.txt";
		
		/*
		try {
			
			FileOutputStream fos = new FileOutputStream(fileName);
//			fos.write(65);
			
			byte[] bytes = "ABCDEFG".getBytes();
			fos.write(bytes);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		/*
		 *  스트림 종류 : 문자 출력스트림
		 *  목적지 : 파일
		 */
		
		String fileName = "D:\\c.txt";
		FileWriter fw = null;
		
		try {
			
			fw = new FileWriter(fileName, true); // , true를 주면 기존 파일이 있으면 추가하고 없으면 생성
			fw.write("찬돌이는 짱이다ㅎㅎ");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // try-catch
			} // if
		} //finally
		
	} // write()
	
	public static void main(String[] args) {
		
//		read();
		
		write();
		
	} // main

} // ene class
