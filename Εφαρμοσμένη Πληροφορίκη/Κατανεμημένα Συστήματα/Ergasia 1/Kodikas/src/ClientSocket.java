import java.net.*;
import java.io.*;

public class ClientSocket {
	public static void main(String[] args) throws IOException {
		
		// Dimiouria Socket pelati.
		Socket clientSocket = null;
		
		try {
			clientSocket = new Socket(InetAddress.getLocalHost(), 5000);
		} catch (UnknownHostException e) {
			System.err.println("Unknown Host: localhost");
			System.exit(1);
		}
		
		// Dimourgia rois eggrafis xaraktiron pros ton server.
		PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
		// Dimourgia rois anagnosis xaraktiron pros ton server.
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		// Dimiourgia rois anagnosis xaraktiron apo ton xristi meso pliktrologiou.
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Dose to onoma kai to ID sou xorismena me keno diastima: ");
		String input = null;
		String output = null;
		ClientProtocol cp = new ClientProtocol(outToServer, inFromServer);
		
		while((input = inFromUser.readLine()) != null) {
			output = cp.processInput(input);
			if(output.equals("FalseConnection")) {
				System.out.println("Edoses lathos stoixeia. Ksanadose onoma kai id");
			} else if(output.equals("negativeNumber")) {
				System.out.println("Den mporeite na kanete arnitiko poso analipsi. Ksanadoste poso analipsis: ");
			} else if(output.equals("limitAmount")){
				System.out.println("To poso pou prospatheite na kanete analipsi upervainei to evdomadiaio orio (420 euro). Ksanadoste poso analipsis: ");
			} else {
				//outToServer.println(output);
			}
			
			if(output.equals("E")) {
				break;
			} else {
				//System.out.println(inFromServer.readLine());
			}			
		}
		
		inFromUser.close();
		outToServer.close();
		inFromServer.close();
		clientSocket.close();
	}
}
