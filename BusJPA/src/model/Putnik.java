package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the PUTNIK database table.
 * 
 */
@Entity
@Table(name="PUTNIK")
@NamedQuery(name="Putnik.findAll", query="SELECT p FROM Putnik p")
public class Putnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idputnik;

	private int brrezervacija;

	private String ime;

	private String password;

	private String prezime;

	private String user;

	//bi-directional many-to-many association to Polazak
	@ManyToMany(mappedBy="putniks")
	private List<Polazak> polazaks;

	//bi-directional many-to-one association to Komentar7
	@OneToMany(mappedBy="putnik")
	private List<Komentar> komentars;

	public Putnik() {
		polazaks= new ArrayList<Polazak>();
	}

	public int getIdputnik() {
		return this.idputnik;
	}

	public void setIdputnik(int idputnik) {
		this.idputnik = idputnik;
	}
			
	public int getBrrezervacija() {
		return brrezervacija;
	}

	public void setBrrezervacija(int brkarata) {
		this.brrezervacija = brkarata;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Polazak> getPolazaks() {
		return this.polazaks;
	}

	public void setPolazaks(List<Polazak> polazaks) {
		this.polazaks = polazaks;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setPutnik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setPutnik(null);

		return komentar;
	}
	
}