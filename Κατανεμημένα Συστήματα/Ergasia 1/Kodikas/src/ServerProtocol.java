
public class ServerProtocol {
	
	String state;
	int upoloipo;
	
	public ServerProtocol() {
		state = "I";
		upoloipo = 5000;
	}
	
	public String processInput(String input) {
		String output = null;
		
		switch(state) {
			case "I": 
				String tautopoiisi[] = input.split(" ", 2);
				if(tautopoiisi[0].matches("[a-zA-Z]*") && tautopoiisi[1].matches("[0-9]*")) {
					output = "TRUE";
					state = "NotI";
				} else {
					output = "FALSE";
				}
				break;
				
			case "NotI": 
				output = input;
				state = input;
				//break;
				
			case "A": 
				upoloipo -= Integer.parseInt(input);
				if(upoloipo > 0) {
					output = Integer.toString(upoloipo);
					state = "NotI";
				} else {
					output="To poso analipsis upervainei to diathesimo upoloipo.";
					state = "A";
				}
				break;
				
			case "K":
				upoloipo += Integer.parseInt(input);
				output = Integer.toString(upoloipo);
				state = "NotI";
				break;
				
			case "Y":
				output = Integer.toString(upoloipo);
				state = "NotI";
				break;
				
			case "E":
				output = state;
				break;
				
		}
		
		return output;
	}
}
