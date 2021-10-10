import java.io.*;
import java.net.*;


public class ClientProtocol {
	
	String state;
	PrintWriter outToServer;
	BufferedReader inFromServer;
	
	public ClientProtocol(PrintWriter pw, BufferedReader bf) throws IOException {
		state = "I";
		outToServer = pw;
		inFromServer = bf;
	}
	
	public String processInput(String input) throws IOException {
		
		String output = null;
		switch(state) {
			case "I": 
				outToServer.println(input);
				String egkurotita = inFromServer.readLine();
				if(egkurotita.equals("TRUE")) {
					output="NotI";
					state="NotI";
					System.out.println("Egkuri sundesi sto sustima !!!");
					System.out.println("Menou: Analipsi (A), Katathesi (K), Ypoloipo (Y), Eksodos (E)");
				} else if(egkurotita.equals("FALSE")) {
					output="FalseConnection";
					state="I";
				} 
				break;
				
			case "NotI":
				
				if(input.equals("A")) {
					state = "A";
					output = "A";
					System.out.println("Dose to poso pou thes na kaneis analipsi");
				} else if(input.equals("K")) {
					state = "K";
					output = "K";
					System.out.println("Dose to poso pou thes na kaneis katathesi");
				} else if(input.equals("Y")) {
					state = "Y";
					output = "Y";
				} else if(input.equals("E")) {
					state = "E";
					output = "E";
				}
				break;
				
			case "A":
				int posoAnalipsis = Integer.parseInt(input);
				
				if(posoAnalipsis <= 0) {
					output = "negativeNumber";
					state = "A";
				} else if(posoAnalipsis > 420) {
					output = "limitAmount";
					state = "A";
				} else {
					outToServer.println(posoAnalipsis);
					output = inFromServer.readLine();
					if(output.matches("[0-9]*")) {
						System.out.println("Epituxis analipsi posou. To neo upoloipo anerxetai sta " + Integer.parseInt(output) + "euro");
						System.out.println("Menou: Analipsi (A), Katathesi (K), Ypoloipo (Y), Eksodos (E)");
						state = "NotI";
					} else {
						System.out.println(output);
						state = "A";
					}
				}
				break;
				
			case "K":
				int posoKatathesis = Integer.parseInt(input);
				
				if(posoKatathesis <= 0) {
					output = "negativeNumber";
					state = "K";
				} else {
					outToServer.println(posoKatathesis);
					output = inFromServer.readLine();
					System.out.println("Epituxis katathesi posou. To neo upoloipo anerxetai sta " + output + " euro.");
					System.out.println("Menou: Analipsi (A), Katathesi (K), Ypoloipo (Y), Eksodos (E)");
					state = "NotI";
				}
				break;
				
			case "Y":
				outToServer.println(input);
				output = inFromServer.readLine();
				System.out.println("To upoloipo autin tin stigmi anerxetai sta " + output + " euro");
				System.out.println("Menou: Analipsi (A), Katathesi (K), Ypoloipo (Y), Eksodos (E)");
				state = "NotI";
				break;
				
			case "E":
				outToServer.println(input);
				output = inFromServer.readLine();
				state = "E";
				break;
		}
		
		return output;
	}
}
