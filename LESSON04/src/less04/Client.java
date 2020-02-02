package less04;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private static final Scanner SCANNER = new Scanner(System.in);
	public static void main(String[] args) {
		for (;;) {
		try {
			Socket s=new Socket(InetAddress.getByName("192.168.1.250"), 3134);
			String data=SCANNER.nextLine();
			s.getOutputStream().write(data.getBytes());
			byte[] buf=new byte[64*1024];
			int r=s.getInputStream().read(buf);
			data=new String(buf,0,r);
			System.out.println("Answer: "+data);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
}