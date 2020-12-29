package socketExam;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;



public class TCPServerExam {

	public static void main(String[] args) {
		
		/*
		tcp 소켓통신 과정
		1. 서버프로그램에서 서버소켓을 특정포트번호를 설정해서 클라이언트의 요청을 기다린다.
		2. 클라이언트  프로그램에서는 ip주소와 포트번호를 설정한 클라이언트 소켓을  다음에 생성해서 서버소켓에게 요청을 한다.
		3. 요청을 받은 서버소켓은 새로운 소켓을 생성해서 클라이언트 소켓과  연결해준다.
		4. 새로운 소켓과 클라이언트 소켓은 일대일 통신을 할 수 있게 된다.
		5. 각 소켓은 입출력스트림을 이용해서 데이터를 이동시킬 수 있다.
		
		데이터 단위 : 입출력 스트림
		서버에서는 서버소켓에 포트번호를 설정
		통신을하는 소켓의 스트림메서드로 데이터를 주고 받는다.
		*/
		
		// 서버 소켓은 소켓간에 연결을 해주는 역할만 한다.
		ServerSocket serverSocket = null;
			
		try {
			
			serverSocket = new ServerSocket(7777);
			System.out.println(getTime() + "서버가 준비되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			
			try {
				
				System.out.println( getTime()+ "연결요청을 기다립니다.");
				
				// 서버 소켓에서 요청이 들어올 때까지 실행을 멈추고 대기한다, 요청이 들어오면 새로운 소켓을 생성한다. 
				Socket socket = serverSocket.accept();
				System.out.println(getTime() + socket.getInetAddress() + "로부터 연결요청이 들어왔습니다.");
				
				// 소켓에 출력스트림을 붙임
				OutputStream out = socket.getOutputStream();
				// 통신을 해서 data를 보내기 위해서 서브스트림생성
				DataOutputStream dos = new DataOutputStream(out);
				
				dos.writeUTF("[notice] test msg from server.");
				
				dos.close();
				socket.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // while
	}// main
	
	public static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}
}
