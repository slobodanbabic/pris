package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PREVOZNIK database table.
 * 
 */
@Entity
@Table(name="PREVOZNIK")
@NamedQuery(name="Prevoznik.findAll", query="SELECT p FROM Prevoznik p")
public class Prevoznik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idprevoznik;

	private int brmesta;

	private String naziv;

	//bi-directional many-to-one association to Polazak
	@OneToMany(mappedBy="prevoznik")
	private List<Polazak> polazaks;

	//bi-directional many-to-one association to Komentar7
	@OneToMany(mappedBy="prevoznik")
	private List<Komentar> komentars;

	public Prevoznik() {
	}

	public int getIdprevoznik() {
		return this.idprevoznik;
	}

	public void setIdprevoznik(int idprevoznik) {
		this.idprevoznik = idprevoznik;
	}

	public int getBrmesta() {
		return this.brmesta;
	}

	public void setBrmesta(int brmesta) {
		this.brmesta = brmesta;
	}


	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Polazak> getPolazaks() {
		return this.polazaks;
	}

	public void setPolazaks(List<Polazak> polazaks) {
		this.polazaks = polazaks;
	}

	public Polazak addPolazak(Polazak polazak) {
		getPolazaks().add(polazak);
		polazak.setPrevoznik(this);

		return polazak;
	}

	public Polazak removePolazak(Polazak polazak) {
		getPolazaks().remove(polazak);
		polazak.setPrevoznik(null);

		return polazak;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setPrevoznik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setPrevoznik(null);

		return komentar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idprevoznik;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prevoznik other = (Prevoznik) obj;
		if (idprevoznik != other.idprevoznik)
			return false;
		return true;
	}
	
	

}