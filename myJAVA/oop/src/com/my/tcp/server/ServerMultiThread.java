package com.my.tcp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

class ServerThread extends Thread {
	private Socket s;
	
	private InputStream is = null;
	private DataInputStream dis = null;
	
	private OutputStream oos = null;
	private DataOutputStream dos = null;
	
	// 3. ArrayList에 스레드 저장
//	private ArrayList<ServerThread> list = null;
//	private Vector<ServerThread> list;
	List<ServerThread> list = new Vector<>();
	private String clientIP;
	
	// 생성자
	ServerThread(Socket s, List<ServerThread> list) throws IOException {
		this.s = s;
		this.list = list;
		
		is = s.getInputStream();
		dis = new DataInputStream(is);
		oos = s.getOutputStream();
		dos = new DataOutputStream(oos);
		
		// 5. 소켓 입장에서 접속한 클라이언트의 정보 알아낼 수 있음
		InetAddress ip = s.getInetAddress();
		clientIP = ip.getHostAddress();
		System.out.println( clientIP + "클라이언트가 접속했습니다");
		
		broadcast(clientIP + "님이 접속했습니다");
	}
	
	// 메서드
	@Override
	public void run() {
		
		try {
			
			String receiveMsg;
			
			while( !(receiveMsg = dis.readUTF()).equals("quit") ) {				
//				System.out.println("클라이언트가 보낸 메시지: " + receiveMsg);
//				dos.writeUTF(receiveMsg);
				
				
				// 4. ArrayList에 스레드 저장
//				for(ServerThread t : list) {
//					t.dos.writeUTF(clientIP + ">" +receiveMsg);
//				}
				broadcast(clientIP + ">" + receiveMsg);
				
			} // while
			
		} catch (EOFException e) {
			
		} catch(IOException e) {
			
		} finally {
			
			list.remove(this);
			
			System.out.println("클라이언트와의 연결이 종료되었습니다. ㅇㅂㅇb");
			
			// 6. 클라이언트 접속정보 출력
//			for(ServerThread t : list) {
//				try {
//					t.dos.writeUTF(clientIP + "님이 나갔습니다");
//				} catch (IOException e) {
//		
//				} // try-catch
			
				
//			} // for
			broadcast(clientIP + "님이 나갔습니다");
			
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // try-catch
			} // if
		} // try-catch-finally
		
	} // run()
	
	private void broadcast(String msg) {
		for(ServerThread t : list) {
			try {
				t.dos.writeUTF(msg);
			} catch (IOException e) {
	
			} // try-catch
		} // for
		
	} // boradcast
	
} // ServerThread	


public class ServerMultiThread {

	public static void main(String[] args) {
		
		// 0~1024는 정해진 포트번호이기 때문에 사용하지 않는게 좋다
		int port = 5432;
		ServerSocket ss = null;
		Socket s = null;
		
//		ArrayList<ServerThread> list = new ArrayList<>(); 
		// 1. 이 리스트에 서버스레드 객체를 대입
//		Vector<ServerThread> list = new Vector<>();
		List<ServerThread>list = new Vector<>();
		
		try {
			
			ss = new ServerSocket(port);
			System.out.println("클라이언트 접속을 기다리기");
			
			while(true) {
				s = ss.accept();
				// 생성자 인자로 소켓을 전달해서 그 스레드가 시작이 되도록!! 여러개 연결이 되도록
//				new ServerThread(s).start();
				
				// 2. 리스트에 서버스레드 객체를 대입
				ServerThread t = new ServerThread(s, list);
				list.add(t);
				t.start();
				
			}

		} catch(BindException e) {
			System.out.println(port + "포트가 이미 사용중입니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} // finally 부분은 새로운 스레드가 담당하도록 옮김!!

	} // main

} // end class
