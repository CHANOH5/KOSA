package com.my.product.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dto.Product;

/*
 * 파일명 : "D:\\products.txt"에 저장되도록
 * 파일구조 : #상품번호값:상품명값:상품가격값
 * 			  C0001:아메리카노:1000
 * 			  C0002:아아:1000
 * 파일을 미리 만들어놓고 각 메서드마다 멤버변수로 inputstream outputstream을 만들어 놓는것은 권장하지 않음
 * 각 메서드마다 write를 쓰고 파일 자원과의 연결을 각각 해야한다. 그리고 메서드마다 끊음
 * 
 */

public class ProductDAOFile implements ProductDAOInterface {
	
	private String fileName = "D:\\products.txt";
	
	public ProductDAOFile() {
		createFile();
	} // ProductDAOFile()
	
	private void createFile() {
		File file = new File(fileName);
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			} // try-catch
		} // if	
	} // createFile()

	@Override
	public void insert(Product product) throws AddException {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("저장할 파일경로와 파일명을 입력하세요. ex) D:\\products.txt");
		String fileName = sc.nextLine();

	    String contents =
	            product.getProdNo() + ":" + product.getProdName() + ":" + product.getProdPrice();

	    ObjectOutputStream oos = null;
	    
	    try {
	    	
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(contents);
			
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
	    
//	    FileWriter fileWriter = null;
//	    BufferedWriter bufferedWriter = null;
//
//	    try {
//	        File file = new File (fileName);
//
//	        if (!file.exists()) {	// 파일 없으면 생성
//	            file.createNewFile();
//	        } // if
//
//	        fileWriter = new FileWriter(file, true);
//	        bufferedWriter = new BufferedWriter(fileWriter); // 한글 깨짐 때문에 사용
//
//	        bufferedWriter.write(contents);
//	        bufferedWriter.newLine();	// 개행
//
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    } finally {
//	        try {
//	        	// 자원 해제
//	            if (bufferedWriter != null) {
//	                bufferedWriter.close();
//	            } // if
//	            
//	            if (fileWriter != null) {
//	                fileWriter.close();
//	            } // if
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        } // try-catch
//	    } // try-catch-finally
		
		// ================ 교수님 ===================
		
//		try {
//			selectByProdNo(product.getProdNo());
//			throw new AddException("이미 존재하는 상품임 =ㅅ=");
//		} catch (FindException e) {
////			e.printStackTrace();
//			
//			FileWriter fw = null;
//			try {
//				
//				fw = new FileWriter(fileName, true);
//				String prodStr = product.getProdNo() + ":" + product.getProdName()+":"+ product.getProdPrice() + "\n";
//				
//				fw.write(prodStr);
//				
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			} finally {
//				if(fw != null) {
//					try {
//						fw.close();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				}
//			}
//		} //try-catch
		
		
	} // insert

	@Override
	public Product selectByProdNo(String no) throws FindException {
		
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("조회할 파일의 경로와 이름을 입력하세요.");
//		String fileName = sc.nextLine();
//		
//		ObjectInputStream ois = null;
//		Object obj = null;
//		
//		try {
//			
//			ois =  new ObjectInputStream(new FileInputStream(fileName));
//			
//			int readValue;
//			
//			obj = ois.readObject();
//			System.out.println(obj);
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			if(ois != null) {
//				try {
//					ois.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				} // try-catch
//			} // if
//		} //try-catch.finally
//
//		return (Product) obj;
		
//		FileReader fr = null;
//		
//		BufferedReader bufferedReader = null;
//		
//		try {
//			
//			fr = new FileReader(fileName); // 자원과의 연결
//			int readValue = -1;
//			
//			bufferedReader =  new BufferedReader(fr);
//
//			while((readValue = fr.read()) != -1) {
//				System.out.print((char)readValue);
//			}
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if(fr != null) {
//				try {
//					if(fr != null)
//					fr.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	
//		return ;
		
		// =============== 교수님 ===============
		
		Scanner sc = null;
		
		try {
			
			sc = new Scanner(new FileInputStream(fileName));
			
			while(sc.hasNextLine()) {
				
				String line = sc.nextLine(); // 파일의 내용을 줄단위로 읽어오기
				String[] arr = line.split(":");
				String prodNo = arr[0];
				
				if(prodNo.equals(no)) {
					return new Product(prodNo, arr[1], Integer.parseInt(arr[2]));
				} // Inner if
			} // while
			throw new FindException("번호에 해당하는 상품이 없습니다");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			createFile();
			throw new FindException("번호에 해당하는 상품이 없습니다");
		} finally {
			if(sc != null) {
				sc.close();
			} // Inner if
		} // try-catch-finally
		
//		return null;
		
	} // selectByProdNo

	@Override
	public Object selectAll() throws FindException {
		
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("조회할 파일의 경로와 이름을 입력하세요.");
//		String fileName = sc.nextLine();
//		
//		FileReader fr = null;
//		
//		try {
//			
//			fr = new FileReader(fileName);
//			
//			int readValue = -1;
//			
//			while((readValue = fr.read()) != -1) {
//				System.out.print((char)readValue);
//			}
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if(fr != null) {
//				try {
//					fr.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				} // try-catch
//			} // if
//		} //try-catch.finally
//
//		return fr;
		
		// ==================== 교수님 ===================
		
		List<Product> all = new ArrayList<>();
		
		Scanner sc = null;
		
//		Scanner sc = new Scanner(new File(fileName));
		try {
			
			sc = new Scanner(new FileInputStream(fileName));
			
			while(sc.hasNextLine()) {
				
				String line = sc.nextLine(); // 파일의 내용을 줄단위로 읽어오기
				String[] arr = line.split(":");
				String prodNo = arr[0];
				String prodName = arr[1];
				int prodPrice = Integer.parseInt(arr[2]);
				
				Product p = new Product(prodNo, prodName, prodPrice);
				
				all.add(p);
				
			} // while
			
			if(all.size() == 0) {
				throw new FindException("상품이 하나도 없습니다.");
			}
			
			return all; // finally 하고 return 하는 것
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			createFile();
			
			throw new FindException("상품이 하나도 없습니다.");
		} finally {
			if(sc != null) {
				sc.close();
			} // if
		} // try-catch-finally
		
//		return null;
		
	} // selectAll

	@Override
	public void update(Product p) throws ModifyException {
		
		FileWriter fw = null;
		
		try {
			
			List<Product> all = (List)selectAll(); // 상품 전체 읽어오기
			
			boolean updated = false;
		
			for(Product savedP : all) {
//				if(savedP.getProdNo().equals(p.getProdNo())) {
				if(savedP.equals(p)) { // 상품 번호가 같은 (같은 메모리x)
					
					if(p.getProdName() != null) {
						savedP.setProdName(p.getProdName());
						updated = true; // 조건 만족하면 break;로 빠져나가기
					} // Inner if
					
					if(p.getProdPrice() != 0) {
						savedP.setProdPrice(p.getProdPrice());
						updated = true; // 조건 만족하면 break;로 빠져나가기
					} // Inner if
					
					break; // Inner if 둘중 하나라도 만족하면 빠져나가기
				} // if
			} // enhanced for
			
			if(updated) { // updated가 true인 경우에만 파일에 쓰기 작업
				fw = new FileWriter(fileName);
				for(Product savedP: all) {
					String pStr = savedP.getProdNo() 
							+ ":" + savedP.getProdName() 
							+ ":" + savedP.getProdPrice() 
							+"\n";
					fw.write(pStr);
				} // enhanced for
			} // if
		
		} catch (FindException e) {
			e.printStackTrace();
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
		} // finally 
		
	} // update

	@Override
	public void delete(String prodNo) throws RemoveException {

		FileWriter fw = null;
		
		try {
			
			List<Product> all = (List)selectAll();// 상품 전체를 읽어옴 -> 이 내용을 다시 쓰기하면 된다
			
			// 다 삭제하고 새로 쓰기
//			Product savedP = new Product();
//			savedP.setProdNo(prodNo);
//			all.remove(savedP);
			
			// 해당 번호 빼고 새로 쓰기
			fw = new FileWriter(fileName);
			
			for(Product p : all) {
				
				if(!p.getProdNo().equals(prodNo)) { // 상품번호가 같지 않을때만
					String pStr = p.getProdNo() 
							+ ":" + p.getProdName() 
							+ ":" + p.getProdPrice() 
							+ "\n";
					
					fw.write(pStr);
				} // if
			} // for 
			
		} catch (FindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // Inner try-catch
			} // if
		} // try-catch-finally
		
	} // delete

} // end class
