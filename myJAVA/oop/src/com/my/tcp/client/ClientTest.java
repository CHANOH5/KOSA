package com.my.tcp.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {

	public static void main(String[] args) {
		
		Socket s = null;
		String serverIp = "127.0.0.1"; // 인터넷이 연결되지 않아도 사용할 수 있는 IP , localHost
		int serverPort = 5432;
		
		OutputStream oos = null;
		
		try {
			
			s = new Socket(serverIp, serverPort);
			System.out.println("서버와 연결 성공");
			
			// 서버로 데이터 보내기
			oos = s.getOutputStream();
			oos.write(65);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch(ConnectException e) {
			System.out.println("서버가 아직 실행되지 않았습니다. 서버 실행을 확인하세용 ");
		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch
		

	} // main

} // end class
