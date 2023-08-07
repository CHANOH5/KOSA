import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileCopy {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("복사할 파일명을 입력하세요.");
		String originalFile = sc.nextLine();
		
		System.out.println("붙여넣기할 파일명을 입력하세요.");
		String File = sc.nextLine();
		
		File original = new File(originalFile);
		File newFile = new File(File);
		
		FileInputStream is = null;
		FileOutputStream os = null;
		
		try {
			
			is = new FileInputStream(original);
			os = new FileOutputStream(newFile);
			
			// 한번에 read하고 write할 사이즈
			byte[] bt = new byte[1024];
			
			int cnt;
			
			while((cnt = is.read(bt)) != -1) {
				os.write(bt, 0, cnt);
			} // while
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			} //Inner try-catch
		} // try-catch-finally
		
	} // main

} // end class
