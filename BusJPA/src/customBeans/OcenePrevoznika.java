package customBeans;

public class OcenePrevoznika {

	private String naziv;
	private double ocena;
	
	public OcenePrevoznika(String naziv,double ocena){
		this.naziv=naziv;
		this.ocena=ocena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}
	
	
	
}
