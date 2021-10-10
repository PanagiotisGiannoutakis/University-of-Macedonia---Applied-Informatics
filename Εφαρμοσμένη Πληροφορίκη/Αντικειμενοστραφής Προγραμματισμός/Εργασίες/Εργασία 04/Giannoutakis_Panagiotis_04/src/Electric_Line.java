import java.util.*;


public class Electric_Line {
	
	private double MaxLine;
	private double asfaleia;
	private double diatomi;
	private ArrayList<Device> devices;
	
	private final double poso_kalodio = 0.021;
	private final double ekthetis = 1.61;
	
	public Electric_Line() {
		devices = new ArrayList<Device>();
		MaxLine = 0.0;
		asfaleia = 0.0;
		diatomi = 0.0;
	}
	
	public void addDevices(Device device) {
		devices.add(device);
	}
	
	public void CalculateMaxLine() {
		for(int i=0; i<devices.size(); i++) {
			devices.get(i).CalculateMaxDevice();
			MaxLine = MaxLine + devices.get(i).getMaxDevice();
		}
	}
	
	public void EntasiAsfaleias() {
		asfaleia = MaxLine - 5;
	}
	
	public void DiatomiKalodiou() {
		diatomi = (poso_kalodio) * (Math.pow(MaxLine, ekthetis));
	}
	

	
	
	public double getMaxLine() {
		return MaxLine;
	}
	
	public double getAsfaleia() {
		return asfaleia;
	}

	public double getDiatomi() {
		return diatomi;
	}
	
}