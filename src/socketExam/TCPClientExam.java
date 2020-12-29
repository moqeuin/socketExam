package socketExam;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClientExam {

	public static void main(String[] args) {
		
		// 한 대의 기기에서 통신하기 위해서 자기의 ip로 설정
		String serverIp = "127.0.0.1";
			
		try {
			System.out.println("서버와 연결중입니다. 서버 IP : " + serverIp);
			
			// 소켓을 생성해서 연결을 요청한다.
			Socket socket = new Socket(serverIp, 7777);
			
			// 서버로부터 입력받을 스트림 생성
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			System.out.println("서버로부터 받은 메세지 : " + dis.readUTF());
			System.out.println("연결을 종료합니다.");
			
			dis.close();
			socket.close();
			System.out.println("연결이 종료되었습니다.");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
