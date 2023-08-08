import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest {

	public static void main(String[] args) {
		
		File f = new File("D:\\");
		
		if(f.isDirectory()) { // 디렉토리인지? 아니면 파일인지 구분
		
//			String[] list = f.list(); // 디렉토리가 갖고있는 하위 폴더,파일명만 변수에 담기
//			
//			for(String sub : list) {
//				System.out.println(sub);
//			} // enhanced for
			
			File[] files = f.listFiles(); 
			// 디렉토리가 갖고있는 하위 디렉토리와 하위 파일들의 정보를 담기
			
			for(File file : files) {
				
				String name = file.getName();
				
				if(file.isFile()) { // d:\\의 하위파일인 경우 파일의 크기 확인
					long fileSize = file.length(); // 파일 크기 확인
					long lastModifiedTime = file.lastModified(); // 파일의 사용한 최종 사용시간 확인
				
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
					
					String formatStr = sdf.format(new Date(lastModifiedTime));
					
					System.out.println(name + "\t" + formatStr +"\t" + fileSize);
				} else { // d:\\의 하위폴더
					System.out.println("["+ name +"]");
				} // if-else
				
			} // enhanced for
			
		} // if
		
		String folder = "D:\\attache";
		File file = new File(folder);
		if(!file.exists()) { // 디렉토리 있는지 확인 == if(file.exists() == false)
		
			boolean result = file.mkdir(); // 생성
			
			if(result == true ) {
				System.out.println( folder + "폴더가 생성되었습니다");
			} else {
				System.out.println(folder + "폴더가 생성되지 않았습니다");
			} 
		} else {
			System.out.println(folder + "폴더가 이미 존재합니다");
		}
		
		String fileName = "a.txt";
		File file1 = new File(file, fileName);
		
		try {
			
			if(!file1.exists()) {
				file1.createNewFile();
				System.out.println(fileName + "파일이 생성되었습니다.");				
			} else {
				System.out.println(fileName + "파일이 이미 존재합니다.");				
			} // if-else
			
		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch
		
	} // main


} // end class
