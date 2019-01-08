package managers;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Polazak;
import model.Prodaja;
import model.Putnik;
import model.Radnik;

public class RezervacijaKarteManager {

	public boolean rezervisiKartu(int idpolaska, int brkarata, Putnik putnik){		
		try {
			EntityManager em = JPAUtils.getEntityManager();
			em.getTransaction().begin();
			Polazak polazak= em.find(Polazak.class, idpolaska);	
			
			int brojMesta = polazak.getPrevoznik().getBrmesta();
			int brprodatihkarata = polazak.getBrprodatihkarata();
			int ukKarata = brprodatihkarata + brkarata;
			if( ukKarata <= brojMesta){	
				putnik.setBrrezervacija(putnik.getBrrezervacija()+1);					
				polazak.setBrprodatihkarata(ukKarata);		
				em.merge(putnik);
				em.merge(polazak);
				em.getTransaction().commit();
				em.close();
				return true;
			}
			em.close();
			return false;
		} catch (Exception e) {			
			e.printStackTrace();
			return false;			
		}		
	}
	
	public Prodaja prodajKartu(int idpolaska, int brkarata, Radnik radnik){		
		try {
			EntityManager em = JPAUtils.getEntityManager();			
			Polazak polazak = em.find(Polazak.class, idpolaska);				
			int brojMesta = polazak.getPrevoznik().getBrmesta();
			int brprodatihkarata = polazak.getBrprodatihkarata();
			int ukKarata = brprodatihkarata + brkarata;
			if( ukKarata <= brojMesta){	
				em.getTransaction().begin();
				Prodaja prodaja = new Prodaja();
				prodaja.setDatumprodaje(new Date());				
				prodaja.setPolazak(polazak);
				prodaja.setRadnik(radnik);				
				prodaja.setIznos( polazak.getCenaKarte() * brkarata);
				polazak.setBrprodatihkarata(ukKarata);
				em.persist(prodaja);		
				radnik.addProdaja(prodaja);
				polazak.addProdaja(prodaja);
				em.merge(radnik);				
				em.getTransaction().commit();;
				em.close();
				return prodaja;
			}
			em.close();
			return null;
		} catch (Exception e) {			
			e.printStackTrace();
			return null;			
		}		
	}
	
	public static double getDnevniProfit(Date datum,Radnik radnik){
		try{
			EntityManager em = JPAUtils.getEntityManager();
			TypedQuery<Prodaja> upit = em.createQuery("SELECT p FROM Prodaja p WHERE p.datumprodaje = :datum AND p.radnik = :radnik",Prodaja.class)
					.setParameter("datum", datum)
					.setParameter("radnik", radnik);
			double dnevniProfit = 0.0;
			List<Prodaja> dnevniPrihodRadnika = upit.getResultList();
			for(Prodaja p: dnevniPrihodRadnika){
				dnevniProfit+=p.getIznos();
			}			
			return dnevniProfit;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
}	
