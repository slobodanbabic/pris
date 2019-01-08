package managers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Komentar;
import model.Prevoznik;


public class PrevoznikManager {
	
	public Prevoznik sacuvajPrevoznika(int brMesta,String naziv){
		try{
			EntityManager em = JPAUtils.getEntityManager();
			em.getTransaction().begin();
			Prevoznik p = new Prevoznik();
			p.setBrmesta(brMesta);
			p.setNaziv(naziv);		
			em.persist(p);
			em.getTransaction().commit();
			em.close();
			return p;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
		
	public List<Komentar> sviKomentariZaPrevoznika(int idPrevoznik){
		try{
			EntityManager em = JPAUtils.getEntityManager();
			TypedQuery<Komentar> upit = em.createQuery("SELECT k FROM Komentar k JOIN k.prevoznik p WHERE p.idprevoznik =:idPrevoznik", Komentar.class);
			upit.setParameter("idPrevoznik", idPrevoznik);
			List<Komentar> komentariZP = upit.getResultList();
			return komentariZP;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Map<Prevoznik,Double> najboljeOcenjeniPrevoznici(){
		EntityManager em = JPAUtils.getEntityManager();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			LocalDate danasnjiDatum = LocalDate.now();			
			LocalDate prosliMesecDatum=danasnjiDatum.minusDays(30);				
			TypedQuery<Komentar> q = em.createQuery("SELECT komentar FROM Komentar komentar where komentar.datumkomentara BETWEEN  :prosliMesecDatum AND :danasnjiDatum",Komentar.class);
			q.setParameter("prosliMesecDatum", sdf.parse(prosliMesecDatum.toString()));
			q.setParameter("danasnjiDatum", sdf.parse(danasnjiDatum.toString()));
			List<Komentar>komentari = q.getResultList();
			
			Map<Prevoznik,Double>prevozniciProsek=new HashMap<>(); 
			Map<Prevoznik,Double>prevozniciProsekSort=new LinkedHashMap<>(); 
			
			prevozniciProsek = komentari
					.stream()
					.collect(Collectors.groupingBy(Komentar::getPrevoznik,Collectors.averagingDouble(Komentar::getOcena)));						
						
			prevozniciProsek.entrySet()
					.stream()	
					.sorted(Map.Entry.<Prevoznik,Double>comparingByValue().reversed())	
					.limit(3)
					.forEach(entry -> prevozniciProsekSort.put(entry.getKey(), entry.getValue()) );			
			
			return prevozniciProsekSort;
					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
