package com.my.tcp.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientTest {

	public static void main(String[] args) {
		
		Socket s = null;
		String serverIp = "127.0.0.1"; // 인터넷이 연결되지 않아도 사용할 수 있는 IP , localHost
//		String serverIp = "192.168.1.21"; // 셍나 IP: 192.168.1.21
//		String serverIp = "192.168.1.84";
		
		int serverPort = 5432;
		
		OutputStream oos = null;
		DataOutputStream dos = null;
		Scanner sc = new Scanner(System.in);
		
		InputStream is =null;
		DataInputStream dis = null;
		
		try {
			
			s = new Socket(serverIp, serverPort);
			System.out.println("서버와 연결 성공");
			
			// 서버로 데이터 보내기
			oos = s.getOutputStream();
			
//			oos.write(66);
			
			dos = new DataOutputStream(oos);
//			dos.writeUTF("셍나는 바보 ㅡㅅ ㅡ");
			
			is = s.getInputStream();
			dis = new DataInputStream(is);
			
			String sendMsg;
			do {
				System.out.println("서버로 보낼 메시지(종료하려면 quit를 입력하세요.) : ");
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
				
				String receiveMsg = dis.readUTF();
				System.out.println("서버로부터 받은 메세지: " + receiveMsg);

			} while(!sendMsg.equals("quit"));
			
			// 서버로부터 데이터 받기
			
		} catch (UnknownHostException e) {
			System.out.println(serverIp + "서버가 존재하지 않습니다. 서버 IP를 확인하세요 -ㅅ- ");
		} catch(ConnectException e) { // 연결 예외 - 부모
			System.out.println("서버가 아직 실행되지 않았습니다. 서버 실행을 확인하세용 ");
		}  catch(SocketException e) { // 소켓 예외 - 자식, 연결은 됐는데 소켓이 망가짐
			System.out.println("서버가 강제종료 되었습니다. 서버를 확인하세요 ㅇㅂㅇ");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // try-catch
			} // if
		} // try-catch-finally
		
		// 192.168.1.22 학원 아이피

	} // main

} // end class
