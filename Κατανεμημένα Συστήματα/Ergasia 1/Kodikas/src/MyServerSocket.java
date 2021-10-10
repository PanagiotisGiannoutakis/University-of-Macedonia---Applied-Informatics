import java.net.*;
import java.io.*;

public class MyServerSocket {

	public static void main(String[] args) throws IOException {
		
		ServerSocket myServerSocket = new ServerSocket(5000);
		
		boolean listening = true;
		
		while(listening) {
			new MyServerSocketThread(myServerSocket.accept()).start();
		}
		//Socket mySocket = myServerSocket.accept();
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
		//PrintWriter pw = new PrintWriter(mySocket.getOutputStream(),true);
		
		//String input, output;
		//ServerProtocol sp = new ServerProtocol();
		
		//while(!(input = br.readLine()).equals("E")) {
			//output = sp.processInput(input);
			//pw.println(output);
		//}
				
		//br.close();
		//pw.close();
		//mySocket.close();
		myServerSocket.close();
	}

}
