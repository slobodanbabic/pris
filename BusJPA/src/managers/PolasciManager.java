package managers;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;

import model.Grad;

import model.Linija;
import model.Polazak;
import model.Prevoznik;
import model.Putnik;


public class PolasciManager {	
	
	
	
	
	public List<Polazak> sviPolasci(EntityManager em){
		try {
			TypedQuery<Polazak> upit = em.createQuery("SELECT p FROM Polazak p", Polazak.class);
			List<Polazak> sviPolasci = upit.getResultList();
			return sviPolasci;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static List<Linija> sveRazliciteLinije(){			
		try {
			EntityManager em = JPAUtils.getEntityManager();	
			TypedQuery<Linija> upit = em.createQuery("SELECT DISTINCT(p.linija) FROM Polazak p ",Linija.class);			
			List<Linija> linije = upit.getResultList();
			return linije;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	//razliciti gradovi koji se nalaze u tabeli polazak
	public static List<Grad> sviRazlicitiPolasci(){			
		try {
			EntityManager em = JPAUtils.getEntityManager();	
			TypedQuery<Grad> upit = em.createQuery("SELECT DISTINCT(p.linija.grad) FROM Polazak p join p.linija l join l.grad g",Grad.class);			
			List<Grad> sviPolasci = upit.getResultList();
			return sviPolasci;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	
	public List<Polazak> getTrazeniPolasci(int idGrad,Date datumPolaska){
		try{			
			EntityManager em = JPAUtils.getEntityManager();
			TypedQuery<Polazak> upit = 
				em.createQuery("SELECT p FROM Polazak p join p.linija l join l.grad g"
						+ " WHERE g.idgrad=:idGrad AND :datumPolaska like p.datum", Polazak.class);

			upit.setParameter("idGrad", idGrad);
			upit.setParameter("datumPolaska", datumPolaska);
			
			List<Polazak> polasci = upit.getResultList();
			System.out.println("dodao sam getResultList() ...");			
			return polasci;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	public static boolean sacuvajPrevoznika(String naziv, int brMesta){
		try {
			EntityManager em = JPAUtils.getEntityManager();
			em.getTransaction().begin();
			Prevoznik p = new Prevoznik();
			p.setNaziv(naziv);
			p.setBrmesta(brMesta);
			em.persist(p);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static boolean sacuvajGrad(String naziv){
		try {
			EntityManager em = JPAUtils.getEntityManager();
			em.getTransaction().begin();
			Grad g = new Grad();
			g.setNaziv(naziv);
			em.persist(g);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean sacuvajLinuju(int idGrad,String nazivLinije){
		try {
			EntityManager em = JPAUtils.getEntityManager();
			em.getTransaction().begin();
			Linija l = new Linija();
			Grad g = em.find(Grad.class, idGrad);
			l.setNazivlinije(nazivLinije);
			l.setGrad(g);			
			em.persist(l);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Polazak sacuvajPolazak(int idPrevoznika,int idLinije,String vremeP,Date datum, int cena){
		try{			
			EntityManager em = JPAUtils.getEntityManager();
			em.getTransaction().begin();
			Polazak p = new Polazak();							
			p.setPrevoznik(em.find(Prevoznik.class, idPrevoznika));
			p.setLinija(em.find(Linija.class, idLinije));					
			p.setVremepolaska(vremeP);
			p.setDatum(datum);		
			p.setCenaKarte(cena);		
			em.persist(p);
			em.getTransaction().commit();
			em.close();
			return p;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static List<Grad> sviGradovi(){
		try {
			EntityManager em =JPAUtils.getEntityManager();
			TypedQuery<Grad> upit = em.createQuery("SELECT g from Grad g",Grad.class);
			List<Grad> gradovi = upit.getResultList();
			return gradovi;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Prevoznik> sviPrevoznici(){
		try {
			EntityManager em =JPAUtils.getEntityManager();
			TypedQuery<Prevoznik> upit = em.createQuery("SELECT p from Prevoznik p",Prevoznik.class);
			List<Prevoznik> prevoznici = upit.getResultList();
			return prevoznici;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static List<Linija> sveLinije(){
		try {
			EntityManager em =JPAUtils.getEntityManager();
			TypedQuery<Linija> upit = em.createQuery("SELECT l from Linija l",Linija.class);
			List<Linija> linije = upit.getResultList();
			return linije;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Polazak> vratiPolazakeZaDatumIDestinaciju(int idGrad, Date datum){
		try {
			EntityManager em = JPAUtils.getEntityManager();						
			TypedQuery<Polazak> q = em.createQuery("select p from Polazak p join p.linija l join l.grad g where :datum like p.datum and g.idgrad=:idGrad",Polazak.class);
			q.setParameter("idGrad", idGrad);
			q.setParameter("datum", datum);
			List<Polazak> polasci = q.getResultList();
			return polasci;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	
	

	public boolean rezervacijaMesta(int idPolaska, Putnik putnik,int brKarata){
		try{			
			EntityManager em  = JPAUtils.getEntityManager();
			em.getTransaction().begin();
			Polazak pol = em.find(Polazak.class, idPolaska);
			int brProdatihKarata = pol.getBrprodatihkarata();
			int noviBrProdatihKarata = brKarata+brProdatihKarata;
			pol.setBrprodatihkarata(noviBrProdatihKarata);
			pol.getPutniks().add(putnik);			
			putnik.getPolazaks().add(pol);			
			em.merge(pol);
			em.merge(putnik);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
