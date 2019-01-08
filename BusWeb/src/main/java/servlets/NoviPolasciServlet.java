package servlets;

import java.io.IOException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.PolasciManager;

/**
 * Servlet implementation class NoviPolasciServlet
 */
public class NoviPolasciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoviPolasciServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try{			
			int idPrevoznika = Integer.parseInt(request.getParameter("idPrevoznika"));
			int idLinije = Integer.parseInt(request.getParameter("idLinije"));			
			String vremePolaska = request.getParameter("vremePolaska");
			String datumOdstr = request.getParameter("datumOd");
			String datumDostr = request.getParameter("datumDo");
			String cenaStr = request.getParameter("cenaKarte");				
			String porukaPolazak = null;
			try {								
				int cena = Integer.parseInt(cenaStr);
				
				LocalDate datumOd = LocalDate.parse(datumOdstr);
				System.out.println("datum od "+ datumOd);
				LocalDate datumDo = LocalDate.parse(datumDostr);
				System.out.println("datum do"+ datumDo);
				Period intervalPeriod = Period.between(datumOd, datumDo);
				int n = intervalPeriod.getDays();
				int i;
				PolasciManager pm = new PolasciManager();				
				for(i=0; i < n; i++){
					LocalDate localDate = datumOd.plusDays(i);					
					Date datum = java.sql.Date.valueOf(localDate);
					pm.sacuvajPolazak(idPrevoznika, idLinije, vremePolaska,datum,cena);
				}				
				if(n <= i)
					porukaPolazak = "Uspešno uneti polasci!";
				else
					porukaPolazak = "Došlo je to greške!";
				request.setAttribute("porukaPolazak", porukaPolazak);
				request.getRequestDispatcher("NoviPolasci.jsp").forward(request, response);
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
