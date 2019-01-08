package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.RegistracijaManager;
import model.Putnik;
import model.Radnik;

/**
 * Servlet implementation class RegistracijaServlet
 */
public class RegistracijaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegistracijaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			String ime = request.getParameter("ime");
			String prezime = request.getParameter("prezime");
			String user = request.getParameter("korisnicko_ime");
			String pass = request.getParameter("lozinka");		
			String radnik = request.getParameter("radnik");			
			RegistracijaManager rm = new RegistracijaManager();
			Radnik r = null;
			Putnik p = null;
			
			if(radnik != null){
				r = rm.sacuvajRadnika(ime, prezime, user, pass);
			}else{				
				p = rm.sacuvajPutnika(ime, prezime, user, pass);
			}
			String porukaReg = null;
			if(p != null || r != null){				
				porukaReg = "Uspešno ste izvršili registraciju!";
				request.setAttribute("porukaReg", porukaReg);
				request.getRequestDispatcher("Logovanje.jsp").forward(request, response);
			}else{			
				porukaReg = "Doslo je do greške!";
				request.setAttribute("porukaReg", porukaReg);
				request.getRequestDispatcher("Registracija.jsp").forward(request, response);
			}
			
		}catch(Exception e){
			e.printStackTrace();			
		}
		
	}

}
