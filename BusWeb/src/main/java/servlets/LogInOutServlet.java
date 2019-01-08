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
 * Servlet implementation class LogInOutServlet
 */
public class LogInOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LogInOutServlet() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			request.getSession().invalidate();
			request.getRequestDispatcher("Logovanje.jsp").forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{			
			String user = request.getParameter("korisnicko_ime");	
			String password = request.getParameter("lozinka");
			Putnik p = new RegistracijaManager().getPutnikZaUserPass(user, password);
			Radnik r = new RegistracijaManager().getRadnikZaUserPass(user, password);			
			String porukaError = null;
			
			if(p != null ){
				request.getSession().setAttribute("korisnik", p);
				request.getRequestDispatcher("ProfilKorisnika.jsp").forward(request, response);
			}else if(r!=null){
				if(r.getUser().equals("admin") && r.getPassword().equals("admin")){
					request.getSession().setAttribute("admin", r);
					request.getRequestDispatcher("Administrator.jsp").forward(request, response);					
				}else{
					request.getSession().setAttribute("radnik", r);
					request.getRequestDispatcher("ProfilRadnika.jsp").forward(request, response);
				}	
			}
			else{
				porukaError = "Greska, korisnik sa unetim paramterima ne postoji!";
				request.setAttribute("porukaError", porukaError);
				request.getRequestDispatcher("Logovanje.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
