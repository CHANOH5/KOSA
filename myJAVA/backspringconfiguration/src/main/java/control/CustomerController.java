package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.customer.dto.Customer;
import com.my.customer.service.CustomerService;
import com.my.exception.FindException;
import com.my.util.Attach;

import net.coobird.thumbnailator.Thumbnailator;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;

	@PostMapping("/signup")
	public Map<String, Object> signup(String id, String pwd, String name,
			HttpServletRequest req, HttpServletResponse res,
			MultipartFile f1, List<MultipartFile> f2) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		
		try {

			Attach attach = new Attach(req);
			Customer c = new Customer(id, pwd, name, null);
			
			service.signup(c);	
			
			System.out.println("f1 ==============> " + f1);
			
			if(f1 != null && f1.getSize() > 0) {
			
		
				File targetFile = new File("C:\\KOSA202307\\attaches", id + "_profile_" + f1.getOriginalFilename());
				FileCopyUtils.copy(f1.getBytes(), targetFile);

				
				//----섬네일파일 만들기 START----
				int width=100;
				int height=100;				

				String thumbFileName = id + "_t_" + f1.getOriginalFilename(); //섬네일파일명
				File thumbFile = new File("C:\\kosa202307\\attaches" , thumbFileName);
				FileOutputStream thumbnailOS = new FileOutputStream(thumbFile);//출력스트림
				InputStream thumbnailIS = f1.getInputStream(); //첨부파일 입력스트림				
				Thumbnailator.createThumbnail(thumbnailIS, thumbnailOS, width, height);

				for (MultipartFile mf : f2) {
				    if (mf != null && mf.getSize() > 0) {
					File targetFile2 = new File("C:\\KOSA202307\\attaches", id + "_intro_ "  + mf.getOriginalFilename());
					FileCopyUtils.copy(mf.getBytes(), targetFile2);
				    } else {
				    	throw new Exception("자소서 파일이 없습니다");
				    }
				}
			}

			map.put("status", 1);
			map.put("msg", "가입성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "가입실패");
		} // try-catch
		
		return map;
		
	} // signup
	
	@PostMapping("/login")
	public Map<String, Object> login(String id, String pwd, HttpSession session) {
		
		Map<String, Object> map = new HashMap<>();
		session.removeAttribute("loginedId");
		
		try {
			
			service.login(id, pwd);
			map.put("status", 1);
			map.put("msg", "로그인 성공");
			// json형태로 응답하기로 했으니 -> jackson라이브러리 쓰기
			
			// 로그인되면 loginedId값을 id로 세팅할거임
			session.setAttribute("loginedId", id);
			
			System.out.println(session);
			
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "로그인 실패");
		}
		
		return map;
		
	} // login
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		System.out.println("지우기 전:" + session.getId());
		session.removeAttribute("loginedId");
		session.invalidate();
		System.out.println("지운 후:" + session.getId());
		
		return null;
	} // logout
	
	@GetMapping("/iddupchk")
	public Map<String, Object> iddupchk(String id) {

		Map<String, Object> map = new HashMap<>();

		try {
			service.idDupChk(id);
			// 고객이 있는 경우
			map.put("status", 0);
		} catch (FindException e) {
			// 고객이 없는 경우
			map.put("status", 1);
		} // try-catch
		
		return map;
		
	} // iddupchk
	
	// 다운로드
    @GetMapping("/download")
    public ResponseEntity<?> download(@RequestParam(name = "id") String id, @RequestParam(name = "opt") String opt
	   ) throws IOException {
	
	String attachesDir = "C:\\KOSA202307\\attaches";
	 String fileName = "";
	 
	if(opt.equals("profile")) {
		
	    fileName = id + "_t_";
	    
	}else if(opt.equals("intro")){
		
	    fileName = id + "_intro_";
	    
	}
	
	File dir = new File(attachesDir);
	
	for (File file : dir.listFiles()) {
		
	    String existFileName = file.getName();	    
	    if (existFileName.startsWith(fileName)) {
		System.out.println(existFileName + "파일입니다. 파일크기:" + file.length());
		HttpStatus status = HttpStatus.OK;

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION,
			"attachment;filename = " + URLEncoder.encode(existFileName, "UTF-8"));

		
		String contentType = Files.probeContentType(file.toPath());
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		headers.add(HttpHeaders.CONTENT_LENGTH, "" + file.length());

		byte[] bArr = FileCopyUtils.copyToByteArray(file);
		ResponseEntity<?> entity = new ResponseEntity<>(bArr, headers, status);
		return entity;
	    }
	}
	
	System.out.println(id + "의 프로필 파일이 없습니다");
	
	HttpStatus status = HttpStatus.NOT_FOUND;
	
	ResponseEntity<?> entity = new ResponseEntity<>(id + "의 프로필 파일이 없습니다", status);
	return entity;
    }
	
} // end class
