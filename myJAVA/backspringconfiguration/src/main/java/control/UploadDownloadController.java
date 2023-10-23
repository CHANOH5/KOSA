//package control;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URLEncoder;
//import java.nio.file.Files;
//import java.util.List;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import net.coobird.thumbnailator.Thumbnailator;
//
//
//@RestController
//public class UploadDownloadController {
//
//	@PostMapping("/upload")
//	public String upload(MultipartFile f1, List<MultipartFile> f2) throws IOException {
//		
//		// 스프링에서 제공하는 MultipartFile 라이브러리 사용
//		System.out.println(f1.getOriginalFilename() + " : " + f1.getSize());
//		
//		if(f1 != null && f1.getSize() > 0) {
//			// 디렉토리명, 파일이름을 인자로 줌
//			File targetFile = new File("C:\\kosa202307\\attaches", f1.getOriginalFilename());
//			
//			// 스프링에서 제공하는 라이브러리 사용 -> 원본 파일을 복사 붙여넣기
//			// 첫번째 인자가 f1파일의 바이트타입으로 원본으로 삼아서
//			FileCopyUtils.copy(f1.getBytes(), targetFile);
//			
//			//----섬네일파일 만들기 START----
//			int width=100;
//			int height=100;				
//
//			String thumbFileName = "t_" + f1.getOriginalFilename(); //섬네일파일명
//			File thumbFile = new File("C:\\kosa202307\\attaches" , thumbFileName);
//			FileOutputStream thumbnailOS = new FileOutputStream(thumbFile);//출력스트림
//			InputStream thumbnailIS = f1.getInputStream(); //첨부파일 입력스트림				
//			Thumbnailator.createThumbnail(thumbnailIS, thumbnailOS, width, height);
//			//-----섬네일파일 만들기 END------
//			
//			for(MultipartFile mf : f2) {
//				if(mf != null && mf.getSize() > 0) {
//					File targetFile2 = new File("C:\\kosa202307\\attaches", mf.getOriginalFilename());
//					FileCopyUtils.copy(mf.getBytes(), targetFile2);
//				} // if
//			} // enhanced-for 
//			
//			return "upload OK~ o_od";
//			
//		} else {
//			return "upload fail";
//		} // if-else
//
//	} // upload()
//	
//	
//	@GetMapping("/download")
//	// ?의 뜻은 모든 자료형
//	public ResponseEntity<?> download(String id, String opt) throws IOException {
////		String existFileName = "C:\\KOSA202307\\attaches\\t_ham08.png";
//		File dir = new File(attachesDir);
//		
//		if(opt.equals("profile")) {
//			opt+="_t";
//		}
//		
//		String fileName = id + "_" + opt + "_";
//		
//		for(File file : dir.listFiles()) {
//			String existFileName = file.getName();
//			if(existFileName.startsWith(fileName)) {
//				
//				HttpStatus status = HttpStatus.OK;
//				
//				HttpHeaders headers = new HttpHeaders();
//				// 헤더 설정
//				headers.add(
//						HttpHeaders.CONTENT_DISPOSITION, 
//						"attachment;filename = " + URLEncoder.encode(existFileName, "UTF-8")
//						);
//				
//				File file = new File(existFileName);	
//				
//				String contentType = Files.probeContentType(file.toPath()); // 파일의 형식
//				headers.add(HttpHeaders.CONTENT_TYPE, contentType); // 응답 형식
//				headers.add(HttpHeaders.CONTENT_LENGTH, ""+file.length()); // 응답길이
//				
//				byte[] bArr = FileCopyUtils.copyToByteArray(file); // file 내용을 byte 배열형태로 저장
//				
//				ResponseEntity entity = new ResponseEntity(bArr, headers, status);
//				
//				return entity;
//			}
//		}
//		
//	
//		
//		
//
//	} // download()
//	
//	
//} // end class
