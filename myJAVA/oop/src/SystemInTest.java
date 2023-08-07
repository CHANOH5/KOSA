import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SystemInTest {

	public static void main(String[] args) {
		
		
		InputStream is = System.in;
		
		/*
		try {
//			int readValue = is.read();
//			System.out.println(readValue);
			
			int readValue = -1;
			while( (readValue = is.read()) != -1 ) {
				System.out.println((char)readValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		// InputStream -> Reader
		
		InputStreamReader isr = new InputStreamReader(is);
		// 문자 단위로 읽기 가능해짐
		try {
			int readValue = -1;
			
			while((readValue = isr.read()) != -1 ) {
				System.out.println((char)readValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	} // main

} // end class
