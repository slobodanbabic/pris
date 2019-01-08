package managers;

import java.util.Date;

import javax.persistence.EntityManager;

import model.Komentar;
import model.Prevoznik;
import model.Putnik;

public class KomentarManager {

	public Komentar sacuvajKomentar(Date datumkomentara,int ocena,
			String tekstkomentara,Prevoznik prevoznik,Putnik putnik){
		try{
			EntityManager em = JPAUtils.getEntityManager();
			em.getTransaction().begin();
			Komentar k = new Komentar();
			k.setDatumkomentara(datumkomentara);
			k.setOcena(ocena);
			k.setPrevoznik(prevoznik);
			k.setPutnik(putnik);
			k.setTekstkomentara(tekstkomentara);
			em.persist(k);
			em.getTransaction().commit();
			em.close();
			return k;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
