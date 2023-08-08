import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileCopy1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("원본파일명: ");
		String originalFileName = sc.nextLine();
		
		System.out.println("복제본파일명: ");
		String copyFileName = sc.nextLine();

		FileInputStream fis = null;
		FileOutputStream fos = null;
		

		try {
			fis = new FileInputStream(originalFileName);
		} catch (FileNotFoundException e) {
			System.out.println("원본파일이 없습니다");
//			e.printStackTrace();
			return;
		} // try-catch

		try {
			fos = new FileOutputStream(copyFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} // try-catch
		
//		int readValue;
		// read메서드의 반환값으로 쓰이니 -1로 초기화 안해도 상관없다
	
		int readCnt; // 읽어온 바이트 수
		byte[] bArr = new byte[1024];
		
		try {
//			while( (readValue = fis.read()) != -1 ) {
//				fos.write(readValue);
			while( (readCnt = fis.read(bArr)) != -1) {
				fos.write(bArr, 0, readCnt);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			} // try-catch
			
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally
		
	} // main

} //end class
