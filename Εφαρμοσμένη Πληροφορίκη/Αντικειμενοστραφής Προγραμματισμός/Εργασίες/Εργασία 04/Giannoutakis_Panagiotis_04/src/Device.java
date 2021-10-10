
public class Device {
	
	private double power;
	private double rate;
	private double MaxDevice;
	
	private final int volt = 230;
	
	public Device(double power, double rate) {
		this.power = power;
		this.rate = rate;
		MaxDevice = 0.0;
	}
	
	public void CalculateMaxDevice() {
		MaxDevice = (power) / (volt * rate);
	}

	
	
	public double getMaxDevice() {
		return MaxDevice;
	}

}
