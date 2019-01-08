package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.PolasciManager;
import managers.RezervacijaKarteManager;
import model.Polazak;
import model.Prodaja;
import model.Radnik;

/**
 * Servlet implementation class ProdajaKarataZaRadnikaServlet
 */
public class ProdajaKarataZaRadnikaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ProdajaKarataZaRadnikaServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{		
			int idGrad = Integer.parseInt(request.getParameter("polazak"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String datumPolaskaStr = request.getParameter("datumPolaska");
			Date datumPolaska = sdf.parse(datumPolaskaStr);			
			List<Polazak> trazeniPolasci = new PolasciManager().getTrazeniPolasci(idGrad,datumPolaska);
			String porukaNemaPol = null;
			if(trazeniPolasci.isEmpty()){
				porukaNemaPol = "Nema polazaka za odabrane paramtere!";
				request.setAttribute("porukaNemaPol", porukaNemaPol);
				request.getRequestDispatcher("ProdajaKarataZaRadnika.jsp").forward(request, response);
			}else{
				request.setAttribute("trazeniPolasci", trazeniPolasci);
				request.setAttribute("datum",datumPolaskaStr);
				request.getRequestDispatcher("ProdajaKarataZaRadnika.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPolaska = Integer.parseInt(request.getParameter("idPolaska"));		
		int brkarata = Integer.parseInt(request.getParameter("brKarata"));		
		Radnik radnik = (Radnik)request.getSession().getAttribute("radnik");		
		RezervacijaKarteManager rk = new RezervacijaKarteManager();
		String poruka=null;
		
		Prodaja prodaja = rk.prodajKartu(idPolaska, brkarata, radnik);
		if(prodaja!=null){ 		
			poruka="Uspesno ste prodali karte!";
		}else{
			poruka="Doslo je do greske, niste prodali kartu!";
		}
		request.setAttribute("poruka", poruka);		
		request.getRequestDispatcher("ProdajaKarataZaRadnika.jsp").forward(request, response);
	}

}
