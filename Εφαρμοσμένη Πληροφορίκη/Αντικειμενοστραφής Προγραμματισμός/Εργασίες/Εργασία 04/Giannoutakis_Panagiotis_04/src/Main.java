
public class Main {

	public static void main(String[] args) {
		
		Device device1 = new Device(3500, 0.9);
		Device device2 = new Device(4000, 0.8);
		Device device3 = new Device(5000, 1);
		Device device4 = new Device(2500, 0.6);
		Device device5 = new Device(3000, 0.8);
		Device device6 = new Device(4200, 0.9);
		Device device7 = new Device(5500, 1.1);
		
		Electric_Line line1 = new Electric_Line();
		Electric_Line line2 = new Electric_Line();
		Electric_Line line3 = new Electric_Line();
		
		line1.addDevices(device1);
		line1.addDevices(device2);
		line2.addDevices(device3);
		line2.addDevices(device4);
		line3.addDevices(device5);
		line3.addDevices(device6);
		line3.addDevices(device7);
		
		Electric_Board board = new Electric_Board();
		
		board.addLines(line1);
		board.addLines(line2);
		board.addLines(line3);
		
		board.CalculateMaxBoard();
		board.printResults();
		
	}

}
