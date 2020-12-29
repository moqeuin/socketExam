package socketExam;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

 /*
  UDP소켓통신과정
  1. 서버소켓을 필요로하지 않고 오직 소켓간 일대일통신을 이용한다.
  2. 클라이언트에서는 소켓을 생성해서 보낼 소켓의 IP주소와 포트번호를 설정해서 패킷을 만든 뒤 소켓에 붙인다.
  3. 데이터를 받을 때도 받을 패킷을 만든 뒤 소켓에 붙인다.
  4. 서버에서도 소켓에 포트번호만 설정하고 나머지는 같다. 
  
   	데이터송수신 수단 : 패킷
   	서버에서는 소켓에 포트번호를 설정
	
 */

public class UDPClientExam {

	public static void main(String[] args) {
		
		try {
			
			new UDPClientExam().start();
				
		} catch (UnknownHostException e) {		
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void start() throws IOException, UnknownHostException{
		
		// UDP는 소켓을 통해서 패킷으로 데이터를 보낸다.
		DatagramSocket datagramSocket = new DatagramSocket();
		// 패킷의 매개변수로 이용할 IP클래스 생성
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
		
		byte[] msg = new byte[100];
		
		// DatagramPacket은 데이터와 헤더로 구성되어 있다.
		// 보낼 패킷과 받을 패킷을 생성
		DatagramPacket outPacket = 
								new DatagramPacket(msg, 1, serverAddress, 7777);
		DatagramPacket inPacket =
								new DatagramPacket(msg, msg.length);
		
		// 데이터그램소켓을 통해서 데이터를 주고받는다.
		datagramSocket.send(outPacket);
		datagramSocket.receive(inPacket);
		
		System.out.println("current server time : " + new String(inPacket.getData()));
		
		datagramSocket.close();
	}

}
