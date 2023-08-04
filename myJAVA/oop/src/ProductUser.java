import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dao.ProductDAOInterface;
import com.my.product.dto.Product;

public class ProductUser {
	
	Scanner sc = new Scanner(System.in);
	
//	ProductDAOArray dao = new ProductDAOArray();
	
//	ProductDAOInterface dao = new ProductDAOList();
//	ProductDAOInterface dao = new ProductDAOArray();
	ProductDAOInterface dao;
	ProductUser(){
//		dao = new ProductDAOArray();
		// 유저가 dao를 어떤것을 쓸지 정하도록 하지 않고
		// properties파일을 가져와서 properties파일에 저장된 dao를 쓰도록 할것임!
		
		Properties env = new Properties();
		
		try {
			// productUser.class 파일이 있는 곳에서 my.properties를 찾아서 읽어와라!
			env.load(ProductUser.class.getResourceAsStream("my.properties"));
			// product.dao라는 key를 가져와라 
			String className = env.getProperty("product.dao"); 
			// 객체 생성
			Class clazz = Class.forName(className);
//			clazz.newInstance();
			Object obj = clazz.getDeclaredConstructor().newInstance();
			
			dao = (ProductDAOInterface)obj;
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	void findAll() {
		System.out.println(">>상품 전체목록<<");
		try {
			//Product[] all1 = dao.selectAll();
			Object obj = dao.selectAll();
			if(obj instanceof Product[]) {
				Product[] all1 = (Product[])obj;				
				for(int i = 0; i< all1.length; i++){
					Product p = all1[i];
					//System.out.println(p.prodNo + ":" + p.prodName + ":" + p.prodPrice);
					System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
				}
			}else if(obj instanceof List) {
				List<Product> list = (List)obj;
				for(int i=0; i<list.size(); i++) {
					Product p = list.get(i);
					System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
				}
			}

		}catch(FindException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("----------------");
	}
		
//		if(all1 == null) {
//			System.out.println("상품이 없습니다"); //출력됨
//		}else {
//			for(Product p: all1) {
//				System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
//			}
//		}
//		System.out.println("----------------");
		
//	}
	
	public void findByProdNo() throws FindException {

		System.out.println(">>상품 번호로 검색<<");
		System.out.println("상품번호를 입력하세요.");
		String prodNo = sc.nextLine();  // 밑에는 user.sc.nextLine이라고하고 여긴 sc.nextLine이라고 생략하는건가
										// 여기는 this.sc.nextLine이 생략 된것
										// 객체의 밖에서 sc를 user라는 참조 변수로 찾아가야하기 때문에 user.이 붙음
		Product p;
		p = dao.selectByProdNo(prodNo);
		if(p == null) {
			System.out.println("상품이 없습니다"); 
		}else{
			//출력됨
			System.out.println(p.getProdNo() +"번호 상품의 상품명:" + p.getProdName() + ", 가격:" + p.getProdPrice());
		}
		
	}
	
	public void add(){
		
		System.out.println(">>상품 추가<<");
//		Product p1 = new Product();

		System.out.println("상품번호를 입력하세요:");
		String prodNo = sc.nextLine();

		System.out.println("상품명을 입력하세요:");
		String prodName = sc.nextLine();

		System.out.println("상품가격을 입력하세요:");
		String prodPrice = sc.nextLine();

		
//		p1.prodNo = prodNo;
//		p1.prodName = prodName;
////		p1.prodPrice = prodPrice; // p1.prodPrice : int타입, prodPrice : String 타입
//								  // String -> int : Integer.parseInt(String);
//		p1.prodPrice = Integer.parseInt(prodPrice);
	
		Product p = new Product(prodNo, prodName, Integer.parseInt(prodPrice));
		
		try {
			dao.insert(p);
		} catch (AddException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
	} // add
	
	public void update() throws ModifyException {
		
		System.out.println("수정할 상품의 번호를 입력하세요");
		String prodNo = sc.nextLine();
		
		if ( prodNo == null ) { // prodNo가 저장된 상품과 같지 않다면..으로 하고싶은데..
			System.out.println("수정할 상품이 없습니다.");
		} else {
			System.out.println("상품의 이름을 입력하세요");
			String prodName = sc.nextLine();

			System.out.println("상품의 가격을 입력하세요 ");
			String prodPrice = sc.nextLine();
			
			Product p = new Product(prodNo, prodName, Integer.parseInt(prodPrice));
			
			dao.update(p);
		}
		
	}
	
	public void delete() throws RemoveException {
		
		System.out.println("삭제할 상품의 상품번호를 입력하세요.");
		String prodNo = sc.nextLine();
	
		dao.delete(prodNo);
	}
	
	
	public static void main(String[] args) throws AddException, FindException, ModifyException, RemoveException {	
		
		ProductUser user = new ProductUser(); // non-static 필드로 선언된 scanner를 사용하기위해 객체 먼저 생성
		
		while(true) {
		
			System.out.println("작업을 선택하세요 : 상품전체목록-1, 상품번호로검색-2, 상품추가-3, 상품수정-4, 상품삭제-5 종료-9");
			String opt = user.sc.nextLine(); 
			
			if(opt.equals("1")) {
				user.findAll();
			} else if(opt.equals("2")) {
				user.findByProdNo();
			} else if(opt.equals("3")) {
				user.add();
			} else if(opt.equals("4")) {
				user.update();
			} else if(opt.equals("5")) {
				user.delete();
			} else if(opt.equals("9")) {
				break;
			} // if-else
		
		} // while
		
	} // main
	
} // end class
