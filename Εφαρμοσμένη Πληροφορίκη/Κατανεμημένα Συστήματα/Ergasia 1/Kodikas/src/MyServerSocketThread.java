import java.net.*;
import java.io.*;

public class MyServerSocketThread extends Thread {

	Socket socket = null;
	
	public MyServerSocketThread(Socket clientSocket) {
		super("MyServerSocketThread");
		this.socket = clientSocket;
	}
	
	public void run() {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			
			String input, output;
			ServerProtocol sp = new ServerProtocol();
			
			while(!(input = br.readLine()).equals("E")) {
				output = sp.processInput(input);
				pw.println(output);
			}
					
			br.close();
			pw.close();
			socket.close();
			
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
}
