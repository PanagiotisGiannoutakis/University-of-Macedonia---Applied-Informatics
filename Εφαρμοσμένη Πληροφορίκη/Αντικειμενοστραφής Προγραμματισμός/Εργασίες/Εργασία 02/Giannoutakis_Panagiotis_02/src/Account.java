import java.text.DecimalFormat;


public class Account {
	
	private String kodikos;
	private double upoloipo;
	private double tokoi;
	
	public void setUpoloipo(Double logariasmos) {
		upoloipo = logariasmos;
	}
	
	public void setKodikos(String arithmos) {
		kodikos = arithmos;
	}
	
	public void Analipsi(Double posoAnalipsis) {
		if (upoloipo > posoAnalipsis) {
			upoloipo = upoloipo - posoAnalipsis;
			System.out.println("Epitixis analipsi xrimaton");
		} else {
			System.out.println("Anepitixis analipsi xrimaton");
		}
	}
	
	public void Katathesi(Double posoKatathesis) {
		upoloipo = upoloipo + posoKatathesis;
		System.out.println("Epitixis katathesi xrimaton");
	}
	
	public void UpologismosTokon(Double epitokio) {
		tokoi = 180 * ((epitokio * upoloipo) / 365);
		upoloipo = upoloipo + tokoi;
	}
	
	public void printInfo(String onomateponumo, String tilefono) {
		DecimalFormat myFormat = new DecimalFormat(".##");
		System.out.println("O idioktitis einai o " + onomateponumo);
		System.out.println("To tilefono tou idioktiti einai " + tilefono);
		System.out.println("O arithmos logariasmou sas einai: " + kodikos);
		System.out.println("To neo upoloipo tou logariasmou sas (mazi me tokous) einai: " + myFormat.format(upoloipo));
		System.out.println("Oi tokoi pou pistothikan gia auton ton mina einai: " + myFormat.format(tokoi));
		
	}
	
}
