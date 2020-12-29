package socketExam;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPServerExam {

	public static void main(String[] args) {
		
		try {
			new UDPServerExam().start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void start() throws IOException{
		
		// 서버에서는 클라이언트에서 데이터를 보낼수 있게 소켓에 포트번호를 부여해 생성한다.
		DatagramSocket socket = new DatagramSocket(7777);
		DatagramPacket inPacket, outPacket;
		
		byte[] inMsg = new byte[10];
		byte[] outMsg;
		
		// UDP에서는 소켓을 통해서 데이터를 받을 준비만 한다.
		while(true) {
			
			// 받을 패킷을 생성해서 데이터를 받는다.
			inPacket = new DatagramPacket(inMsg, inMsg.length);
			socket.receive(inPacket);
			
			// 받은 ip주소와 포트번호 저장(헤더정보)
			InetAddress address = inPacket.getAddress();
			int port = inPacket.getPort();
			
			// 보낼 날짜 데이터, 패킷으로 보내기 위해서 바이트배열로 변환시킨다. 
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			outMsg = time.getBytes(); // time을 바이트배열로 변환
			
			// 데이터를 보낼 패킷을 생성해서  데이터를 보낸다.
			outPacket = new DatagramPacket(outMsg, outMsg.length, address, port);
			socket.send(outPacket);
						
		}	// while			
		
		
	} // start


}
